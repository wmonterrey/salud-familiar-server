package ni.gob.minsa.hsf.domain.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.audit.Auditable;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;

@Entity
@Table(name = "hsf_fam_est_comu", catalog = "hsf", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO_COMU") })
public class FamEstComunidad implements Serializable, Auditable{
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
	@JoinColumn(name="CODIGO_COMU",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "FAMEST_SECTOR_FK")
	public Comunidades getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}

	@Column(name = "FAM_ESTIMADAS", nullable = false)
	public long getFamEstimadas() {
		return famEstimadas;
	}
	
	public void setFamEstimadas(long famEstimadas) {
		this.famEstimadas = famEstimadas;
	}
	
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")){
			return false;
		}
		return true;
	}
}
