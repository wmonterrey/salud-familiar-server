package ni.gob.minsa.hsf.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovilInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Metadata del formulario movil
	private String estado;
	private Boolean eliminado;
	private String deviceid;
	private String phonenumber;
	
	public MovilInfo(){
		
	}
	
	public MovilInfo(String estado
			, String deviceid,
			String phonenumber, Date today){
		
		this.setEstado(estado);
		this.setDeviceid(deviceid);
		this.setPhonenumber(phonenumber);
	}
	
	@Column(name = "ESTADO", nullable = true, length = 15)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "ID_EQUIPO", nullable = true, length = 50)
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	@Column(name = "NUM_TELEFONO", nullable = true, length = 50)
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Column(name = "ELIMINADO", nullable = true)
	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}
