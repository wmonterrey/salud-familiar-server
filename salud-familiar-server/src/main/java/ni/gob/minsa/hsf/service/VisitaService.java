package ni.gob.minsa.hsf.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<Visita> getVisitas(String comunidad, Integer numVivienda, Integer numFamilia, Integer numFicha, String fechaVisita) throws ParseException {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Date visitDate = null;
		String strQuery = "FROM Visita vis where vis.familia.comunidad.codigo = :codComunidad";
		if (numVivienda != null) strQuery = strQuery + " and vis.familia.numVivienda = :numVivienda";
		if (numFamilia != null) strQuery = strQuery + " and vis.familia.numFamilia = :numFamilia";
		if (numFicha != null) strQuery = strQuery + " and vis.numFicha = :numFicha";
		if (!fechaVisita.equals("")){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			visitDate = formatter.parse(fechaVisita);
			strQuery = strQuery + " and vis.fechaVisita = :fechaVisita";
		}
		Query query = session.createQuery(strQuery);
		query.setParameter("codComunidad", comunidad);
		if (numVivienda != null) query.setParameter("numVivienda", numVivienda);
		if (numFamilia != null) query.setParameter("numFamilia", numFamilia);
		if (numFicha != null) query.setParameter("numFicha", numFicha);
		if (!fechaVisita.equals("")) query.setDate("fechaVisita", visitDate);
		// Retrieve all
		return  query.list();
	}
	
	public Visita getVisita(String idVisita) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Visita v where v.idVisita = '"+ idVisita + "'");
		Visita visita = (Visita) query.uniqueResult();
		return visita;
	}
	
	public void addVisita(Visita visita) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(visita);
	}
}
