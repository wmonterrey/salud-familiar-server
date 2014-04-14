package ni.gob.minsa.hsf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.catalogos.EnfCronica;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_ENF_CRONICAS", catalog = "HSF")
public class EnfermedadesCronicas {
	
	
	private String idEnfCronicas;
	private Persona persona;
	private EnfCronica enfermedad;
	private Date fechaOcurrencia;
	private Profesion personaAtendio;
	private MovilInfo movilInfo;   
    
	public EnfermedadesCronicas() {
		
	}
	
	@Id
	@Column(name = "ID_ENF_CRONICA", nullable = false, length = 50)
	public String getIdEnfCronicas() {
		return idEnfCronicas;
	}

	public void setIdEnfCronicas(String idEnfCronicas) {
		this.idEnfCronicas = idEnfCronicas;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	@ForeignKey(name = "CRONICAS_PERSONAS_FK")
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ENFERMEDAD",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "CRONICAS_ENFERMEDAD_FK")
	public EnfCronica getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(EnfCronica enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	public void setFechaOcurrencia(Date fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PROFESION",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "CRONICAS_PROFESION_FK")
	public Profesion getPersonaAtendio() {
		return personaAtendio;
	}

	public void setPersonaAtendio(Profesion personaAtendio) {
		this.personaAtendio = personaAtendio;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}


	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

}
