package ni.gob.minsa.hsf.domain.estructura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass  
public class BaseEntidadCreacion  
{  

	private Date fechaRegistro;
	private String usuarioRegistro;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO")
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name="USUARIO_REGISTRO", length = 50)
	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}  
