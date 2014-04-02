package ni.gob.minsa.hsf.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;


@Entity
@Table(name = "PERSONAS", catalog = "HSF")
public class Persona {
	
	private String idPersona;
	private long idPersonaSis;
	private Familia familia;
	private String codPersona;
	private String primerNombre;
    private String primerApellido;
    private String segundoNombre;
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
    private String lactMaterna;
    private String men1A;
    private String men1AVPCD;
    private GrupoDispensarial grupoDisp;
    private String fallecido;
    private Date fechaFallecimiento;
    
    
	public Persona() {
		
	}


	@Id
	@Column(name = "ID_PERSONA", nullable = false, length = 30)
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

	@ManyToOne
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

	@Column(name = "PRIMER_NOMBRE", nullable = false, length = 50)
	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	@Column(name = "PRIMER_APELLIDO", nullable = false, length = 50)
	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	@Column(name = "SEGUNDO_NOMBRE", nullable = true, length = 50)
	public String getSegundoNombre() {
		return segundoNombre;
	}


	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
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

	@Column(name = "LACT_MAT", nullable = true, length = 2)
	public String getLactMaterna() {
		return lactMaterna;
	}


	public void setLactMaterna(String lactMaterna) {
		this.lactMaterna = lactMaterna;
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
