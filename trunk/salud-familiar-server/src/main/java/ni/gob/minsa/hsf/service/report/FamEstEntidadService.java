package ni.gob.minsa.hsf.service.report;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.report.FamEstEntidad;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("famEstEntidadService")
@Transactional
public class FamEstEntidadService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FamEstEntidad> getFamEstEntidad(UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("From FamEstEntidad");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("From FamEstEntidad fe where " +
					"fe.entidad.codigo = "+ usuario.getEntidad().getCodigo());
		}
		else {
			return null;
		}
		// Retrieve all
		return  query.list();
	}
	
	public FamEstEntidad getFamEstEntidad(long codEntidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FamEstEntidad est where est.entidad.codigo =:codEntidad");
		query.setParameter("codEntidad", codEntidad);
		FamEstEntidad estimadas = (FamEstEntidad) query.uniqueResult();
		return estimadas;
	}
	
	public FamEstEntidad getFamEstEntidad(String famEstEntidadId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FamEstEntidad est where est.famEstEntidadId =:famEstEntidadId");
		query.setParameter("famEstEntidadId", famEstEntidadId);
		FamEstEntidad estimadas = (FamEstEntidad) query.uniqueResult();
		return estimadas;
	}
	
	public void addFFamEstEntidad(FamEstEntidad estimadas) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(estimadas);
	}
	
	public Integer delFamEstEntidad(String famEstEntidadId) {
		// Retrieve session from Hibernate
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		String hqlDelete = "delete FamEstEntidad fe where fe.famEstEntidadId = :famEstEntidadId";
		int deletedEntities = s.createQuery( hqlDelete )
		        .setString( "famEstEntidadId", famEstEntidadId )
		        .executeUpdate();
		tx.commit();
		s.close();
		return deletedEntities;
	}
}
