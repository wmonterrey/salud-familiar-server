package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.users.model.UserAccess;
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
	public List<UserAccess> getUserAccess(UserSistema usuario) {
		List<UserAccess> accesos = null;
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("From UserAccess");
			accesos = query.list();
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("From UserAccess us where " +
					"us.usuario.entidad.codigo = "+ usuario.getEntidad().getCodigo());
			accesos = query.list();
			
			List<UserAccess> usersEntidad = session.createQuery(
					"From UserAccess us where " +
							"us.usuario.unidad.codigo in (Select codigo from Unidades where entidadAdtva = "+ usuario.getEntidad().getCodigo() +")")
		            .list();
			accesos.addAll(usersEntidad);
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("From UserAccess us where " +
					"us.usuario.unidad.codigo = "+ usuario.getUnidad().getCodigo() +" or (us.usuario.unidad.codigo in (Select codigo from Unidades where unidadAdtva = "+ usuario.getUnidad().getCodigo() +"))");
			accesos = query.list();
		}
		// Retrieve all
		return  accesos;
	}
	
	
}
