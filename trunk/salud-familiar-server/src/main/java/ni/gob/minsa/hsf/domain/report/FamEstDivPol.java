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
import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;

@Entity
@Table(name = "hsf_fam_est_muni", catalog = "hsf", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO_MUNI") })
public class FamEstDivPol implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String famEstDivPolId;
	private Divisionpolitica municipio;
	private long famEstimadas;

	@Id
	@Column(name = "FAMS_DIV_POL_ID", length = 50)
	public String getFamEstDivPolId() {
		return famEstDivPolId;
	}

	public void setFamEstDivPolId(String famEstDivPolId) {
		this.famEstDivPolId = famEstDivPolId;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_MUNI",referencedColumnName="CODIGO_NACIONAL", nullable=false)
	@ForeignKey(name = "FAMEST_MUNI_FK")
	public Divisionpolitica getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Divisionpolitica municipio) {
		this.municipio = municipio;
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
