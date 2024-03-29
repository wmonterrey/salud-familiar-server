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
import ni.gob.minsa.hsf.domain.catalogos.EnfSocioC;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "hsf_enf_socioc", catalog = "hsf")
public class EnfermedadesSocioCult extends BaseMetaData implements Auditable{
	
	
	private String idEnfSocioC;
	private Persona persona;
	private EnfSocioC enfermedad;
	private Date fechaOcurrencia;
	private Profesion personaAtendio;  
    
	public EnfermedadesSocioCult() {
		
	}
	
	@Id
	@Column(name = "ID_ENF_SOCIOC", nullable = false, length = 50)
	public String getIdEnfSocioC() {
		return idEnfSocioC;
	}

	public void setIdEnfSocioC(String idEnfSocioC) {
		this.idEnfSocioC = idEnfSocioC;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	@ForeignKey(name = "SOCIOC_PERSONAS_FK")
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ENFERMEDAD",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "SOCIOC_ENFERMEDAD_FK")
	public EnfSocioC getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(EnfSocioC enfermedad) {
		this.enfermedad = enfermedad;
	}

	@Column(name = "FECHA_OCURRENCIA", nullable = false)
	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	public void setFechaOcurrencia(Date fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PROFESION",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "SOCIOC_PROFESION_FK")
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
		return idEnfSocioC;
	}

}
