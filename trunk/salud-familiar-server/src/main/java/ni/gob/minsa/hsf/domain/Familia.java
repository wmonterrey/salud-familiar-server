package ni.gob.minsa.hsf.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.IndexColumn;

import ni.gob.minsa.hsf.domain.poblacion.Comunidades;


@Entity
@Table(name = "FAMILIAS", catalog = "HSF")
public class Familia {
	
	private String idFamilia;
	private String codFamilia;
	private Comunidades comunidad;
	private Integer numVivienda;
	private Integer numFamilia;
	private String direccion;
	
	private Set<Persona> personas;
	
	public Familia() {
		
	}

	public Familia(String idFamilia, String codFamilia, Comunidades comunidad,
			Integer numVivienda, Integer numFamilia, String direccion) {
		super();
		this.idFamilia = idFamilia;
		this.codFamilia = codFamilia;
		this.numVivienda = numVivienda;
		this.numFamilia = numFamilia;
		this.direccion = direccion;
	}

	@Id
	@Column(name = "ID_FAMILIA", nullable = false, length = 30)
	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	@Column(name = "COD_FAMILIA", nullable = false, length = 30)
	public String getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(String codFamilia) {
		this.codFamilia = codFamilia;
	}
	
	@ManyToOne
	@JoinColumn(name="COMUNIDAD_ID")
	@ForeignKey(name = "FAMILIAS_COMUNIDAD_FK")
	public Comunidades getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}

	@Column(name = "NUM_VIVIENDA", nullable = false)
	public Integer getNumVivienda() {
		return numVivienda;
	}

	public void setNumVivienda(Integer numVivienda) {
		this.numVivienda = numVivienda;
	}

	@Column(name = "NUM_FAMILIA", nullable = false)
	public Integer getNumFamilia() {
		return numFamilia;
	}

	public void setNumFamilia(Integer numFamilia) {
		this.numFamilia = numFamilia;
	}

	@Column(name = "DIRECCION", nullable = true, length = 500)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@OneToMany(mappedBy = "familia")
	@IndexColumn(name = "COMUNIDAD_ID")
	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}
	
}
