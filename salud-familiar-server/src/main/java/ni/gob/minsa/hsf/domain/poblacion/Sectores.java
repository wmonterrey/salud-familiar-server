package ni.gob.minsa.hsf.domain.poblacion;

// Generated Mar 28, 2014 11:26:42 AM by Hibernate Tools 4.0.0

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Sectores generated by hbm2java
 */
@Entity
@Table(name = "SECTORES", catalog = "HSF", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class Sectores implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5782372426905284224L;
	private long sectorId;
	private String nombre;
	private String referencias;
	private Long unidad;
	private String municipio;
	private String codigo;
	private char sede;
	private char pasivo;
	private Serializable fechaRegistro;
	private String usuarioRegistro;

	public Sectores() {
	}

	public Sectores(long sectorId, String nombre, String municipio,
			String codigo, char sede, char pasivo, Serializable fechaRegistro,
			String usuarioRegistro) {
		this.sectorId = sectorId;
		this.nombre = nombre;
		this.municipio = municipio;
		this.codigo = codigo;
		this.sede = sede;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Sectores(long sectorId, String nombre, String referencias,
			Long unidad, String municipio, String codigo, char sede,
			char pasivo, Serializable fechaRegistro, String usuarioRegistro) {
		this.sectorId = sectorId;
		this.nombre = nombre;
		this.referencias = referencias;
		this.unidad = unidad;
		this.municipio = municipio;
		this.codigo = codigo;
		this.sede = sede;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	@Id
	@Column(name = "SECTOR_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(long sectorId) {
		this.sectorId = sectorId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 400)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "REFERENCIAS", length = 2000)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	@Column(name = "UNIDAD", precision = 10, scale = 0)
	public Long getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Long unidad) {
		this.unidad = unidad;
	}

	@Column(name = "MUNICIPIO", nullable = false, length = 4)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "CODIGO", unique = true, nullable = false, length = 28)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "SEDE", nullable = false, length = 1)
	public char getSede() {
		return this.sede;
	}

	public void setSede(char sede) {
		this.sede = sede;
	}

	@Column(name = "PASIVO", nullable = false, length = 1)
	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

	@Column(name = "FECHA_REGISTRO", nullable = false)
	public Serializable getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Serializable fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "USUARIO_REGISTRO", nullable = false, length = 100)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}