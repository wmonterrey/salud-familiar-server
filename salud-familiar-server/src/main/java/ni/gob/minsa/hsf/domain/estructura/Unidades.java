package ni.gob.minsa.hsf.domain.estructura;

// Generated Mar 28, 2014 11:26:42 AM by Hibernate Tools 4.0.0

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Unidades generated by hbm2java
 */
@Entity
@Table(name = "unidades", catalog = "general", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class Unidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long unidadId;
	private String nombre;
	private String razonSocial;
	private String direccion;
	private long tipoUnidad;
	private String municipio;
	private long entidadAdtva;
	private char declaraSector;
	private String telefono;
	private String fax;
	private String email;
	private char grupoEconomico;
	private long regimen;
	private long categoria;
	private BigDecimal longitud;
	private BigDecimal latitud;
	private char conectividad;
	private long codigo;
	private char pasivo;
	private DateTime fechaRegistro;
	private String usuarioRegistro;
	private Long ficha;
	private String ubicacionGeografica;
	private String zona;
	private Long unidadAdtva;

	public Unidades() {
	}

	public Unidades(long unidadId, String nombre, long tipoUnidad,
			String municipio, long entidadAdtva, char declaraSector,
			char grupoEconomico, long regimen, long categoria,
			char conectividad, long codigo, char pasivo,
			DateTime fechaRegistro, String usuarioRegistro) {
		this.unidadId = unidadId;
		this.nombre = nombre;
		this.tipoUnidad = tipoUnidad;
		this.municipio = municipio;
		this.entidadAdtva = entidadAdtva;
		this.declaraSector = declaraSector;
		this.grupoEconomico = grupoEconomico;
		this.regimen = regimen;
		this.categoria = categoria;
		this.conectividad = conectividad;
		this.codigo = codigo;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Unidades(long unidadId, String nombre, String razonSocial,
			String direccion, long tipoUnidad, String municipio,
			long entidadAdtva, char declaraSector, String telefono, String fax,
			String email, char grupoEconomico, long regimen, long categoria,
			BigDecimal longitud, BigDecimal latitud, char conectividad,
			long codigo, char pasivo, DateTime fechaRegistro,
			String usuarioRegistro, Long ficha, String ubicacionGeografica,
			String zona, Long unidadAdtva) {
		this.unidadId = unidadId;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.tipoUnidad = tipoUnidad;
		this.municipio = municipio;
		this.entidadAdtva = entidadAdtva;
		this.declaraSector = declaraSector;
		this.telefono = telefono;
		this.fax = fax;
		this.email = email;
		this.grupoEconomico = grupoEconomico;
		this.regimen = regimen;
		this.categoria = categoria;
		this.longitud = longitud;
		this.latitud = latitud;
		this.conectividad = conectividad;
		this.codigo = codigo;
		this.pasivo = pasivo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
		this.ficha = ficha;
		this.ubicacionGeografica = ubicacionGeografica;
		this.zona = zona;
		this.unidadAdtva = unidadAdtva;
	}

	@Id
	@Column(name = "UNIDAD_ID")
	public long getUnidadId() {
		return this.unidadId;
	}

	public void setUnidadId(long unidadId) {
		this.unidadId = unidadId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 400)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "RAZON_SOCIAL", length = 400)
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "DIRECCION", length = 800)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "TIPO_UNIDAD", nullable = false)
	public long getTipoUnidad() {
		return this.tipoUnidad;
	}

	public void setTipoUnidad(long tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	@Column(name = "MUNICIPIO", nullable = false, length = 4)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "ENTIDAD_ADTVA", nullable = false)
	public long getEntidadAdtva() {
		return this.entidadAdtva;
	}

	public void setEntidadAdtva(long entidadAdtva) {
		this.entidadAdtva = entidadAdtva;
	}

	@Column(name = "DECLARA_SECTOR", nullable = false, length = 1)
	public char getDeclaraSector() {
		return this.declaraSector;
	}

	public void setDeclaraSector(char declaraSector) {
		this.declaraSector = declaraSector;
	}

	@Column(name = "TELEFONO", length = 200)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "FAX", length = 200)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "GRUPO_ECONOMICO", nullable = false, length = 1)
	public char getGrupoEconomico() {
		return this.grupoEconomico;
	}

	public void setGrupoEconomico(char grupoEconomico) {
		this.grupoEconomico = grupoEconomico;
	}

	@Column(name = "REGIMEN", nullable = false)
	public long getRegimen() {
		return this.regimen;
	}

	public void setRegimen(long regimen) {
		this.regimen = regimen;
	}

	@Column(name = "CATEGORIA", nullable = false)
	public long getCategoria() {
		return this.categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}

	@Column(name = "LONGITUD")
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "LATITUD")
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "CONECTIVIDAD", nullable = false, length = 1)
	public char getConectividad() {
		return this.conectividad;
	}

	public void setConectividad(char conectividad) {
		this.conectividad = conectividad;
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

	@Column(name = "FICHA")
	public Long getFicha() {
		return this.ficha;
	}

	public void setFicha(Long ficha) {
		this.ficha = ficha;
	}

	@Column(name = "UBICACION_GEOGRAFICA", length = 16)
	public String getUbicacionGeografica() {
		return this.ubicacionGeografica;
	}

	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}

	@Column(name = "ZONA", length = 200)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Column(name = "UNIDAD_ADTVA")
	public Long getUnidadAdtva() {
		return this.unidadAdtva;
	}

	public void setUnidadAdtva(Long unidadAdtva) {
		this.unidadAdtva = unidadAdtva;
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
		if (!(other instanceof Unidades))
			return false;
		
		Unidades castOther = (Unidades) other;
		
		return (this.getUnidadId() == castOther.getUnidadId())
				&& (this.getCodigo() == castOther.getCodigo()) 
				&& (this.getNombre().equals(castOther.getNombre()));
	}
}
