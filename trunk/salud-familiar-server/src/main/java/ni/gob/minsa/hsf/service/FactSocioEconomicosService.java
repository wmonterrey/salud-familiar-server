package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.FactSocioEconomicos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("factSocioEconomicosService")
@Transactional
public class FactSocioEconomicosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FactSocioEconomicos> getFactSocioEconomicos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FactSocioEconomicos");
		// Retrieve all
		return  query.list();
	}
	
	public FactSocioEconomicos getFactSocioEconomicos(String idFactSocioEc) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FactSocioEconomicos fse where fse.idFactSocioEc = '"+ idFactSocioEc + "'");
		FactSocioEconomicos factSocEc = (FactSocioEconomicos) query.uniqueResult();
		return factSocEc;
	}
	
	public FactSocioEconomicos getVisitaFactSocioEconomicos(String idVisita) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FactSocioEconomicos fse where fse.visita.idVisita = :idVisita");
		query.setParameter("idVisita", idVisita);
		FactSocioEconomicos factSocEc = (FactSocioEconomicos) query.uniqueResult();
		return factSocEc;
	}
	
	public void addFactSocioEconomicos(FactSocioEconomicos factSocEc) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(factSocEc);
	}
}
