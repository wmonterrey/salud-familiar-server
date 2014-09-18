package ni.gob.minsa.hsf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.audit.Auditable;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;
import ni.gob.minsa.hsf.domain.estructura.Cie10;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_ENFERMEDADES", catalog = "HSF")
public class Enfermedades extends BaseMetaData implements Auditable{
	
	
	private String idEnfermedad;
	private Persona persona;
	private Cie10 enfermedad;
	private Date fechaOcurrencia;
	private Profesion personaAtendio;   
    
	public Enfermedades() {
		
	}
	
	@Id
	@Column(name = "ID_ENFERMEDAD", nullable = false, length = 50)
	public String getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(String idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	@ForeignKey(name = "ENFERMEDADES_PERSONAS_FK")
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Cie10.class)
    @JoinColumn(name="CODIGO_ENFERMEDAD",referencedColumnName="CODIGO_CIE10", nullable=false)
	@ForeignKey(name = "ENFERMEDADES_FK")
	public Cie10 getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Cie10 enfermedad) {
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
	@ForeignKey(name = "ENFERMEDADES_PROFESION_FK")
	public Profesion getPersonaAtendio() {
		return personaAtendio;
	}

	public void setPersonaAtendio(Profesion personaAtendio) {
		this.personaAtendio = personaAtendio;
	}
	
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")){
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return idEnfermedad;
	}

}
