package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.users.model.UserSistema;

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
	public List<EntidadesAdtvas> getEntidadesAdtvas(UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		// Create a Hibernate query (HQL)
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("FROM EntidadesAdtvas ea order by ea.nombre");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("FROM EntidadesAdtvas ea where ea.codigo = :codigo order by ea.nombre");
			query.setParameter("codigo", usuario.getEntidad().getCodigo());
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("FROM EntidadesAdtvas ea where ea.codigo = :codigo order by ea.nombre");
			query.setParameter("codigo", usuario.getUnidad().getEntidadAdtva());
		}
		// Retrieve all
		return  query.list();
	}
	
}
