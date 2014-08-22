package ni.gob.minsa.hsf.web.listener;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import ni.gob.minsa.hsf.domain.audit.AuditTrail;
import ni.gob.minsa.hsf.domain.audit.Auditable;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.Initializable;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreLoadEvent;
import org.hibernate.event.PreLoadEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** 
 * Audit Log Listener is used to log insert, update, delete, and load operations. Complete list of listeners/events can be obtained at <tt>org.hibernate.event.EventListeners</tt>. 
 *  
 * @see org.hibernate.event.EventListeners 
 * @author whoover 
 */  
public final class HibernateAuditLogListener implements  
PreDeleteEventListener, PreInsertEventListener, PreUpdateEventListener,  
PreLoadEventListener, Initializable { 
	
	
	private static final long serialVersionUID = 1L;  
    private static final Logger LOG = LoggerFactory.getLogger(HibernateAuditLogListener.class);  
    public static final String OPERATION_TYPE_INSERT = "INSERT";  
    public static final String OPERATION_TYPE_UPDATE = "UPDATE";  
    public static final String OPERATION_TYPE_DELETE = "DELETE";  
  

    /** 
     * {@inheritDoc} 
     */  
    @Override  
    public final void initialize(final Configuration cfg) {  
        //  
    }  
  
    /** 
     * Log deletions made to the current model in the the Audit Trail. 
     *  
     * @param event 
     *            the pre-deletion event 
     */  
    @Override  
    public final boolean onPreDelete(final PreDeleteEvent event) {  
        try {  
            final Serializable entityId = event.getPersister().hasIdentifierProperty() ? event.getPersister().getIdentifier(event.getEntity(), event.getSession()) : null;  
            final String entityName = event.getEntity().getClass().toString();  
            final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());  
            
            // need to have a separate session for audit save  
            if (event.getEntity() instanceof Auditable){
            	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                final String actorId = authentication.getName();
	            StatelessSession session = event.getPersister().getFactory().openStatelessSession();  
	            session.beginTransaction();  
	  
	            if (LOG.isDebugEnabled()) {  
	                LOG.debug("{} for: {}, ID: {}, actor: {}, date: {}", new Object[] { entityName, entityId, actorId, transTime });  
	            }  
	            session.insert(new AuditTrail(entityId.toString(), entityName, OPERATION_TYPE_DELETE, null, null, OPERATION_TYPE_DELETE, actorId, transTime));  
	  
	            session.getTransaction().commit();  
	            session.close();  
            }
        } catch (HibernateException e) {  
            LOG.error("Unable to process audit log for DELETE operation", e);  
        }  
        return false;  
    }  
    /** 
     * Log insertions made to the current model in the the Audit Trail. 
     *  
     * @param event 
     *            the pre-insertion event 
     */  
    @Override  
    public final boolean onPreInsert(final PreInsertEvent event) {  
        try {    
            final String entityId = event.getPersister().hasIdentifierProperty() ? event.getPersister().getIdentifier(event.getEntity(), event.getSession())  
                    .toString() : "";  
            final String entityName = event.getEntity().getClass().toString();  
            final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());  
            final EntityMode entityMode = event.getPersister().guessEntityMode(event.getEntity());  
            Object newPropValue = null;  
            
            if (event.getEntity() instanceof Auditable){
            	Auditable obj = (Auditable) event.getEntity();
            	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                final String actorId = authentication.getName();
	            // need to have a separate session for audit save  
	            StatelessSession session = event.getPersister().getFactory().openStatelessSession();  
	            session.beginTransaction();  
	  
	            for (String propertyName : event.getPersister().getPropertyNames()) { 
	            	if (obj.isFieldAuditable(propertyName)){
		                newPropValue = event.getPersister().getPropertyValue(event.getEntity(), propertyName, entityMode);  
		                // because we are performing an insert we only need to be concerned will non-null values  
		                if (newPropValue != null) {  
		                    // collections will fire their own events  
		                    if (!(newPropValue instanceof Collection)) {  
		                        if (LOG.isDebugEnabled()) {  
		                            LOG.debug("{} for: {}, ID: {}, property: {}, value: {}, actor: {}, date: {}", new Object[] { OPERATION_TYPE_INSERT, entityName, entityId, propertyName, newPropValue, actorId, transTime });  
		                        }  
		                        session.insert(new AuditTrail(entityId, entityName, propertyName, null, newPropValue != null ? newPropValue.toString() : null, OPERATION_TYPE_INSERT, actorId, transTime));  
		                    }  
		                }  
	            	}
	            }  
	  
	            session.getTransaction().commit();  
	            session.close(); 
            }
        } catch (HibernateException e) {  
            LOG.error("Unable to process audit log for INSERT operation", e);  
        }  
        return false;  
    }  
  
    /** 
     * Log updates made to the current model in the the Audit Trail. 
     *  
     * @param event 
     *            the pre-update event 
     */  
    @Override  
    public final boolean onPreUpdate(PreUpdateEvent event) {  
        try {  
            final Serializable entityId = event.getPersister().hasIdentifierProperty() ? event.getPersister().getIdentifier(event.getEntity(), event.getSession())  
                    : null;  
            final String entityName = event.getEntity().getClass().toString();  
            final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());  
            final EntityMode entityMode = event.getPersister().guessEntityMode(event.getEntity());  
            Object oldPropValue = null;  
            Object newPropValue = null; 
            
            if (event.getEntity() instanceof Auditable){
              	Auditable obj = (Auditable) event.getEntity();
              	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                final String actorId = authentication.getName();
                // need to have a separate session for audit save  
	            StatelessSession session = event.getPersister().getFactory().openStatelessSession();  
	            session.beginTransaction();  
	  
	            // get the existing entity from session so that we can extract existing property values  
	            Object existingEntity = session.get(event.getEntity().getClass(), entityId);  
	  
	            // cycle through property names, extract corresponding property values and insert new entry in audit trail  
	            for (String propertyName : event.getPersister().getPropertyNames()) {  
	            	if (obj.isFieldAuditable(propertyName)){
		                newPropValue = event.getPersister().getPropertyValue(event.getEntity(), propertyName, entityMode);  
		                if (newPropValue != null) {  
		                    // collections will fire their own events  
		                    if (!(newPropValue instanceof Collection)) {  
		                        oldPropValue = event.getPersister().getPropertyValue(existingEntity, propertyName, entityMode); 
		                        if(!newPropValue.equals(oldPropValue)){
			                        if (LOG.isDebugEnabled()) {  
			                            LOG.debug("{} for: {}, ID: {}, property: {}, old value: {}, new value: {}, actor: {}, date: {}", new Object[] { OPERATION_TYPE_UPDATE, entityName, entityId, propertyName, oldPropValue, newPropValue, actorId, transTime });  
			                        }  
			                        session.insert(new AuditTrail(entityId.toString(), entityName, propertyName, oldPropValue != null ? oldPropValue.toString() : null, newPropValue != null ? newPropValue  
			                                .toString() : null, OPERATION_TYPE_UPDATE, actorId, transTime));  
		                        }
		                    }  
		                }  
	            	}
	            }  
	  
	            session.getTransaction().commit();  
	            session.close(); 
            }
        } catch (HibernateException e) {  
            LOG.error("Unable to process audit log for UPDATE operation", e);  
        }  
        return false;  
    }  
  
    /** 
     * Log the loading of the current model in the the Audit Trail. 
     *  
     * @param event 
     *            the pre-load event 
     */  
    @Override  
    public final void onPreLoad(final PreLoadEvent event) {  
        // TODO : complete load audit log if desired  
    }  
    
    
}
