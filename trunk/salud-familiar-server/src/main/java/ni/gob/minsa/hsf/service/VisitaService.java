package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Visita;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("visitaService")
@Transactional
public class VisitaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita");
		// Retrieve all
		return  query.list();
	}
	
	public Visita getVisita(String idVisita) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Visita v where v.idVisita = '"+ idVisita + "'");
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public void addVisita(Visita visita) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(visita);
	}
}
