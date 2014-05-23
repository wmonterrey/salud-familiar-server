package ni.gob.minsa.hsf.auth.config;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.hibernate.annotations.ForeignKey;

/**
 * Simple objeto de dominio que representa un rol para un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "HSF_USUARIOS_ROLES", catalog = "HSF")
public class Authority {
	
	private AuthorityId authId;
	private User user;
	private Rol rol;
	
	
	@Id
	public AuthorityId getAuthId() {
		return authId;
	}
	public void setAuthId(AuthorityId authId) {
		this.authId = authId;
	}
	
	@ManyToOne
	@JoinColumn(name="NOMBRE_USUARIO", insertable = false, updatable = false)
	@ForeignKey(name = "ROLES_USUARIOS_FK")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="ROL", insertable = false, updatable = false)
	@ForeignKey(name = "ROLES_ROL_FK")
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
