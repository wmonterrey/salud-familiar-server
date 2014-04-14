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
	private Date ultimoCambio;
	private String deviceid;
	private String phonenumber;
	private String simserial;
	private Date today;	
	private String username; 
	
	public MovilInfo(){
		
	}
	
	public MovilInfo(String estado
			, Date ultimoCambio, String start, String end, String deviceid,
			String simserial, String phonenumber, Date today){
		
		this.setEstado(estado);
		this.setUltimoCambio(ultimoCambio);
		this.setDeviceid(deviceid);
		this.setPhonenumber(phonenumber);
		this.setToday(today);
	}
	
	@Column(name = "ESTADO", nullable = true, length = 15)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "ultimo_cambio", nullable = true)
	public Date getUltimoCambio() {
		return ultimoCambio;
	}

	public void setUltimoCambio(Date ultimoCambio) {
		this.ultimoCambio = ultimoCambio;
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

	@Column(name = "FECHA_REGISTRO", nullable = false)
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	@Column(name = "USUARIO", nullable = false, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "ELIMINADO", nullable = true)
	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Column(name = "SERIE_SIM", nullable = true, length = 50)
	public String getSimserial() {
		return simserial;
	}

	public void setSimserial(String simserial) {
		this.simserial = simserial;
	}

}
