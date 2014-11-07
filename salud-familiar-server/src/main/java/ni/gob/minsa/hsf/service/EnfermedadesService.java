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
	
	@SuppressWarnings("unchecked")
	public List<Enfermedades> getEnfermedadesPersona(String idPersona) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Enfermedades enf where enf.persona.idPersona = :idPersona and enf.pasive = :pasivo");
		query.setParameter("idPersona", idPersona);
		query.setParameter("pasivo", '0');
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
	
	public boolean quitarEnfermedad(String idEnfermedad) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Query query = session.createQuery("update Enfermedades set pasive = :pasivo" +
				" where idEnfermedad = :idEnfermedad");
			query.setParameter("pasivo", '1');
			query.setParameter("idEnfermedad", idEnfermedad);
			query.executeUpdate();
		}
		catch (Exception e){
			return false;
		}
		return true;
	}
	
	public void addEnfermedades(Enfermedades enfermedad) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(enfermedad);
	}
}
