package ni.gob.minsa.hsf.domain.poblacion;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "comunidades", catalog = "hsf")
public class Comunidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Comunidades() {
	}

	public Comunidades(Integer comunidadId, String nombre, String sector,
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

	public Comunidades(Integer comunidadId, String nombre, String sector,
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
	@Column(name = "COMUNIDAD_ID", nullable = false)
	public Integer getComunidadId() {
		return this.comunidadId;
	}

	public void setComunidadId(Integer comunidadId) {
		this.comunidadId = comunidadId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "SECTOR", nullable = false, length = 7)
	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	@Column(name = "REFERENCIAS", nullable = true, length = 1000)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	@Column(name = "TIPO_AREA", nullable = true, length = 1)
	public Character getTipoArea() {
		return this.tipoArea;
	}

	public void setTipoArea(Character tipoArea) {
		this.tipoArea = tipoArea;
	}

	@Column(name = "CODIGO", nullable = true, length = 9)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "CARACTERISTICAS", nullable = true, length = 1000)
	public String getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Column(name = "PASIVO", nullable = false, length = 1)
	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

	@Column(name = "USUARIO_REGISTRO", nullable = false, length = 100)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	@Column(name = "LONGITUD", nullable = true)
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "LATITUD", nullable = true)
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "ZONA", nullable = true, length = 50)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Column(name="FECHA_REGISTRO")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@JsonSerialize(using = CustomDateSerializer.class)
	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
		if (!(other instanceof Comunidades))
			return false;
		
		Comunidades castOther = (Comunidades) other;
		
		
		return ((this.getComunidadId().equals(castOther.getComunidadId()))
				&& (this.getCodigo().equals(castOther.getCodigo())) 
				&& (this.getNombre().equals(castOther.getNombre())));
	}

}
