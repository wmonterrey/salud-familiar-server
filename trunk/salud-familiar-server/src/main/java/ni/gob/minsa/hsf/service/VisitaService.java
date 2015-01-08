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
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita v where v.familia.idFamilia = :idFamilia order by v.fechaVisita");
		query.setParameter("idFamilia", idFamilia);
		// Retrieve all
		return  query.list();
	}
		
	public Visita getUltimaVisita(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita v where v.familia.idFamilia = :idFamilia order by v.fechaVisita DESC");
		query.setParameter("idFamilia", idFamilia);
		query.setMaxResults(1);
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public Visita getVisita(String idVisita) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Visita v where v.idVisita = :idVisita");
		query.setParameter("idVisita", idVisita);
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public void addVisita(Visita visita) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(visita);
	}
}
