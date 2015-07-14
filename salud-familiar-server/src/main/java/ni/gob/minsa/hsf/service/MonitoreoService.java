package ni.gob.minsa.hsf.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto UserSistema
 * 
 * @author William Aviles
 * 
 **/

@Service("monitoreoService")
@Transactional
public class MonitoreoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los accesos de usuarios para un usuario
	 * 
	 * @return una lista de <code>UserAccess</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Object> getUserAccess(UserSistema usuario, long date1, long date2) {
		List<Object> accesos = null;
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("Select ua.usuario.username as nombre, ua.usuario.completeName as nombrec, Count(ua.id) as total, min(ua.loginDate) as primera, max(loginDate) as ultima" +
					" From UserAccess ua where ua.loginDate between :fechaInicio and :fechaFinal group by ua.usuario.username,ua.usuario.completeName");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos = query.list();
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("Select ua.usuario.username as nombre, ua.usuario.completeName as nombrec, Count(ua.id) as total, min(ua.loginDate) as primera, max(loginDate) as ultima " +
					"From UserAccess ua where (((ua.usuario.entidad.codigo =:silais)) AND ((ua.loginDate) Between :fechaInicio and :fechaFinal)) " +
					"group by ua.usuario.username,ua.usuario.completeName");
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos = query.list();
			
			query = session.createQuery("Select ua.usuario.username as nombre, ua.usuario.completeName as nombrec, Count(ua.id) as total, min(ua.loginDate) as primera, max(loginDate) as ultima " +
					"From UserAccess ua where (((ua.loginDate) Between :fechaInicio and :fechaFinal) AND ((ua.usuario.unidad.codigo) In (select codigo from Unidades unds where unds.entidadAdtva =:silais))) " +
					"group by ua.usuario.username,ua.usuario.completeName");
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			
			accesos.addAll(query.list());
			
			
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("Select ua.usuario.username as nombre, ua.usuario.completeName as nombrec, Count(ua.id) as total, min(ua.loginDate) as primera, max(loginDate) as ultima " +
					"From UserAccess ua where ua.loginDate between :fechaInicio and :fechaFinal and " +
					"(ua.usuario.unidad.codigo =:unidad or (ua.usuario.unidad.codigo in (Select codigo from Unidades where unidadAdtva =:unidad))) " +
					"group by ua.usuario.username,ua.usuario.completeName");
			query.setParameter("unidad", usuario.getUnidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos = query.list();
		}
		// Retrieve all
		return  accesos;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getDaysAccess(UserSistema usuario, long date1, long date2) {
		List<Object> accesos = new ArrayList<Object>();
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("Select to_char(ua.loginDate,'yyyy-mm-dd'), Count(ua.id) as total" +
					" From UserAccess ua where ua.loginDate between :fechaInicio and :fechaFinal group by to_char(ua.loginDate,'yyyy-mm-dd') order by to_char(ua.loginDate,'yyyy-mm-dd')");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos = query.list();
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			List<Object[]> accesos1 = null;
			query = session.createQuery("Select to_char(ua.loginDate,'yyyy-mm-dd'), Count(ua.id) as total " +
					"From UserAccess ua where (((ua.usuario.entidad.codigo =:silais)) AND ((ua.loginDate) Between :fechaInicio and :fechaFinal)) " +
					"group by to_char(ua.loginDate,'yyyy-mm-dd') order by to_char(ua.loginDate,'yyyy-mm-dd')");
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);			
			accesos1 = query.list();
			List<Object[]> accesos2 = null;
			query = session.createQuery("Select to_char(ua.loginDate,'yyyy-mm-dd'), Count(ua.id) as total " +
					"From UserAccess ua where (((ua.loginDate) Between :fechaInicio and :fechaFinal) AND ((ua.usuario.unidad.codigo) In (select codigo from Unidades unds where unds.entidadAdtva =:silais))) " +
					"group by to_char(ua.loginDate,'yyyy-mm-dd') order by to_char(ua.loginDate,'yyyy-mm-dd')");
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos2 = query.list();
			
			List<Object[]> accesos3 = new ArrayList<Object[]>();
			
			for (Object[] usSil: accesos1){ 
				for (Object[] usUnd: accesos2){
					if(usSil[0].toString().matches(usUnd[0].toString())){
						usSil[1] = Integer.valueOf(usSil[1].toString()) + Integer.valueOf(usUnd[1].toString());
					}
				}
				accesos3.add(usSil);
			}
			
			boolean noExiste = true;
			for (Object[] usUnd: accesos2){ 
				for (Object[] usUndEx: accesos3){
					if (usUnd[0].toString().matches(usUndEx[0].toString())){
						noExiste = false;
					}
				}
				 if (noExiste) accesos3.add(usUnd);
			}
			
			accesos.addAll(accesos3);

		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("Select to_char(ua.loginDate,'yyyy-mm-dd'), Count(ua.id) as total " +
					"From UserAccess ua where ua.loginDate between :fechaInicio and :fechaFinal and " +
					"(ua.usuario.unidad.codigo =:unidad or (ua.usuario.unidad.codigo in (Select codigo from Unidades where unidadAdtva =:unidad))) " +
					"group by to_char(ua.loginDate,'yyyy-mm-dd') order by to_char(ua.loginDate,'yyyy-mm-dd')");
			query.setParameter("unidad", usuario.getUnidad().getCodigo());
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			accesos = query.list();
		}
		// Retrieve all
		return  accesos;
	}
	
	
}
