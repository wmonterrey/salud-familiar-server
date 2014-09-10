package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.AbastecimientoAgua;
import ni.gob.minsa.hsf.domain.catalogos.AccionesComunitarias;
import ni.gob.minsa.hsf.domain.catalogos.AnimalesDomesticos;
import ni.gob.minsa.hsf.domain.catalogos.CalidadAgua;
import ni.gob.minsa.hsf.domain.catalogos.CarPsicosociales;
import ni.gob.minsa.hsf.domain.catalogos.CombCocinar;
import ni.gob.minsa.hsf.domain.catalogos.CrisisNormativa;
import ni.gob.minsa.hsf.domain.catalogos.CrisisParanormativa;
import ni.gob.minsa.hsf.domain.catalogos.CulturaSanitaria;
import ni.gob.minsa.hsf.domain.catalogos.DepBasura;
import ni.gob.minsa.hsf.domain.catalogos.DepExcretas;
import ni.gob.minsa.hsf.domain.catalogos.DepResLiq;
import ni.gob.minsa.hsf.domain.catalogos.Discapacidad;
import ni.gob.minsa.hsf.domain.catalogos.Electricidad;
import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.EtapaCicloVital;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoNoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoSoc;
import ni.gob.minsa.hsf.domain.catalogos.FactoresMedAmb;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Ontogenesis;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoBiologico;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoMeteorologico;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoNatural;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoSocial;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.catalogos.SiNoNs;
import ni.gob.minsa.hsf.domain.catalogos.TamanoFam;
import ni.gob.minsa.hsf.domain.catalogos.TenenciaVivienda;
import ni.gob.minsa.hsf.domain.catalogos.TipoPared;
import ni.gob.minsa.hsf.domain.catalogos.TipoPiso;
import ni.gob.minsa.hsf.domain.catalogos.TipoTecho;
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
	
	public Nivel getNivel(String nivel) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerNivelPorCodigo").setString("pCodigo", nivel);
		// Retrieve all
		return  (Nivel) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Nivel> getNiveles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Nivel where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
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
	
	@SuppressWarnings("unchecked")
	public List<Sexo> getSexo() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Sexo where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Etnia getEtnia(String etnia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerEtniaPorCodigo").setString("pCodigo", etnia);
		// Retrieve all
		return  (Etnia) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Etnia> getEtnia() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Etnia where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Escolaridad getEscda(String escda) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerEscolaridadPorCodigo").setString("pCodigo", escda);
		// Retrieve all
		return  (Escolaridad) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Escolaridad> getEscda() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Escolaridad where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Ocupacion getOcupacion(String ocupacion) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerOcupacionPorCodigo").setString("pCodigo", ocupacion);
		// Retrieve all
		return  (Ocupacion) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ocupacion> getOcupacion() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Ocupacion where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Religion getReligion(String religion) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerReligionPorCodigo").setString("pCodigo", religion);
		// Retrieve all
		return  (Religion) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Religion> getReligion() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Religion where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public GrupoDispensarial getGrupoDispensarial(String gd) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerGDPorCodigo").setString("pCodigo", gd);
		// Retrieve all
		return  (GrupoDispensarial) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoDispensarial> getGrupoDispensarial() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM GrupoDispensarial where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Profesion getProfesion(String profesion) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerProfesionPorCodigo").setString("pCodigo", profesion);
		// Retrieve all
		return  (Profesion) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Profesion> getProfesiones() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Profesion where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SiNoNs> getSiNoNs() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM SiNoNs where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FactRiesgoMod> getFactRiesgoMod() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FactRiesgoMod where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FactRiesgoNoMod> getFactRiesgoNoMod() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FactRiesgoNoMod where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FactRiesgoSoc> getFactRiesgoSoc() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FactRiesgoSoc where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Discapacidad> getDiscapacidad() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Discapacidad where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AnimalesDomesticos> getAnimalesDomesticos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM AnimalesDomesticos where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RiesgoNatural> getRiesgoNatural() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RiesgoNatural where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RiesgoMeteorologico> getRiesgoMeteorologico() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RiesgoMeteorologico where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RiesgoBiologico> getRiesgoBiologico() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RiesgoBiologico where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RiesgoSocial> getRiesgoSocial() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RiesgoSocial where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FactoresMedAmb> getFactoresMedAmb() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM FactoresMedAmb where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CombCocinar> getCombCocinar() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CombCocinar where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public AbastecimientoAgua getAbastecimientoAgua(String aagua) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerAbastecimientoAguaPorCodigo").setString("pCodigo", aagua);
		// Retrieve all
		return  (AbastecimientoAgua) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<AbastecimientoAgua> getAbastecimientoAgua() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM AbastecimientoAgua where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public CalidadAgua getCalidadAgua(String caagua) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerCalidadAguaPorCodigo").setString("pCodigo", caagua);
		// Retrieve all
		return  (CalidadAgua) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CalidadAgua> getCalidadAgua() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CalidadAgua where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Electricidad getElectricidad(String electricidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerElectricidadPorCodigo").setString("pCodigo", electricidad);
		// Retrieve all
		return  (Electricidad) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Electricidad> getElectricidad() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Electricidad where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public DepExcretas getDepExcretas(String depExcretas) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerDepExcretasPorCodigo").setString("pCodigo", depExcretas);
		// Retrieve all
		return  (DepExcretas) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<DepExcretas> getDepExcretas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM DepExcretas where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public DepBasura getDepBasura(String depBasura) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerDepBasuraPorCodigo").setString("pCodigo", depBasura);
		// Retrieve all
		return  (DepBasura) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<DepBasura> getDepBasura() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM DepBasura where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	
	public DepResLiq getDepResLiq(String depResLiq) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerDepBasuraPorCodigo").setString("pCodigo", depResLiq);
		// Retrieve all
		return  (DepResLiq) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<DepResLiq> getDepResLiq() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM DepResLiq where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public TipoPiso getTipoPiso(String tipo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerTipoPisoPorCodigo").setString("pCodigo", tipo);
		// Retrieve all
		return  (TipoPiso) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoPiso> getTipoPiso() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoPiso where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public TipoTecho getTipoTecho(String tipo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerTipoTechoPorCodigo").setString("pCodigo", tipo);
		// Retrieve all
		return  (TipoTecho) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoTecho> getTipoTecho() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoTecho where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public TipoPared getTipoPared(String tipo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerTipoParedPorCodigo").setString("pCodigo", tipo);
		// Retrieve all
		return  (TipoPared) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoPared> getTipoPared() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoPared where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public CulturaSanitaria getCulturaSanitaria(String cultura) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerCulturaSanitariaPorCodigo").setString("pCodigo", cultura);
		// Retrieve all
		return  (CulturaSanitaria) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CulturaSanitaria> getCulturaSanitaria() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CulturaSanitaria where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public CarPsicosociales getCarPsicosociales(String car) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerCarPsicosocialesPorCodigo").setString("pCodigo", car);
		// Retrieve all
		return  (CarPsicosociales) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CarPsicosociales> getCarPsicosociales() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CarPsicosociales where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public TenenciaVivienda getTenenciaVivienda(String tenencia) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerTenenciaViviendaPorCodigo").setString("pCodigo", tenencia);
		// Retrieve all
		return  (TenenciaVivienda) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TenenciaVivienda> getTenenciaVivienda() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TenenciaVivienda where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AccionesComunitarias> getAccionesComunitarias() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM AccionesComunitarias where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public TamanoFam getTamanoFam(String tamano) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerTamanoFamPorCodigo").setString("pCodigo", tamano);
		// Retrieve all
		return  (TamanoFam) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TamanoFam> getTamanoFam() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TamanoFam where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public Ontogenesis getOntogenesis(String onto) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerOntogenesisPorCodigo").setString("pCodigo", onto);
		// Retrieve all
		return  (Ontogenesis) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ontogenesis> getOntogenesis() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Ontogenesis where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	public EtapaCicloVital getEtapaCicloVital(String etapa) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.getNamedQuery("obtenerEtapaCicloVitalPorCodigo").setString("pCodigo", etapa);
		// Retrieve all
		return  (EtapaCicloVital) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<EtapaCicloVital> getEtapaCicloVital() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EtapaCicloVital where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CrisisNormativa> getCrisisNormativa() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CrisisNormativa where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CrisisParanormativa> getCrisisParanormativa() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM CrisisParanormativa where pasivo = :pasivo order by orden");
		query.setParameter("pasivo", false);
		// Retrieve all
		return  query.list();
	}
	
}
