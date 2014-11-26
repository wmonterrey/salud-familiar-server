package ni.gob.minsa.hsf.domain.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;

@Entity
@Table(name = "hsf_fam_est_silais", catalog = "hsf")
public class FamEstEntidad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String famEstEntidadId;
	private EntidadesAdtvas entidad;
	private long famEstimadas;

	@Id
	@Column(name = "FAMS_ENTIDAD_ADTVA_ID", length = 50)
	public String getFamEstEntidadId() {
		return famEstEntidadId;
	}

	public void setFamEstEntidadId(String famEstEntidadId) {
		this.famEstEntidadId = famEstEntidadId;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_SILAIS",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FAMEST_SILAIS_FK")
	public EntidadesAdtvas getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadesAdtvas entidad) {
		this.entidad = entidad;
	}
	
	
	@Column(name = "FAM_ESTIMADAS", nullable = true)
	public long getFamEstimadas() {
		return famEstimadas;
	}
	
	public void setFamEstimadas(long famEstimadas) {
		this.famEstimadas = famEstimadas;
	}
}
