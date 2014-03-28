package ni.gob.minsa.hsf.domain.estructura;

// Generated Mar 28, 2014 11:26:42 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Regimenes generated by hbm2java
 */
@Entity
@Table(name = "REGIMENES", catalog = "HSF", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODIGO"),
		@UniqueConstraint(columnNames = "NOMBRE") })
public class Regimenes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -682475271434188605L;
	private long regimenId;
	private String nombre;
	private long codigo;
	private char pasivo;

	public Regimenes() {
	}

	public Regimenes(long regimenId, String nombre, long codigo, char pasivo) {
		this.regimenId = regimenId;
		this.nombre = nombre;
		this.codigo = codigo;
		this.pasivo = pasivo;
	}

	@Id
	@Column(name = "REGIMEN_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getRegimenId() {
		return this.regimenId;
	}

	public void setRegimenId(long regimenId) {
		this.regimenId = regimenId;
	}

	@Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "CODIGO", unique = true, nullable = false, precision = 10, scale = 0)
	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "PASIVO", nullable = false, length = 1)
	public char getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

}