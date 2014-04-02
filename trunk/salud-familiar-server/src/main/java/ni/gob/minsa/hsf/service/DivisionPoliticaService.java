package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("divPoliticaService")
@Transactional
public class DivisionPoliticaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	public List<Divisionpolitica> getDivisionpoliticas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Divisionpolitica");
		// Retrieve all
		return  query.list();
	}
	

	public Divisionpolitica getDivisionpolitica(Integer divPoliticaId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Divisionpolitica dp where dp.divisionpoliticaId = "+ divPoliticaId);
		Divisionpolitica dp = (Divisionpolitica) query.uniqueResult();
		return dp;
	}
	
}
