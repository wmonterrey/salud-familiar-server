package ni.gob.minsa.hsf.domain.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.poblacion.Comunidades;

@Entity
@Table(name = "hsf_fam_est_comu", catalog = "hsf")
public class FamEstComunidad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String famEstComuId;
	private Comunidades comunidad;
	private long famEstimadas;

	@Id
	@Column(name = "FAMS_COMU_ID", length = 50)
	public String getFamEstComuId() {
		return famEstComuId;
	}

	public void setFamEstComuId(String famEstComuId) {
		this.famEstComuId = famEstComuId;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_COMU",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FAMEST_SECTOR_FK")
	public Comunidades getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}

	@Column(name = "FAM_ESTIMADAS", nullable = true)
	public long getFamEstimadas() {
		return famEstimadas;
	}
	
	public void setFamEstimadas(long famEstimadas) {
		this.famEstimadas = famEstimadas;
	}
}
