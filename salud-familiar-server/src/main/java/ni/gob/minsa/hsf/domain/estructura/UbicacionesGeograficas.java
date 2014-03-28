package ni.gob.minsa.hsf.domain.estructura;

// Generated Mar 28, 2014 11:26:42 AM by Hibernate Tools 4.0.0

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UbicacionesGeograficas generated by hbm2java
 */
@Entity
@Table(name = "UBICACIONES_GEOGRAFICAS", catalog = "HSF", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO_CSE"),
		@UniqueConstraint(columnNames = "CODIGO_NACIONAL") })
public class UbicacionesGeograficas implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7793224099439012079L;
	private long ubicacionGeograficaId;
	private UbicacionesGeograficas ubicacionesGeograficas;
	private String nombre;
	private Long administracion;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String codigoIso;
	private String codigoNacional;
	private Short codigoCse;
	private char pasivo;
	private Serializable fechaRegistro;
	private String usuarioRegistro;

	public UbicacionesGeograficas() {
	}

	public UbicacionesGeograficas(long ubicacionGeograficaId, String nombre,
			String codigoNacional, char pasivo, Serializable fechaRegistro,
			String usuarioRegistro) {
		this.ubicacionGeograficaId = ubicacionGeograficaId;
		this.nombre = nombre;
		this.codigoNacional = codigoNacional;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	public UbicacionesGeograficas(long ubicacionGeograficaId,
			UbicacionesGeograficas ubicacionesGeograficas, String nombre,
			Long administracion, BigDecimal latitud, BigDecimal longitud,
			String codigoIso, String codigoNacional, Short codigoCse,
			char pasivo, Serializable fechaRegistro, String usuarioRegistro) {
		this.ubicacionGeograficaId = ubicacionGeograficaId;
		this.ubicacionesGeograficas = ubicacionesGeograficas;
		this.nombre = nombre;
		this.administracion = administracion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codigoIso = codigoIso;
		this.codigoNacional = codigoNacional;
		this.codigoCse = codigoCse;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	@Id
	@Column(name = "UBICACION_GEOGRAFICA_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getUbicacionGeograficaId() {
		return this.ubicacionGeograficaId;
	}

	public void setUbicacionGeograficaId(long ubicacionGeograficaId) {
		this.ubicacionGeograficaId = ubicacionGeograficaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPENDENCIA")
	public UbicacionesGeograficas getUbicacionesGeograficas() {
		return this.ubicacionesGeograficas;
	}

	public void setUbicacionesGeograficas(
			UbicacionesGeograficas ubicacionesGeograficas) {
		this.ubicacionesGeograficas = ubicacionesGeograficas;
	}

	@Column(name = "NOMBRE", nullable = false, length = 400)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "ADMINISTRACION", precision = 10, scale = 0)
	public Long getAdministracion() {
		return this.administracion;
	}

	public void setAdministracion(Long administracion) {
		this.administracion = administracion;
	}

	@Column(name = "LATITUD", precision = 10, scale = 6)
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "LONGITUD", precision = 10, scale = 6)
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "CODIGO_ISO", length = 2)
	public String getCodigoIso() {
		return this.codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	@Column(name = "CODIGO_NACIONAL", unique = true, nullable = false, length = 4)
	public String getCodigoNacional() {
		return this.codigoNacional;
	}

	public void setCodigoNacional(String codigoNacional) {
		this.codigoNacional = codigoNacional;
	}

	@Column(name = "CODIGO_CSE", unique = true, precision = 3, scale = 0)
	public Short getCodigoCse() {
		return this.codigoCse;
	}

	public void setCodigoCse(Short codigoCse) {
		this.codigoCse = codigoCse;
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
