package ni.gob.minsa.hsf.domain.estructura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass  
public class BaseMetaData  
{  

	private Date created;
	private String createdBy;
	private char pasive = 0;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name="USUARIO_REGISTRO", length = 50)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="USUARIO_REGISTRO", nullable = false, length = 1)
	public char getPasive() {
		return pasive;
	}

	public void setPasive(char pasive) {
		this.pasive = pasive;
	}

}  
