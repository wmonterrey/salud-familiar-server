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

import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * EntidadesAdtvas generated by hbm2java
 */
@Entity
@Table(name = "entidades_adtvas", catalog = "general")
public class EntidadesAdtvas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long entidadAdtvaId;
	private EntidadesAdtvas entidadesAdtvas;
	private String nombre;
	private String municipio;
	private String telefono;
	private String fax;
	private String email;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private long codigo;
	private char pasivo;
	private DateTime fechaRegistro;
	private String usuarioRegistro;
	private String direccion;

	public EntidadesAdtvas() {
	}

	public EntidadesAdtvas(long entidadAdtvaId, String nombre,
			String municipio, long codigo, char pasivo,
			DateTime fechaRegistro, String usuarioRegistro) {
		this.entidadAdtvaId = entidadAdtvaId;
		this.nombre = nombre;
		this.municipio = municipio;
		this.codigo = codigo;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	public EntidadesAdtvas(long entidadAdtvaId,
			EntidadesAdtvas entidadesAdtvas, String nombre, String municipio,
			String telefono, String fax, String email, BigDecimal latitud,
			BigDecimal longitud, long codigo, char pasivo,
			DateTime fechaRegistro, String usuarioRegistro,
			String direccion) {
		this.entidadAdtvaId = entidadAdtvaId;
		this.entidadesAdtvas = entidadesAdtvas;
		this.nombre = nombre;
		this.municipio = municipio;
		this.telefono = telefono;
		this.fax = fax;
		this.email = email;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codigo = codigo;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
		this.direccion = direccion;
	}

	@Id
	@Column(name = "ENTIDAD_ADTVA_ID")
	public long getEntidadAdtvaId() {
		return this.entidadAdtvaId;
	}

	public void setEntidadAdtvaId(long entidadAdtvaId) {
		this.entidadAdtvaId = entidadAdtvaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPENDENCIA")
	public EntidadesAdtvas getEntidadesAdtvas() {
		return this.entidadesAdtvas;
	}

	public void setEntidadesAdtvas(EntidadesAdtvas entidadesAdtvas) {
		this.entidadesAdtvas = entidadesAdtvas;
	}

	@Column(name = "NOMBRE", nullable = false, length = 400)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "MUNICIPIO", nullable = false, length = 4)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "TELEFONO", nullable = true, length = 200)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "FAX", nullable = true, length = 200)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EMAIL", nullable = true, length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Column(name = "CODIGO", nullable = false)
	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
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

	@Column(name = "DIRECCION", length = 800)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString(){
		return nombre;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntidadesAdtvas))
			return false;
		
		EntidadesAdtvas castOther = (EntidadesAdtvas) other;
		
		return (this.getEntidadAdtvaId() == castOther.getEntidadAdtvaId())
				&& (this.getCodigo() == castOther.getCodigo()) 
				&& (this.getNombre().equals(castOther.getNombre()));
	}

}
