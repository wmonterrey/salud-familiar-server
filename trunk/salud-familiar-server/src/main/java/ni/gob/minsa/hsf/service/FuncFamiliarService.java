package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.FuncFamiliar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("funcFamiliarService")
@Transactional
public class FuncFamiliarService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<FuncFamiliar> getFuncFamiliar() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FuncFamiliar");
		// Retrieve all
		return  query.list();
	}
	
	public FuncFamiliar getFuncFamiliar(String idFuncFamiliar) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FuncFamiliar fm where fm.idFuncFamiliar = '"+ idFuncFamiliar + "'");
		FuncFamiliar funcFam = (FuncFamiliar) query.uniqueResult();
		return funcFam;
	}
	
	public void addFuncFamiliar(FuncFamiliar funcFam) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(funcFam);
	}
}
