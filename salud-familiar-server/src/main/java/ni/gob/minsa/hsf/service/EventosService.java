package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Eventos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventosService")
@Transactional
public class EventosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Eventos> getEventos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Eventos");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Eventos> getEventosPersona(String idPersona) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Eventos eve where eve.persona.idPersona = :idPersona and eve.pasive = :pasivo");
		query.setParameter("idPersona", idPersona);
		query.setParameter("pasivo", '0');
		// Retrieve all
		return  query.list();
	}
	
	public Eventos getEvento(String idEvento) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Eventos eve where eve.idEvento = '"+ idEvento + "'");
		Eventos evento = (Eventos) query.uniqueResult();
		return evento;
	}
	
	public void addEventos(Eventos evento) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(evento);
	}
}
