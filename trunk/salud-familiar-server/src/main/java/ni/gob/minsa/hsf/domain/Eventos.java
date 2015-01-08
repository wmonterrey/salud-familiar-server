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
import ni.gob.minsa.hsf.domain.catalogos.Evento;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "hsf_eventos", catalog = "hsf")
public class Eventos extends BaseMetaData implements Auditable{
	
	
	private String idEvento;
	private Persona persona;
	private Date fechaVisita;
	private Evento evento;
	private Date fechaEvento;
	private String observacion;   
    
	public Eventos() {
		
	}
	
	@Id
	@Column(name = "ID_EVENTO", nullable = false, length = 50)
	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	@ForeignKey(name = "EVENTOS_PERSONAS_FK")
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Column(name = "FECHA_VISITA", nullable = false)
	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	@Column(name = "FECHA_EVENTO", nullable = false)
	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	@Column(name = "OBSERVACION", nullable = true, length = 250)
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_EVENTO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "EVENTOS_EVENTO_FK")
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
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
		return idEvento;
	}

}
