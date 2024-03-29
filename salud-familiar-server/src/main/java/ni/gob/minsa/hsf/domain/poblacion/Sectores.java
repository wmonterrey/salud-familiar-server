package ni.gob.minsa.hsf.domain.poblacion;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "sectores", catalog = "general")
public class Sectores implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sectorId;
	private String nombre;
	private String referencias;
	private Integer unidad;
	private Divisionpolitica municipio;
	private String codigo;
	private char sede = 0;
	private char pasivo = 0;
	private DateTime fechaRegistro;
	private String usuarioRegistro;
	

	public Sectores() {
	}

	
	@Id
	@Column(name = "SECTOR_ID", nullable = false)
	public Integer getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "REFERENCIAS", nullable = true, length = 500)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	@Column(name = "UNIDAD", nullable = true)
	public Integer getUnidad() {
		return unidad;
	}

	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="MUNICIPIO",referencedColumnName="CODIGO_NACIONAL", nullable=true)
	@ForeignKey(name = "SECTOR_MUNICIPIO_FK")
	public Divisionpolitica getMunicipio() {
		return municipio;
	}


	public void setMunicipio(Divisionpolitica municipio) {
		this.municipio = municipio;
	}

	@Column(name = "CODIGO", nullable = false, length = 7)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "SEDE", nullable = false, length = 1)
	public char getSede() {
		return sede;
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

	@Column(name = "USUARIO_REGISTRO", nullable = false, length = 100)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
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
		if (!(other instanceof Sectores))
			return false;
		
		Sectores castOther = (Sectores) other;
		
		
		return ((this.getSectorId().equals(castOther.getSectorId()))
				&& (this.getCodigo().equals(castOther.getCodigo())) 
				&& (this.getNombre().equals(castOther.getNombre())));
	}


}
