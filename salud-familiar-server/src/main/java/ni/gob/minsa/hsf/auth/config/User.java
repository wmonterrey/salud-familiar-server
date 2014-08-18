package ni.gob.minsa.hsf.auth.config;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.estructura.Unidades;

import org.hibernate.annotations.ForeignKey;
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
	private Nivel nivel;
	private EntidadesAdtvas entidad;
	private Unidades unidad;
	private String usuario;
	private Set<Authority> authorities;
	
	@Id
	@Column(name = "NOMBRE_USUARIO", nullable = false, length =50)
	@Size(min = 5, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@NotBlank
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
	@Size(min = 8, max = 150)
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?/]+$")
	@NotBlank
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "DESCRIPCION", nullable = true, length =250)
	@Size(max = 250)
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	@Column(name = "CORREO_ELECTRONICO", nullable = true, length =100)
	@Size(max = 100)
	@Email
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
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_ENTIDAD",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "USUARIOS_ENTIDAD_FK")
	public EntidadesAdtvas getEntidad() {
		return entidad;
	}
	public void setEntidad(EntidadesAdtvas entidad) {
		this.entidad = entidad;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CODIGO_UNIDAD",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "USUARIOS_UNIDAD_FK")
	public Unidades getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidades unidad) {
		this.unidad = unidad;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_NIVEL",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "USUARIOS_NIVEL_FK")
	public Nivel getNivel() {
		return nivel;
	}
	
	public void setNivel(Nivel nivel) {
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
