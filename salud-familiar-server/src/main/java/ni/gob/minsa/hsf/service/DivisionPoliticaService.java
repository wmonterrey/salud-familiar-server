package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("divPoliticaService")
@Transactional
public class DivisionPoliticaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	public List<Divisionpolitica> getDivisionpoliticas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Divisionpolitica");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los municipios de un departamento
	 * 
	 * @return una lista de <code>Divisionpolitica</code>
	 */
	@SuppressWarnings("unchecked")
	public List<Divisionpolitica> getMunicipios(long entidadId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Divisionpolitica dp where dp.dependenciaSilais = :entidad order by dp.nombre");
		query.setParameter("entidad", entidadId);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los municipios de un usuario
	 * 
	 * @return una lista de <code>Divisionpolitica</code>
	 */
	@SuppressWarnings("unchecked")
	public List<Divisionpolitica> getMunicipios(long entidadId, UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("FROM  Divisionpolitica dp where dp.codigoNacional = :munUnidad order by dp.nombre");
			query.setParameter("munUnidad", usuario.getUnidad().getMunicipio());
		}
		else {
			query = session.createQuery("FROM  Divisionpolitica dp where dp.dependenciaSilais = :entidad order by dp.nombre");
			query.setParameter("entidad", entidadId);
		}
		// Retrieve all
		return  query.list();
	}
	

	public Divisionpolitica getDivisionpolitica(Integer divPoliticaId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Divisionpolitica dp where dp.divisionpoliticaId = "+ divPoliticaId);
		Divisionpolitica dp = (Divisionpolitica) query.uniqueResult();
		return dp;
	}
	
	public Divisionpolitica getDivisionpolitica(String codDivPolitica) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Divisionpolitica dp where dp.codigoNacional = :codDivPolitica");
		query.setParameter("codDivPolitica", codDivPolitica);
		Divisionpolitica dp = (Divisionpolitica) query.uniqueResult();
		return dp;
	}
	
}
