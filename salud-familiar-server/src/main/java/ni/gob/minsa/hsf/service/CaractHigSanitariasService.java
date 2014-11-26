package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.CaractHigSanitarias;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("caractHigSanitariasService")
@Transactional
public class CaractHigSanitariasService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CaractHigSanitarias> getCaractHigSanitarias() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CaractHigSanitarias");
		// Retrieve all
		return  query.list();
	}
	
	public CaractHigSanitarias getCaractHigSanitarias(String idCaractHig) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM CaractHigSanitarias chs where chs.idCaractHig = '"+ idCaractHig + "'");
		CaractHigSanitarias carHigSan = (CaractHigSanitarias) query.uniqueResult();
		return carHigSan;
	}
	
	public CaractHigSanitarias getCaractHigSanitariasFamilia(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM CaractHigSanitarias chs where chs.familia.idFamilia = :idFamilia and chs.pasive =:pasivo");
		query.setParameter("idFamilia", idFamilia);
		query.setParameter("pasivo", '0');
		CaractHigSanitarias carHigSan = (CaractHigSanitarias) query.uniqueResult();
		return carHigSan;
	}
	
	public void addCaractHigSanitarias(CaractHigSanitarias carHigSan) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(carHigSan);
	}
}
