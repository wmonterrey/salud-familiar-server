package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.users.model.UserSistema;

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
	public List<Sectores> getSectoresMunicipio(String municipio,UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("from Sectores as sect where sect.unidad in (select u.codigo " +
					"from Unidades as u where (u.codigo = :unidadUsuario or u.unidadAdtva =:undadtvaUsuario))");
			query.setParameter("unidadUsuario", usuario.getUnidad().getCodigo());
			query.setParameter("undadtvaUsuario", usuario.getUnidad().getCodigo());
		}
		else{
			query = session.createQuery("from Sectores secs where secs.municipio.codigoNacional = :municipio order by secs.nombre");
			query.setParameter("municipio", municipio);
		}
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
	
	public Sectores getSector(String codSector) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Sectores s where s.codigo = :codSector");
		query.setParameter("codSector",codSector);
		Sectores sector = (Sectores) query.uniqueResult();
		return sector;
	}
	
}
