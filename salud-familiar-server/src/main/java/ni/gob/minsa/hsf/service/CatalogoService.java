package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("catalogoService")
@Transactional
public class CatalogoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Catalogo> getCatalogos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Catalogo");
		// Retrieve all
		return  query.list();
	}

	public Sexo getSexo(String sexo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerSexoPorCodigo").setString("pCodigo", sexo);
		// Retrieve all
		return  (Sexo) query.uniqueResult();
	}
	
	public Etnia getEtnia(String etnia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerEtniaPorCodigo").setString("pCodigo", etnia);
		// Retrieve all
		return  (Etnia) query.uniqueResult();
	}
	
	public Escolaridad getEscda(String escda) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerEscolaridadPorCodigo").setString("pCodigo", escda);
		// Retrieve all
		return  (Escolaridad) query.uniqueResult();
	}
	
	public Ocupacion getOcupacion(String ocupacion) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerOcupacionPorCodigo").setString("pCodigo", ocupacion);
		// Retrieve all
		return  (Ocupacion) query.uniqueResult();
	}
	
	public Religion getReligion(String religion) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerReligionPorCodigo").setString("pCodigo", religion);
		// Retrieve all
		return  (Religion) query.uniqueResult();
	}
	
	public GrupoDispensarial getGrupoDispensarial(String gd) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerGDPorCodigo").setString("pCodigo", gd);
		// Retrieve all
		return  (GrupoDispensarial) query.uniqueResult();
	}
	
}
