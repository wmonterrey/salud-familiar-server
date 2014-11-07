package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.estructura.Unidades;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("entidadAdtvaService")
@Transactional
public class EntidadesAdtvasService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<EntidadesAdtvas> getEntidadesAdtvas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EntidadesAdtvas ea order by ea.nombre");
		// Retrieve all
		return  query.list();
	}
	
	public EntidadesAdtvas getEntidadesAdtvas(long codigo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM EntidadesAdtvas ea where ea.codigo = "+ codigo);
		EntidadesAdtvas ea = (EntidadesAdtvas) query.uniqueResult();
		return ea;
	}
	
	@SuppressWarnings("unchecked")
	public List<EntidadesAdtvas> getEntidadesAdtvas(Nivel nivel, EntidadesAdtvas entidad, Unidades unidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		// Create a Hibernate query (HQL)
		if (nivel.getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("FROM EntidadesAdtvas ea order by ea.nombre");
		}
		else if (nivel.getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("FROM EntidadesAdtvas ea where ea.codigo = :codigo order by ea.nombre");
			query.setParameter("codigo", entidad.getCodigo());
		}
		else if (nivel.getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("FROM EntidadesAdtvas ea where ea.codigo = :codigo order by ea.nombre");
			query.setParameter("codigo", unidad.getEntidadAdtva());
		}
		// Retrieve all
		return  query.list();
	}
	
}
