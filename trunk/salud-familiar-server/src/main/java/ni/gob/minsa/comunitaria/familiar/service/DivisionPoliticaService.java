package ni.gob.minsa.comunitaria.familiar.service;

import java.util.List;

import javax.annotation.Resource;



import ni.gob.minsa.comunitaria.familiar.domain.Divisionpolitica;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Division politica
 * 
 * @author William Aviles
 * 
 **/

@Service("divisionPoliticaService")
@Transactional
public class DivisionPoliticaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los deptos y municipios
	 * 
	 * @return una lista de <code>Divisionpolitica</code>
	 */
	@SuppressWarnings("unchecked")
	public List<Divisionpolitica> getDivisionPolitica() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Divisionpolitica");
		// Retrieve all
		return  query.list();
	}
	
}
