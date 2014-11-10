package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("unidadesService")
@Transactional
public class UnidadesService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Unidades> getUnidades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Unidades where (unds.tipoUnidad = 2 " +
				"or unds.tipoUnidad = 3 or unds.tipoUnidad = 4");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidades> getUnidadesEntidad(long entidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Unidades unds where unds.entidadAdtva = " + entidad + " and (unds.tipoUnidad = 2 " +
				"or unds.tipoUnidad = 3 or unds.tipoUnidad = 4) order by unds.nombre");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidades> getUnidadesMunicipio(String municipio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Unidades unds where unds.municipio = " + municipio + " and (unds.tipoUnidad = 2 " +
				"or unds.tipoUnidad = 3 or unds.tipoUnidad = 4) order by unds.nombre");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidades> getUnidadesMunicipio(String municipio, UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("FROM Unidades unds " +
					"where unds.codigo = " + usuario.getUnidad().getCodigo() + " or unds.unidadAdtva = " + usuario.getUnidad().getCodigo() + " and (unds.tipoUnidad = 2 " +
				"or unds.tipoUnidad = 3 or unds.tipoUnidad = 4) order by unds.nombre");
		}
		else {
			query = session.createQuery("FROM Unidades unds where unds.municipio = " + municipio + " and (unds.tipoUnidad = 2 " +
				"or unds.tipoUnidad = 3 or unds.tipoUnidad = 4) order by unds.nombre");
		}
		// Retrieve all
		return  query.list();
	}
	
	public Unidades getUnidades(long codigo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Unidades und where und.codigo = "+ codigo);
		Unidades unidad = (Unidades) query.uniqueResult();
		return unidad;
	}
	
}
