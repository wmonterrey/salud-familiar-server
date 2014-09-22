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
	private char estado='0';
	private String deviceid;
	private String phonenumber;
	
	public MovilInfo(){
		
	}
	
	public MovilInfo(char estado
			, String deviceid,
			String phonenumber, Date today){
		
		this.setEstado(estado);
		this.setDeviceid(deviceid);
		this.setPhonenumber(phonenumber);
	}
	
	@Column(name = "ESTADO", nullable = true)
	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
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
	
	@Override
	public String toString(){
		return estado+deviceid;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MovilInfo))
			return false;
		
		MovilInfo castOther = (MovilInfo) other;

		return ((this.getEstado()==(castOther.getEstado()))
				&&(this.getDeviceid().equals(castOther.getDeviceid())));
	}

}
