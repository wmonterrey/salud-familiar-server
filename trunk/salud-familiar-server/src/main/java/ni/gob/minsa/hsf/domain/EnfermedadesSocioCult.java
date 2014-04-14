package ni.gob.minsa.hsf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.catalogos.EnfSocioC;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_ENF_SOCIOC", catalog = "HSF")
public class EnfermedadesSocioCult {
	
	
	private String idEnfSocioC;
	private Persona persona;
	private EnfSocioC enfermedad;
	private Date fechaOcurrencia;
	private Profesion personaAtendio;
	private MovilInfo movilInfo;   
    
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

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}


	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

}
