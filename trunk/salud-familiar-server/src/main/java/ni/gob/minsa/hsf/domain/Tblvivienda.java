package ni.gob.minsa.hsf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
// default package
// Generated 06-17-2013 02:38:16 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.ForeignKey;


/**
 * Tblvivienda generated by hbm2java
 */

@Entity
@Table(name = "tblvivienda", catalog = "hsf")
public class Tblvivienda implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TblviviendaId id;
	private Comunidad comunidad;
	private String coordenadas;
	private String direccion;

	public Tblvivienda() {
	}

	public Tblvivienda(TblviviendaId id) {
		this.id = id;
	}

	@Id
	public TblviviendaId getId() {
		return this.id;
	}

	public void setId(TblviviendaId id) {
		this.id = id;
	}
	
	@Column(name = "coordenadas", nullable = true, length = 100)
	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	@Column(name = "direccion", nullable = true, length = 255)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@ManyToOne
	@JoinColumn(name="id_pob", insertable = false, updatable = false)
	@ForeignKey(name = "FK_VIV_COM")
	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

}
