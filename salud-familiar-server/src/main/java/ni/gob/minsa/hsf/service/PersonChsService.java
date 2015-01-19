package ni.gob.minsa.hsf.service;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.CaractHigSanitarias;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.ws.PersonChs;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personChsService")
@Transactional
public class PersonChsService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public PersonChs getData(String cedula) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Persona persona where persona.cedula =:cedula");
		query.setParameter("cedula", cedula);
		Persona persona = (Persona) query.uniqueResult();
		if(persona == null){
			return null;
		}
		PersonChs personChs = new PersonChs();
		personChs.setPersona(persona);
		//CaractHigSanitarias
		query = session.createQuery("FROM CaractHigSanitarias chs where chs.familia.idFamilia =:idFamilia");
		query.setParameter("idFamilia", persona.getFamilia().getIdFamilia());
		CaractHigSanitarias chs = (CaractHigSanitarias) query.uniqueResult();
		personChs.setChs(chs);
		return personChs;
	}
	
}
