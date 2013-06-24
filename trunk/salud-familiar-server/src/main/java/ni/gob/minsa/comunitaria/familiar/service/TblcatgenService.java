package ni.gob.minsa.comunitaria.familiar.service;

import java.util.List;

import javax.annotation.Resource;



import ni.gob.minsa.comunitaria.familiar.domain.Tblcatgen;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Tblcatgen
 * 
 * @author William Aviles
 * 
 **/

@Service("tblcatenService")
@Transactional
public class TblcatgenService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los registros de CatGen 
	 * 
	 * @return una lista de <code>CatGen</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Tblcatgen> getCatGens() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Tblcatgen cat order by cat.nombre");
		// Retrieve all
		return  query.list();
	}
	
}
