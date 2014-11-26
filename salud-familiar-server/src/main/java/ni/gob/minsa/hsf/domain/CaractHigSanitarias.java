package ni.gob.minsa.hsf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.audit.Auditable;
import ni.gob.minsa.hsf.domain.catalogos.AbastecimientoAgua;
import ni.gob.minsa.hsf.domain.catalogos.CalidadAgua;
import ni.gob.minsa.hsf.domain.catalogos.DepBasura;
import ni.gob.minsa.hsf.domain.catalogos.DepExcretas;
import ni.gob.minsa.hsf.domain.catalogos.DepResLiq;
import ni.gob.minsa.hsf.domain.catalogos.Electricidad;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "hsf_caract_hig", catalog = "hsf")
public class CaractHigSanitarias extends BaseMetaData implements Auditable{
	
	
	private String idCaractHig;
	private Familia familia;
	private Date fechaUltimaVisita;
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
    private String obsCaractHig;
    
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

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_FAMILIA")
	@ForeignKey(name = "CARACT_FAMILIAS_FK")
	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	@Column(name = "FECHA_ULTIMA_VISITA", nullable = false)
	public Date getFechaUltimaVisita() {
		return fechaUltimaVisita;
	}

	public void setFechaUltimaVisita(Date fechaUltimaVisita) {
		this.fechaUltimaVisita = fechaUltimaVisita;
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
	public String getObsCaractHig() {
		return obsCaractHig;
	}

	public void setObsCaractHig(String obsCaractHig) {
		this.obsCaractHig = obsCaractHig;
	}
	
	@Override
	public String toString(){
		return idCaractHig;
	}
	
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")){
			return false;
		}
		return true;
	}

}
