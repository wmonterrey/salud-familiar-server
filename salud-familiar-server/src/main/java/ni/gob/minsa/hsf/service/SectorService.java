package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;



import ni.gob.minsa.hsf.domain.Sector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Sector
 * 
 * @author William Aviles
 * 
 **/

@Service("sectorService")
@Transactional
public class SectorService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los sectores 
	 * 
	 * @return una lista de <code>Sectores</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Sector> getSectores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Sector s order by s.nombre");
		// Retrieve all
		return  query.list();
	}
	
	
	
	
	/**
	 * Regresa los sectores de un municipio
	 * 
	 * @return una lista de <code>Sector</code>(es)
	 */
	@SuppressWarnings("unchecked")
	public List<Sector> getSectores(String municipio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Sector s where s.municipio = '"+ municipio +"' order by s.nombre");
		// Retrieve all
		return query.list();
	}
	
}
