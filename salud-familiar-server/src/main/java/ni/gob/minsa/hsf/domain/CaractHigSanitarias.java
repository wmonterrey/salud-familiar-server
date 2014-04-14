package ni.gob.minsa.hsf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.catalogos.AbastecimientoAgua;
import ni.gob.minsa.hsf.domain.catalogos.CalidadAgua;
import ni.gob.minsa.hsf.domain.catalogos.DepBasura;
import ni.gob.minsa.hsf.domain.catalogos.DepExcretas;
import ni.gob.minsa.hsf.domain.catalogos.DepResLiq;
import ni.gob.minsa.hsf.domain.catalogos.Electricidad;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_CARACT_HIG", catalog = "HSF")
public class CaractHigSanitarias {
	
	
	private String idCaractHig;
	private Visita visita;
    private int numPersonas;
    private int numCuartos;
    private String hacinamiento;
    private String animalesDom;
    private String riesgoNatural;
    private String riesgoMeteorologico;
    private String riesgoBiologico;
    private String riesgoSocial;
    private String factoresMedAmb;
    private String combCocinar;
    private AbastecimientoAgua aAgua;
    private CalidadAgua cAgua;
    private Electricidad electricidad;
    private DepExcretas depExcretas;
    private DepBasura depBasura;
    private DepResLiq depResLiq;
    private String observaciones;
	private MovilInfo movilInfo;
    
    
    
	public CaractHigSanitarias() {
		
	}

	@Id
	@Column(name = "ID_CARACTHIG", nullable = false, length = 50)
	public String getIdCaractHig() {
		return idCaractHig;
	}


	public void setIdCaractHig(String idCaractHig) {
		this.idCaractHig = idCaractHig;
	}

	@OneToOne(optional=false)
	@JoinColumn(name="ID_VISITA")
	@ForeignKey(name = "CARACT_VISITAS_FK")
	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	@Column(name = "NUM_PERSONAS")
	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	
	@Column(name = "NUM_CUARTOS")
	public int getNumCuartos() {
		return numCuartos;
	}

	public void setNumCuartos(int numCuartos) {
		this.numCuartos = numCuartos;
	}

	@Column(name = "HACINAMIENTO", nullable = true, length = 2)
	public String getHacinamiento() {
		return hacinamiento;
	}

	public void setHacinamiento(String hacinamiento) {
		this.hacinamiento = hacinamiento;
	}

	@Column(name = "ANIM_DOM", nullable = true, length = 255)
	public String getAnimalesDom() {
		return animalesDom;
	}

	public void setAnimalesDom(String animalesDom) {
		this.animalesDom = animalesDom;
	}

	@Column(name = "RIESGO_NATURAL", nullable = true, length = 255)
	public String getRiesgoNatural() {
		return riesgoNatural;
	}

	public void setRiesgoNatural(String riesgoNatural) {
		this.riesgoNatural = riesgoNatural;
	}

	@Column(name = "RIESGO_METEOROLOGICO", nullable = true, length = 255)
	public String getRiesgoMeteorologico() {
		return riesgoMeteorologico;
	}

	public void setRiesgoMeteorologico(String riesgoMeteorologico) {
		this.riesgoMeteorologico = riesgoMeteorologico;
	}

	@Column(name = "RIESGO_BIOLOGICO", nullable = true, length = 255)
	public String getRiesgoBiologico() {
		return riesgoBiologico;
	}

	public void setRiesgoBiologico(String riesgoBiologico) {
		this.riesgoBiologico = riesgoBiologico;
	}

	@Column(name = "RIESGO_SOCIAL", nullable = true, length = 255)
	public String getRiesgoSocial() {
		return riesgoSocial;
	}

	public void setRiesgoSocial(String riesgoSocial) {
		this.riesgoSocial = riesgoSocial;
	}

	@Column(name = "FACTORES_MEDIO", nullable = true, length = 255)
	public String getFactoresMedAmb() {
		return factoresMedAmb;
	}

	public void setFactoresMedAmb(String factoresMedAmb) {
		this.factoresMedAmb = factoresMedAmb;
	}

	@Column(name = "COMBUSTIBLE_COCINAR", nullable = true, length = 255)
	public String getCombCocinar() {
		return combCocinar;
	}

	public void setCombCocinar(String combCocinar) {
		this.combCocinar = combCocinar;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_AAGUA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_AAGUA_FK")
	public AbastecimientoAgua getaAgua() {
		return aAgua;
	}

	public void setaAgua(AbastecimientoAgua aAgua) {
		this.aAgua = aAgua;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_CAGUA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_CAGUA_FK")
	public CalidadAgua getcAgua() {
		return cAgua;
	}

	public void setcAgua(CalidadAgua cAgua) {
		this.cAgua = cAgua;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ELECTRICIDAD",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_ELECTRICIDAD_FK")
	public Electricidad getElectricidad() {
		return electricidad;
	}

	public void setElectricidad(Electricidad electricidad) {
		this.electricidad = electricidad;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_DEPEXC",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_DEPEXC_FK")
	public DepExcretas getDepExcretas() {
		return depExcretas;
	}

	public void setDepExcretas(DepExcretas depExcretas) {
		this.depExcretas = depExcretas;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_DEPBAS",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_DEPBAS_FK")
	public DepBasura getDepBasura() {
		return depBasura;
	}

	public void setDepBasura(DepBasura depBasura) {
		this.depBasura = depBasura;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_DEPRLIQ",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "CARACTHIG_DEPRLIQ_FK")
	public DepResLiq getDepResLiq() {
		return depResLiq;
	}

	public void setDepResLiq(DepResLiq depResLiq) {
		this.depResLiq = depResLiq;
	}

	@Column(name = "OBSERVACIONES", nullable = true, length = 255)
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

}
