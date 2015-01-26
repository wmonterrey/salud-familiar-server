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
import ni.gob.minsa.hsf.domain.poblacion.Sectores;

@Entity
@Table(name = "hsf_fam_est_sectores", catalog = "hsf", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO_SECTOR") })
public class FamEstSector implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String famEstSectorId;
	private Sectores sector;
	private long famEstimadas;

	@Id
	@Column(name = "FAMS_SEC_ID", length = 50)
	public String getFamEstSectorId() {
		return famEstSectorId;
	}

	public void setFamEstSectorId(String famEstSectorId) {
		this.famEstSectorId = famEstSectorId;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_SECTOR",referencedColumnName="CODIGO", nullable=false)
	@ForeignKey(name = "FAMEST_SECTOR_FK")
	public Sectores getSector() {
		return sector;
	}

	public void setSector(Sectores sector) {
		this.sector = sector;
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
