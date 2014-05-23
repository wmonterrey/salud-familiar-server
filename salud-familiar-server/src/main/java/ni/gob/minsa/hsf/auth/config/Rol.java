package ni.gob.minsa.hsf.auth.config;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

/**
 * Simple objeto de dominio que representa un rol
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "HSF_ROLES", catalog = "HSF")
public class Rol {
	private String authority;
	private Set<Authority> authorities;
	
	@Id
	@Column(name = "ROL", nullable = false, length =50)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@OneToMany(mappedBy = "rol")
	@IndexColumn(name = "rol", base=1)
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
