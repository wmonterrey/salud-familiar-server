package ni.gob.minsa.hsf.service;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Persona;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personaService")
@Transactional
public class PersonaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	
	public void addPersona(Persona persona) {
		Session session = sessionFactory.getCurrentSession();
		session.save(persona);
	}
}
