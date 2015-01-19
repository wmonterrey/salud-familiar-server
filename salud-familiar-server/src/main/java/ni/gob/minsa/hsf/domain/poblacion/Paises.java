package ni.gob.minsa.hsf.domain.poblacion;

// Generated Mar 28, 2014 11:26:42 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * Paises generated by hbm2java
 */
@Entity
@Table(name = "paises", catalog = "general", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO_ALFADOS"),
		@UniqueConstraint(columnNames = "CODIGO_NUMERICO"),
		@UniqueConstraint(columnNames = "CODIGO_ALFATRES") })
public class Paises {

	private long paisId;
	private String nombre;
	private String codigoNumerico;
	private String codigoAlfados;
	private String codigoAlfatres;
	private String codigoIso;
	private DateTime fechaRegistro;
	private String usuarioRegistro;

	public Paises() {
	}

	public Paises(long paisId, String nombre, String codigoNumerico,
			String codigoAlfados, String codigoAlfatres, String codigoIso,
			DateTime fechaRegistro, String usuarioRegistro) {
		this.paisId = paisId;
		this.nombre = nombre;
		this.codigoNumerico = codigoNumerico;
		this.codigoAlfados = codigoAlfados;
		this.codigoAlfatres = codigoAlfatres;
		this.codigoIso = codigoIso;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	@Id
	@Column(name = "PAIS_ID")
	public long getPaisId() {
		return this.paisId;
	}

	public void setPaisId(long paisId) {
		this.paisId = paisId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "CODIGO_NUMERICO", nullable = false, length = 20)
	public String getCodigoNumerico() {
		return this.codigoNumerico;
	}

	public void setCodigoNumerico(String codigoNumerico) {
		this.codigoNumerico = codigoNumerico;
	}

	@Column(name = "CODIGO_ALFADOS", nullable = false, length = 2)
	public String getCodigoAlfados() {
		return this.codigoAlfados;
	}

	public void setCodigoAlfados(String codigoAlfados) {
		this.codigoAlfados = codigoAlfados;
	}

	@Column(name = "CODIGO_ALFATRES", nullable = false, length = 3)
	public String getCodigoAlfatres() {
		return this.codigoAlfatres;
	}

	public void setCodigoAlfatres(String codigoAlfatres) {
		this.codigoAlfatres = codigoAlfatres;
	}

	@Column(name = "CODIGO_ISO", nullable = false, length = 20)
	public String getCodigoIso() {
		return this.codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	@Column(name="FECHA_REGISTRO")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@JsonSerialize(using = CustomDateSerializer.class)
	public DateTime getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "USUARIO_REGISTRO", nullable = false, length = 20)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}
