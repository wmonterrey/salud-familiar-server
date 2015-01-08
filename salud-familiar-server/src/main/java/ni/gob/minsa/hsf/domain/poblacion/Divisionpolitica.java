package ni.gob.minsa.hsf.domain.poblacion;

// Generated 12-06-2012 03:38:40 PM by Hibernate Tools 3.6.0

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * Divisionpolitica generated by hbm2java
 */
@Entity
@Table(name = "divisionpolitica", catalog = "general")
public class Divisionpolitica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long divisionpoliticaId;
	private String nombre;
	private Long dependencia;
	private Long administracion;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String codigoIso;
	private String codigoNacional;
	private char pasivo;
	private DateTime fechaRegistro;
	private String usuarioRegistro;
	private Short codigoCse;
	private EntidadesAdtvas dependenciaSilais;

	public Divisionpolitica() {
	}

	public Divisionpolitica(long divisionpoliticaId, String nombre,
			String codigoNacional, char pasivo, DateTime fechaRegistro,
			String usuarioRegistro) {
		this.divisionpoliticaId = divisionpoliticaId;
		this.nombre = nombre;
		this.codigoNacional = codigoNacional;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Divisionpolitica(long divisionpoliticaId, String nombre,
			Long dependencia, Long administracion, BigDecimal latitud,
			BigDecimal longitud, String codigoIso, String codigoNacional,
			char pasivo, DateTime fechaRegistro, String usuarioRegistro,
			Short codigoCse) {
		this.divisionpoliticaId = divisionpoliticaId;
		this.nombre = nombre;
		this.dependencia = dependencia;
		this.administracion = administracion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codigoIso = codigoIso;
		this.codigoNacional = codigoNacional;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
		this.codigoCse = codigoCse;
	}

	@Id
	@Column(name = "DIVISIONPOLITICA_ID", nullable = false)
	public long getDivisionpoliticaId() {
		return this.divisionpoliticaId;
	}

	public void setDivisionpoliticaId(long divisionpoliticaId) {
		this.divisionpoliticaId = divisionpoliticaId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "DEPENDENCIA", nullable = true)
	public Long getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(Long dependencia) {
		this.dependencia = dependencia;
	}

	@Column(name = "ADMINISTRACION", nullable = true)
	public Long getAdministracion() {
		return this.administracion;
	}

	public void setAdministracion(Long administracion) {
		this.administracion = administracion;
	}

	@Column(name = "LATITUD", nullable = true)
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "LONGITUD", nullable = true)
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "CODIGO_ISO", nullable = true, length = 2)
	public String getCodigoIso() {
		return this.codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	@Column(name = "CODIGO_NACIONAL", nullable = false, length = 4)
	public String getCodigoNacional() {
		return this.codigoNacional;
	}

	public void setCodigoNacional(String codigoNacional) {
		this.codigoNacional = codigoNacional;
	}

	@Column(name = "PASIVO", nullable = false, length = 1)
	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
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
	
	@Column(name = "USUARIO_REGISTRO", nullable = false, length = 100)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	@Column(name = "CODIGO_CSE", nullable = true)
	public Short getCodigoCse() {
		return this.codigoCse;
	}

	public void setCodigoCse(Short codigoCse) {
		this.codigoCse = codigoCse;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="DEPENDENCIA_SILAIS",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "MUNICIPIO_SILAIS_FK")
	public EntidadesAdtvas getDependenciaSilais() {
		return dependenciaSilais;
	}

	public void setDependenciaSilais(EntidadesAdtvas dependenciaSilais) {
		this.dependenciaSilais = dependenciaSilais;
	}

}
