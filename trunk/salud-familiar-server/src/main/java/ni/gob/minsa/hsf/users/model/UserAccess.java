package ni.gob.minsa.hsf.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HSF_USUARIOS_ACCESOS", catalog = "HSF")
public class UserAccess {

	private int id;
	private String username;
	private Date loginDate;
	private String sessionId;
	private String remoteIpAddress;
	private Date logoutDate;
	private String logoutRefererUrl;
	
	
	public UserAccess() {
		super();
	}
	
	
	public UserAccess(String username, Date loginDate, String sessionId,
			String remoteIpAddress) {
		super();
		this.username = username;
		this.loginDate = loginDate;
		this.sessionId = sessionId;
		this.remoteIpAddress = remoteIpAddress;
	}

	@Id
	@GenericGenerator(name="idautoinc2" , strategy="increment")
	@GeneratedValue(generator="idautoinc2")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NOMBRE_USUARIO", nullable = false, length =50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "FECHA_ENTRADA")
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	@Column(name = "ID_SESION", nullable = true, length =100)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name = "DIRECCION_IP", nullable = true, length =50)
	public String getRemoteIpAddress() {
		return remoteIpAddress;
	}
	public void setRemoteIpAddress(String remoteIpAddress) {
		this.remoteIpAddress = remoteIpAddress;
	}
	@Column(name = "FECHA_SALIDA")
	public Date getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	@Column(name = "URL_SALIDA", nullable = true, length =100)
	public String getLogoutRefererUrl() {
		return logoutRefererUrl;
	}
	public void setLogoutRefererUrl(String logoutRefererUrl) {
		this.logoutRefererUrl = logoutRefererUrl;
	}
	@Override
	public String toString(){
		return username;
	}
}