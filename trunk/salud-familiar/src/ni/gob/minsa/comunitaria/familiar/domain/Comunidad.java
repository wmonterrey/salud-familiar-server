package ni.gob.minsa.comunitaria.familiar.domain;


import java.math.BigDecimal;

import org.joda.time.DateTime;


public class Comunidad {

	private Integer comunidadId;
	private String nombre;
	private String sector;
	private String referencias;
	private Character tipoArea;
	private String codigo;
	private String caracteristicas;
	private char pasivo = 0;
	private DateTime fechaRegistro;
	private String usuarioRegistro;
	private BigDecimal longitud;
	private BigDecimal latitud;
	private String zona;
	

	public Comunidad() {
	}

	public Comunidad(Integer comunidadId, String nombre, String sector,
			String codigo, char pasivo, DateTime fechaRegistro,
			String usuarioRegistro) {
		this.comunidadId = comunidadId;
		this.nombre = nombre;
		this.sector = sector;
		this.codigo = codigo;
		this.pasivo = pasivo;
		this.setFechaRegistro(fechaRegistro);
		this.usuarioRegistro = usuarioRegistro;
	}

	public Comunidad(Integer comunidadId, String nombre, String sector,
			String referencias, Character tipoArea, String codigo,
			String caracteristicas, char pasivo, DateTime fechaRegistro,
			String usuarioRegistro, BigDecimal longitud, BigDecimal latitud,
			String zona) {
		this.comunidadId = comunidadId;
		this.nombre = nombre;
		this.sector = sector;
		this.referencias = referencias;
		this.tipoArea = tipoArea;
		this.codigo = codigo;
		this.caracteristicas = caracteristicas;
		this.pasivo = pasivo;
		this.setFechaRegistro(fechaRegistro);
		this.usuarioRegistro = usuarioRegistro;
		this.longitud = longitud;
		this.latitud = latitud;
		this.zona = zona;
	}

	
	public Integer getComunidadId() {
		return this.comunidadId;
	}

	public void setComunidadId(Integer comunidadId) {
		this.comunidadId = comunidadId;
	}

	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	
	public Character getTipoArea() {
		return this.tipoArea;
	}

	public void setTipoArea(Character tipoArea) {
		this.tipoArea = tipoArea;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	

}
