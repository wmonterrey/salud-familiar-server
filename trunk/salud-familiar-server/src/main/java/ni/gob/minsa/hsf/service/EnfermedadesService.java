package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Enfermedades;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("enfermedadesService")
@Transactional
public class EnfermedadesService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Enfermedades> getEnfermedades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Enfermedades");
		// Retrieve all
		return  query.list();
	}
	
	public Enfermedades getEnfermedades(String idEnfermedad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Enfermedades enf where enf.idEnfermedad = '"+ idEnfermedad + "'");
		Enfermedades enfermedad = (Enfermedades) query.uniqueResult();
		return enfermedad;
	}
	
	public void addEnfermedades(Enfermedades enfermedad) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(enfermedad);
	}
}
