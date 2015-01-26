package ni.gob.minsa.hsf.service.report;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.report.FamEstDivPol;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("famEstDivPolService")
@Transactional
public class FamEstDivPolService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FamEstDivPol> getFamEstDivPol(UserSistema usuario, long entidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL") || usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			Query query = session.createQuery("From FamEstDivPol fe where " +
					"fe.municipio.dependenciaSilais = "+ entidad);
			return  query.list();
		}
		else {
			return null;
		}
	}
	
	public FamEstDivPol getFamEstDivPol(String famEstDivPolId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FamEstDivPol est where est.famEstDivPolId =:famEstDivPolId");
		query.setParameter("famEstDivPolId", famEstDivPolId);
		FamEstDivPol estimadas = (FamEstDivPol) query.uniqueResult();
		return estimadas;
	}
	
	public void addFamEstDivPol(FamEstDivPol estimadas) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(estimadas);
	}
	
	public Integer delFamEstDivPol(String famEstDivPolId) {
		// Retrieve session from Hibernate
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		String hqlDelete = "delete FamEstDivPol fe where fe.famEstDivPolId = :famEstDivPolId";
		int deletedEntities = s.createQuery( hqlDelete )
		        .setString( "famEstDivPolId", famEstDivPolId )
		        .executeUpdate();
		tx.commit();
		s.close();
		return deletedEntities;
	}
}
