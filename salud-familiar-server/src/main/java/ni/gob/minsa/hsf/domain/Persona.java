package ni.gob.minsa.hsf.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;


@Entity
@Table(name = "HSF_PERSONAS", catalog = "HSF")
public class Persona extends BaseMetaData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private long idPersonaSis;
	private Familia familia;
	private Integer numPersona;
	private String codPersona;
	private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String cedula;
    private Date fechaNacimiento;
    private String actaNacimiento;
    private Integer edad;
    private Etnia etnia;
    private Sexo sexo;
    private Escolaridad escolaridad;
    private Ocupacion ocupacion;
    private Religion religion;
    private String embarazada;
    private String cpnActualizado;
    private String mujerEdadFertil;
    private String planFamiliar;
    private String men1A;
    private String men1AVPCD;
    private String factRiesgoMod;
    private String factRiesgoNoMod;
    private String factRiesgoSocial;
	private Persona padre;
    private Persona madre;
    private String discapacidades;
    private GrupoDispensarial grupoDisp;
    private String fallecido;
    private Date fechaFallecimiento;
    
    
	public Persona() {
		
	}


	@Id
	@Column(name = "ID_PERSONA", nullable = false, length = 50)
	public String getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}


	@Column(name = "ID_PERSONA_SIS", nullable = true)
	public long getIdPersonaSis() {
		return idPersonaSis;
	}


	public void setIdPersonaSis(long idPersonaSis) {
		this.idPersonaSis = idPersonaSis;
	}

	@Column(name = "NUM_PERSONA", nullable = false)
	public Integer getNumPersona() {
		return numPersona;
	}


	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="ID_FAMILIA")
	@ForeignKey(name = "PERSONAS_FAMILIAS_FK")
	public Familia getFamilia() {
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	@Column(name = "COD_PERSONA", nullable = false, length = 30)
	public String getCodPersona() {
		return codPersona;
	}


	public void setCodPersona(String codPersona) {
		this.codPersona = codPersona;
	}

	@Column(name = "NOMBREC", nullable = false, length = 50)
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	@Column(name = "PRIMER_APELLIDO", nullable = false, length = 50)
	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	@Column(name = "SEGUNDO_APELLIDO", nullable = true, length = 50)
	public String getSegundoApellido() {
		return segundoApellido;
	}


	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	@Column(name = "CEDULA", nullable = true, length = 25)
	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Column(name = "FECHA_NAC", nullable = true)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "ACTA_NAC", nullable = true, length = 2)
	public String getActaNacimiento() {
		return actaNacimiento;
	}


	public void setActaNacimiento(String actaNacimiento) {
		this.actaNacimiento = actaNacimiento;
	}

	@Column(name = "EDAD", nullable = true)
	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ETNIA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_ETNIAS_FK")
	public Etnia getEtnia() {
		return etnia;
	}


	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_SEXO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_SEXO_FK")
	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ESCDA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_ESCDA_FK")
	public Escolaridad getEscolaridad() {
		return escolaridad;
	}


	public void setEscolaridad(Escolaridad escolaridad) {
		this.escolaridad = escolaridad;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_OCUPA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_OCUPA_FK")
	public Ocupacion getOcupacion() {
		return ocupacion;
	}


	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_RELIG",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_RELIGION_FK")
	public Religion getReligion() {
		return religion;
	}


	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	@Column(name = "EMBARAZADA", nullable = true, length = 2)
	public String getEmbarazada() {
		return embarazada;
	}


	public void setEmbarazada(String embarazada) {
		this.embarazada = embarazada;
	}

	@Column(name = "CPN_ACTUALIZADO", nullable = true, length = 2)
	public String getCpnActualizado() {
		return cpnActualizado;
	}


	public void setCpnActualizado(String cpnActualizado) {
		this.cpnActualizado = cpnActualizado;
	}

	@Column(name = "MEF", nullable = true, length = 2)
	public String getMujerEdadFertil() {
		return mujerEdadFertil;
	}


	public void setMujerEdadFertil(String mujerEdadFertil) {
		this.mujerEdadFertil = mujerEdadFertil;
	}

	@Column(name = "PLAN_FAM", nullable = true, length = 2)
	public String getPlanFamiliar() {
		return planFamiliar;
	}


	public void setPlanFamiliar(String planFamiliar) {
		this.planFamiliar = planFamiliar;
	}

	@Column(name = "NIN_MEN_1A", nullable = true, length = 2)
	public String getMen1A() {
		return men1A;
	}


	public void setMen1A(String men1a) {
		men1A = men1a;
	}

	@Column(name = "VPCD_ACTUALIZADO", nullable = true, length = 2)
	public String getMen1AVPCD() {
		return men1AVPCD;
	}


	public void setMen1AVPCD(String men1avpcd) {
		men1AVPCD = men1avpcd;
	}
	
	@Column(name = "FACT_RIESGO_MOD", nullable = true, length = 200)
	public String getFactRiesgoMod() {
		return factRiesgoMod;
	}


	public void setFactRiesgoMod(String factRiesgoMod) {
		this.factRiesgoMod = factRiesgoMod;
	}

	@Column(name = "FACT_RIESGO_NO_MOD", nullable = true, length = 200)
	public String getFactRiesgoNoMod() {
		return factRiesgoNoMod;
	}


	public void setFactRiesgoNoMod(String factRiesgoNoMod) {
		this.factRiesgoNoMod = factRiesgoNoMod;
	}

	@Column(name = "FACT_RIESGO_SOCIAL", nullable = true, length = 200)
	public String getFactRiesgoSocial() {
		return factRiesgoSocial;
	}


	public void setFactRiesgoSocial(String factRiesgoSocial) {
		this.factRiesgoSocial = factRiesgoSocial;
	}

    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PADRE",
				updatable=false,
				nullable=true,
				insertable=false,
				referencedColumnName="ID_PERSONA")
    @ForeignKey(name = "PADRE_FK")
	public Persona getPadre() {
		return padre;
	}


	public void setPadre(Persona padre) {
		this.padre = padre;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MADRE",
				updatable=false,
				nullable=true,
				insertable=false,
				referencedColumnName="ID_PERSONA")
	@ForeignKey(name = "MADRE_FK")
	public Persona getMadre() {
		return madre;
	}


	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	@Column(name = "DISCAPACIDADES", nullable = true, length = 200)
	public String getDiscapacidades() {
		return discapacidades;
	}


	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}


	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_GD",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "PERSONAS_GD_FK")
	public GrupoDispensarial getGrupoDisp() {
		return grupoDisp;
	}


	public void setGrupoDisp(GrupoDispensarial grupoDisp) {
		this.grupoDisp = grupoDisp;
	}

	@Column(name = "FALLECIDO", nullable = false, length = 2)
	public String getFallecido() {
		return fallecido;
	}


	public void setFallecido(String fallecido) {
		this.fallecido = fallecido;
	}

	@Column(name = "FECHA_FALL", nullable = true)
	public Date getFechaFallecimiento() {
		return fechaFallecimiento;
	}


	public void setFechaFallecimiento(Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

}
