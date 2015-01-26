package ni.gob.minsa.hsf.service.report;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.report.FamEstComunidad;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("famEstComunidadService")
@Transactional
public class FamEstComunidadService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FamEstComunidad> getFamEstComunidad(UserSistema usuario, String sector) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From FamEstComunidad fe where " +
					"fe.comunidad.sector.codigo =:codSector");
		query.setParameter("codSector", sector);
		return  query.list();
	}
	
	public FamEstComunidad getFamEstComunidad(String famEstComuId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FamEstComunidad est where est.famEstComuId =:famEstComuId");
		query.setParameter("famEstComuId", famEstComuId);
		FamEstComunidad estimadas = (FamEstComunidad) query.uniqueResult();
		return estimadas;
	}
	
	public void addFamEstComunidad(FamEstComunidad estimadas) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(estimadas);
	}
	
	public Integer delFamEstComunidad(String famEstComuId) {
		// Retrieve session from Hibernate
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		String hqlDelete = "delete FamEstComunidad fe where fe.famEstComuId = :famEstComuId";
		int deletedEntities = s.createQuery( hqlDelete )
		        .setString( "famEstComuId", famEstComuId )
		        .executeUpdate();
		tx.commit();
		s.close();
		return deletedEntities;
	}
}
