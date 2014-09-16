package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.estructura.Cie10;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cie10Service")
@Transactional
public class Cie10Service {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Cie10> getCie10() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Cie10 where activo = :activo order by codigoCie10");
		query.setParameter("activo", true);
		// Retrieve all
		return  query.list();
	}
	
	public Cie10 getCie10(String codigoCie10) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Cie10 cie10 where cie10.codigoCie10 = '"+ codigoCie10 + "'");
		Cie10 cie10 = (Cie10) query.uniqueResult();
		return cie10;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cie10> getCie10Filtered(String filtro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Cie10 where ((activo = :activo) and (lower(codigoCie10) like :codigo or lower(nombreCie10) like :nombre)) order by codigoCie10");
		query.setParameter("activo", true);
		query.setParameter("codigo", "%" +filtro.toLowerCase() + "%");
		query.setParameter("nombre", "%" +filtro.toLowerCase() + "%");
		// Retrieve all
		return  query.list();
	}

}
