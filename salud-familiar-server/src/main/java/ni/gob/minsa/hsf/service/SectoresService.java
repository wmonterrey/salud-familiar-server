package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.poblacion.Sectores;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sectorService")
@Transactional
public class SectoresService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Sectores> getSectores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Sectores");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sectores> getSectoresUnidad(long unidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("from Sectores secs where secs.unidad in (select unidad.codigo " +
				"from Unidades as unidad where unidad.codigo = " + unidad + " or unidad.unidadAdtva = "+ unidad +")");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sectores> getSectoresMunicipio(String municipio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("from Sectores secs where secs.municipio = "+ municipio +" order by secs.nombre");
		// Retrieve all
		return  query.list();
	}
	
	public Sectores getSector(Integer sectorId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Sectores s where s.sectorId = "+ sectorId);
		Sectores sector = (Sectores) query.uniqueResult();
		return sector;
	}
	
}
