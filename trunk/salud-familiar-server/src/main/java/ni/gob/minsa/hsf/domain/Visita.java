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

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "hsf_visitas", catalog = "hsf")
public class Visita extends BaseMetaData implements Auditable{
	
	private String idVisita;
	private Familia familia;
	private Date fechaVisita;
	private char tipoVisita = '1';
	private String personaVisita;
	private Profesion personaVisitaProfesion;
	private MovilInfo movilInfo;
	
	public Visita() {
		
	}

	@Id
	@Column(name = "ID_VISITA", nullable = false, length = 50)
	public String getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(String idVisita) {
		this.idVisita = idVisita;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ID_FAMILIA")
	@ForeignKey(name = "VISITAS_FAMILIAS_FK")
	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	@Column(name = "FECHA_VISITA", nullable = false)
	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	@Column(name = "PERSONA_VISITA", nullable = false)
	public String getPersonaVisita() {
		return personaVisita;
	}

	public void setPersonaVisita(String personaVisita) {
		this.personaVisita = personaVisita;
	}
	
	@Column(name = "TIPO_VISITA", nullable = false)
	public char getTipoVisita() {
		return tipoVisita;
	}

	public void setTipoVisita(char tipoVisita) {
		this.tipoVisita = tipoVisita;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PROFESION",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "VISITAS_PROFESION_FK")
	public Profesion getPersonaVisitaProfesion() {
		return personaVisitaProfesion;
	}

	public void setPersonaVisitaProfesion(Profesion personaVisitaProfesion) {
		this.personaVisitaProfesion = personaVisitaProfesion;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
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
		return idVisita;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Visita))
			return false;
		
		Visita castOther = (Visita) other;

		return (this.getIdVisita().equals(castOther.getIdVisita()));
	}
	
}
