package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Persona;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personaService")
@Transactional
public class PersonaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Persona");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Persona per where per.familia.idFamilia = :idFamilia order by per.numPersona");
		query.setParameter("idFamilia", idFamilia);
		// Retrieve all
		return  query.list();
	}
	
	public Persona getPersona(String idPersona) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Persona p where p.idPersona = '"+ idPersona + "'");
		Persona persona = (Persona) query.uniqueResult();
		return persona;
	}
	
	public Integer getCodePersona(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT max(numPersona) FROM Persona per where per.familia.idFamilia = :idFamilia group by per.familia.idFamilia");
		query.setParameter("idFamilia", idFamilia);
		Integer numMax = (Integer) query.uniqueResult();
		if (numMax==null){
			numMax=1;
		}
		else{
			numMax=numMax+1;
		}
		return numMax;
	}
	
	public void addPersona(Persona persona) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(persona);
	}
}
