package ni.gob.minsa.comunitaria.familiar.service;

import java.util.List;

import javax.annotation.Resource;



import ni.gob.minsa.comunitaria.familiar.domain.Comunidad;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Comunidad
 * 
 * @author William Aviles
 * 
 **/

@Service("comunidadService")
@Transactional
public class ComunidadService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todas las comunidades
	 * 
	 * @return una lista de <code>Comunidad</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Comunidad> getComunidades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidad c order by c.nombre");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa las comunidades de un sector
	 * 
	 * @return una lista de <code>Comunidad</code>(es)
	 */
	@SuppressWarnings("unchecked")
	public List<Comunidad> getComunidades(String sector) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidad c where c.sector = '"+ sector +"' order by c.nombre");
		// Retrieve all
		return query.list();
	}
	
}
