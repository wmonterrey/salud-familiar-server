package ni.gob.minsa.hsf.service;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Familia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("familiaService")
@Transactional
public class FamiliaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Familia> getFamilias() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Familia");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Familia> getFamilias(String comunidad, Integer numVivienda, Integer numFamilia, Integer numFicha) throws ParseException {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		String strQuery = "FROM Familia fam where fam.comunidad.codigo = :codComunidad";
		if (numVivienda != null) strQuery = strQuery + " and fam.numVivienda = :numVivienda";
		if (numFamilia != null) strQuery = strQuery + " and fam.numFamilia = :numFamilia";
		if (numFicha != null) strQuery = strQuery + " and fam.numFicha = :numFicha";
		Query query = session.createQuery(strQuery);
		query.setParameter("codComunidad", comunidad);
		if (numVivienda != null) query.setParameter("numVivienda", numVivienda);
		if (numFamilia != null) query.setParameter("numFamilia", numFamilia);
		if (numFicha != null) query.setParameter("numFicha", numFicha);
		// Retrieve all
		return  query.list();
	}
	
	public Familia getFamilia(String idFamilia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Familia f where f.idFamilia = '"+ idFamilia + "'");
		Familia familia = (Familia) query.uniqueResult();
		return familia;
	}
	
	public void addFamilia(Familia familia) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(familia);
	}
}
