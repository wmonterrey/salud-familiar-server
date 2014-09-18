package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.EnfermedadesSocioCult;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("enfermedadesSocioCultService")
@Transactional
public class EnfermedadesSocioCultService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<EnfermedadesSocioCult> getEnfermedadesSocioCult() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EnfermedadesSocioCult");
		// Retrieve all
		return  query.list();
	}
	
	public EnfermedadesSocioCult getEnfermedadesSocioCult(String idEnfermedad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM EnfermedadesSocioCult enf where enf.idEnfSocioC = '"+ idEnfermedad + "'");
		EnfermedadesSocioCult enfermedad = (EnfermedadesSocioCult) query.uniqueResult();
		return enfermedad;
	}
	
	public void addEnfermedadesSocioCult(EnfermedadesSocioCult enfermedad) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(enfermedad);
	}
}
