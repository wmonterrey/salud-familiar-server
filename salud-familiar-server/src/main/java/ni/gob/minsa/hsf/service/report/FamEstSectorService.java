package ni.gob.minsa.hsf.service.report;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.report.FamEstSector;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("famEstSectorService")
@Transactional
public class FamEstSectorService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FamEstSector> getFamEstSector(UserSistema usuario, String municipio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From FamEstSector fe where " +
					"fe.sector.municipio.codigoNacional =:codMunicipio");
		query.setParameter("codMunicipio", municipio);
		return  query.list();
	}
	
	public FamEstSector getFamEstSector(String famEstSectorId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FamEstSector est where est.famEstSectorId =:famEstSectorId");
		query.setParameter("famEstSectorId", famEstSectorId);
		FamEstSector estimadas = (FamEstSector) query.uniqueResult();
		return estimadas;
	}
	
	public void addFamEstSector(FamEstSector estimadas) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(estimadas);
	}
	
	public Integer delFamEstSector(String famEstSectorId) {
		// Retrieve session from Hibernate
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		String hqlDelete = "delete FamEstSector fe where fe.famEstSectorId = :famEstSectorId";
		int deletedEntities = s.createQuery( hqlDelete )
		        .setString( "famEstSectorId", famEstSectorId )
		        .executeUpdate();
		tx.commit();
		s.close();
		return deletedEntities;
	}
}
