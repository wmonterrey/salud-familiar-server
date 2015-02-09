package ni.gob.minsa.hsf.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Visita;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("visitaService")
@Transactional
public class VisitaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas(String comunidad, Integer numVivienda, Integer numFamilia, Integer numFicha, Date fecha1, Date fecha2) throws ParseException {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		String strQuery = "FROM Visita vis where vis.familia.comunidad.codigo = :codComunidad";
		if (numVivienda != null) strQuery = strQuery + " and vis.familia.numVivienda = :numVivienda";
		if (numFamilia != null) strQuery = strQuery + " and vis.familia.numFamilia = :numFamilia";
		if (numFicha != null) strQuery = strQuery + " and vis.familia.numFicha = :numFicha";
		if (fecha1 != null) strQuery = strQuery + " and vis.fechaVisita >= :fec1";
		if (fecha2 != null) strQuery = strQuery + " and vis.fechaVisita <= :fec2";
		strQuery = strQuery + " order by vis.fechaVisita desc, vis.familia.comunidad.codigo, vis.familia.numVivienda, vis.familia.numFamilia";
		Query query = session.createQuery(strQuery);
		query.setParameter("codComunidad", comunidad);
		if (numVivienda != null) query.setParameter("numVivienda", numVivienda);
		if (numFamilia != null) query.setParameter("numFamilia", numFamilia);
		if (numFicha != null) query.setParameter("numFicha", numFicha);
		if (fecha1 != null) query.setTimestamp("fec1", fecha1);
		if (fecha2 != null) query.setTimestamp("fec2", fecha2);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitasFamActivas(String comunidad, Integer numVivienda, Integer numFamilia, Integer numFicha, Date fecha1, Date fecha2) throws ParseException {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		String strQuery = "FROM Visita vis where vis.familia.comunidad.codigo = :codComunidad";
		if (numVivienda != null) strQuery = strQuery + " and vis.familia.numVivienda = :numVivienda";
		if (numFamilia != null) strQuery = strQuery + " and vis.familia.numFamilia = :numFamilia";
		if (numFicha != null) strQuery = strQuery + " and vis.familia.numFicha = :numFicha";
		if (fecha1 != null) strQuery = strQuery + " and vis.fechaVisita >= :fec1";
		if (fecha2 != null) strQuery = strQuery + " and vis.fechaVisita <= :fec2";
		strQuery = strQuery + " and vis.familia.pasive =:pasivo order by vis.fechaVisita desc, vis.familia.comunidad.codigo, vis.familia.numVivienda, vis.familia.numFamilia";
		Query query = session.createQuery(strQuery);
		query.setParameter("codComunidad", comunidad);
		query.setParameter("pasivo", '0');
		if (numVivienda != null) query.setParameter("numVivienda", numVivienda);
		if (numFamilia != null) query.setParameter("numFamilia", numFamilia);
		if (numFicha != null) query.setParameter("numFicha", numFicha);
		if (fecha1 != null) query.setTimestamp("fec1", fecha1);
		if (fecha2 != null) query.setTimestamp("fec2", fecha2);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita v where v.familia.idFamilia = :idFamilia order by v.fechaVisita");
		query.setParameter("idFamilia", idFamilia);
		// Retrieve all
		return  query.list();
	}
		
	public Visita getUltimaVisita(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Visita v where v.familia.idFamilia = :idFamilia order by v.fechaVisita DESC");
		query.setParameter("idFamilia", idFamilia);
		query.setMaxResults(1);
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public Visita getVisita(String idVisita) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Visita v where v.idVisita = :idVisita");
		query.setParameter("idVisita", idVisita);
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public void addVisita(Visita visita) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(visita);
	}
}
