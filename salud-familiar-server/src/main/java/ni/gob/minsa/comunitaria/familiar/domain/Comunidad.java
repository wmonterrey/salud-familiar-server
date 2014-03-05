package ni.gob.minsa.comunitaria.familiar.domain;


import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import ni.gob.minsa.comunitaria.familiar.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "comunidades", catalog = "salud_familiar")
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
	
	private Set<Tblvivienda> viviendas;

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

	@Id
	@Column(name = "comunidad_id", nullable = false)
	public Integer getComunidadId() {
		return this.comunidadId;
	}

	public void setComunidadId(Integer comunidadId) {
		this.comunidadId = comunidadId;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "sector", nullable = false, length = 7)
	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	@Column(name = "referencias", nullable = true, length = 1000)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	@Column(name = "tipo_area", nullable = true, length = 1)
	public Character getTipoArea() {
		return this.tipoArea;
	}

	public void setTipoArea(Character tipoArea) {
		this.tipoArea = tipoArea;
	}

	@Column(name = "codigo", nullable = true, length = 9)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "caracteristicas", nullable = true, length = 1000)
	public String getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Column(name = "pasivo", nullable = false, length = 1)
	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

	@Column(name = "usuario_registro", nullable = false, length = 100)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	@Column(name = "longitud", nullable = true)
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "latitud", nullable = true)
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "zona", nullable = true, length = 50)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Column(name="fecha_registro")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@JsonSerialize(using = CustomDateSerializer.class)
	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@OneToMany(mappedBy = "comunidad")
	@IndexColumn(name = "id_pob", base=0)
	public Set<Tblvivienda> getViviendas() {
		return viviendas;
	}

	public void setViviendas(Set<Tblvivienda> viviendas) {
		this.viviendas = viviendas;
	}

}
