package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.poblacion.Comunidades;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("comunidadService")
@Transactional
public class ComunidadesService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Comunidades> getComunidades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidades c order by c.nombre");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comunidades> getComunidadesSector(String sector) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidades c where c.sector.codigo = :sector order by c.nombre");
		query.setParameter("sector", sector);
		// Retrieve all
		return  query.list();
	}
	
	public Comunidades getComunidad(String codComunidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Comunidades c where c.codigo = '"+ codComunidad + "'");
		Comunidades com = (Comunidades) query.uniqueResult();
		return com;
	}
	
}
