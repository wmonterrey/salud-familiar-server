package ni.gob.minsa.hsf.domain;
// default package
// Generated 06-17-2013 02:38:16 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Tbldatosper generated by hbm2java
 */
public class Tbldatosper implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPersona;
	private String noCed;
	private String nombrec;
	private String apellido;
	private Integer idCargo;
	private Integer idPob;
	private String codeHab;
	private Character sexo;
	private Date fechaNac;
	private String TEnfermeda;
	private String apellido2;
	private String codeVivienda;
	private boolean jefe;
	private String etnia;
	private int edad;
	private boolean sinEdad;
	private int escolaridad;
	private int profesion;
	private int ocupacion;
	private int grupod;
	private String factRiesgomodif;
	private String factRiesgoNoModif;
	private Integer tipoEdad;
	private int idFamilia;
	private int borrado;
	private int MEmb;
	private int MMef;
	private int NVpcd;
	private String idDiscapacidad;

	public Tbldatosper() {
	}

	public Tbldatosper(String noCed, String codeHab, String codeVivienda,
			boolean jefe, String etnia, int edad, boolean sinEdad,
			int escolaridad, int profesion, int ocupacion, int grupod,
			String factRiesgomodif, String factRiesgoNoModif, int idFamilia,
			int borrado, int MEmb, int MMef, int NVpcd, String idDiscapacidad) {
		this.noCed = noCed;
		this.codeHab = codeHab;
		this.codeVivienda = codeVivienda;
		this.jefe = jefe;
		this.etnia = etnia;
		this.edad = edad;
		this.sinEdad = sinEdad;
		this.escolaridad = escolaridad;
		this.profesion = profesion;
		this.ocupacion = ocupacion;
		this.grupod = grupod;
		this.factRiesgomodif = factRiesgomodif;
		this.factRiesgoNoModif = factRiesgoNoModif;
		this.idFamilia = idFamilia;
		this.borrado = borrado;
		this.MEmb = MEmb;
		this.MMef = MMef;
		this.NVpcd = NVpcd;
		this.idDiscapacidad = idDiscapacidad;
	}

	public Tbldatosper(String noCed, String nombrec, String apellido,
			Integer idCargo, Integer idPob, String codeHab, Character sexo,
			Date fechaNac, String TEnfermeda, String apellido2,
			String codeVivienda, boolean jefe, String etnia, int edad,
			boolean sinEdad, int escolaridad, int profesion, int ocupacion,
			int grupod, String factRiesgomodif, String factRiesgoNoModif,
			Integer tipoEdad, int idFamilia, int borrado, int MEmb, int MMef,
			int NVpcd, String idDiscapacidad) {
		this.noCed = noCed;
		this.nombrec = nombrec;
		this.apellido = apellido;
		this.idCargo = idCargo;
		this.idPob = idPob;
		this.codeHab = codeHab;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.TEnfermeda = TEnfermeda;
		this.apellido2 = apellido2;
		this.codeVivienda = codeVivienda;
		this.jefe = jefe;
		this.etnia = etnia;
		this.edad = edad;
		this.sinEdad = sinEdad;
		this.escolaridad = escolaridad;
		this.profesion = profesion;
		this.ocupacion = ocupacion;
		this.grupod = grupod;
		this.factRiesgomodif = factRiesgomodif;
		this.factRiesgoNoModif = factRiesgoNoModif;
		this.tipoEdad = tipoEdad;
		this.idFamilia = idFamilia;
		this.borrado = borrado;
		this.MEmb = MEmb;
		this.MMef = MMef;
		this.NVpcd = NVpcd;
		this.idDiscapacidad = idDiscapacidad;
	}

	public Integer getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNoCed() {
		return this.noCed;
	}

	public void setNoCed(String noCed) {
		this.noCed = noCed;
	}

	public String getNombrec() {
		return this.nombrec;
	}

	public void setNombrec(String nombrec) {
		this.nombrec = nombrec;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public Integer getIdPob() {
		return this.idPob;
	}

	public void setIdPob(Integer idPob) {
		this.idPob = idPob;
	}

	public String getCodeHab() {
		return this.codeHab;
	}

	public void setCodeHab(String codeHab) {
		this.codeHab = codeHab;
	}

	public Character getSexo() {
		return this.sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTEnfermeda() {
		return this.TEnfermeda;
	}

	public void setTEnfermeda(String TEnfermeda) {
		this.TEnfermeda = TEnfermeda;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCodeVivienda() {
		return this.codeVivienda;
	}

	public void setCodeVivienda(String codeVivienda) {
		this.codeVivienda = codeVivienda;
	}

	public boolean isJefe() {
		return this.jefe;
	}

	public void setJefe(boolean jefe) {
		this.jefe = jefe;
	}

	public String getEtnia() {
		return this.etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isSinEdad() {
		return this.sinEdad;
	}

	public void setSinEdad(boolean sinEdad) {
		this.sinEdad = sinEdad;
	}

	public int getEscolaridad() {
		return this.escolaridad;
	}

	public void setEscolaridad(int escolaridad) {
		this.escolaridad = escolaridad;
	}

	public int getProfesion() {
		return this.profesion;
	}

	public void setProfesion(int profesion) {
		this.profesion = profesion;
	}

	public int getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}

	public int getGrupod() {
		return this.grupod;
	}

	public void setGrupod(int grupod) {
		this.grupod = grupod;
	}

	public String getFactRiesgomodif() {
		return this.factRiesgomodif;
	}

	public void setFactRiesgomodif(String factRiesgomodif) {
		this.factRiesgomodif = factRiesgomodif;
	}

	public String getFactRiesgoNoModif() {
		return this.factRiesgoNoModif;
	}

	public void setFactRiesgoNoModif(String factRiesgoNoModif) {
		this.factRiesgoNoModif = factRiesgoNoModif;
	}

	public Integer getTipoEdad() {
		return this.tipoEdad;
	}

	public void setTipoEdad(Integer tipoEdad) {
		this.tipoEdad = tipoEdad;
	}

	public int getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
	}

	public int getBorrado() {
		return this.borrado;
	}

	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}

	public int getMEmb() {
		return this.MEmb;
	}

	public void setMEmb(int MEmb) {
		this.MEmb = MEmb;
	}

	public int getMMef() {
		return this.MMef;
	}

	public void setMMef(int MMef) {
		this.MMef = MMef;
	}

	public int getNVpcd() {
		return this.NVpcd;
	}

	public void setNVpcd(int NVpcd) {
		this.NVpcd = NVpcd;
	}

	public String getIdDiscapacidad() {
		return this.idDiscapacidad;
	}

	public void setIdDiscapacidad(String idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}

}
