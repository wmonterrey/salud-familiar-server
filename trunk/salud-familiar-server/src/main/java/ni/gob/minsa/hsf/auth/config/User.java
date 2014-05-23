package ni.gob.minsa.hsf.auth.config;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;



/**
 * Simple objeto de dominio que representa un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "HSF_USUARIOS_SISTEMA", catalog = "HSF")
public class User {
	private String username;
	private Date created;
	private String password;
	private String completeName;
	private String email;
	private Boolean enabled=true;
	private String nivel;
	private String entidad;
	private String usuario;
	private Set<Authority> authorities;
	
	@Id
	@Column(name = "NOMBRE_USUARIO", nullable = false, length =50)
	@Size(min = 5, max = 50, message = "Nombre de usuario debe contener m�nimo 5 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Solo alfanumerico sin espacios")
	@NotBlank(message = "No puede estar vac�o.")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "FECHA_REGISTRO", nullable = false)
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Column(name = "CONTRASENA", nullable = false, length =150)
	@Size(min = 5, max = 150, message = "Contrase�a debe contener m�nimo 8 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?/]+$", message = "Solo alfanumerico y caracteres especiales (!@#$%^&*()?/). No espacios")
	@NotBlank(message = "No puede estar vac�o.")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "DESCRIPCION", nullable = true, length =250)
	@Size(max = 250, message = "Descripci�n debe contener m�ximo 250 caracteres.")
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	@Column(name = "CORREO_ELECTRONICO", nullable = true, length =100)
	@Size(max = 100, message = "Email debe contener m�ximo 100 caracteres.")
	@Email(message = "No es una direcci�n de correo v�lida.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "HABILITADO", nullable = false)
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@IndexColumn(name = "username", base=0)
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	@Column(name = "ENTIDAD", nullable = false, length =10)
	@NotBlank(message = "No puede estar vac�o.")
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	@Column(name = "NIVEL", nullable = false, length =50)
	@NotBlank(message = "No puede estar vac�o.")
	public String getNivel() {
		return nivel;
	}
	
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	@Column(name = "USUARIO_REGISTRO", nullable = false, length =50)
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
