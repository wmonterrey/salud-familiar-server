package ni.gob.minsa.hsf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_VISITAS", catalog = "HSF")
public class Visita extends BaseMetaData{
	
	private String idVisita;
	private Familia familia;
	private Date fechaVisita;
	private Integer numFicha;
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
	
	@Column(name = "NUM_FICHA", nullable = true)
	public Integer getNumFicha() {
		return numFicha;
	}

	public void setNumFicha(Integer numFicha) {
		this.numFicha = numFicha;
	}

	@Column(name = "PERSONA_VISITA", nullable = false)
	public String getPersonaVisita() {
		return personaVisita;
	}

	public void setPersonaVisita(String personaVisita) {
		this.personaVisita = personaVisita;
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
	
}
