package ni.gob.minsa.hsf.domain.estructura;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import ni.gob.minsa.hsf.serializer.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@MappedSuperclass  
public class BaseEntidadCreacion  
{  
	@Column(name="FECHA_REGISTRO")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@JsonSerialize(using = CustomDateSerializer.class)
	private DateTime fechaRegistro;

    @NotNull(message="No se encontró el usuario que realiza la transacción.  Es posible que la sesión de trabajo haya finalizado.")
	@Column(name="USUARIO_REGISTRO",updatable=false,nullable=false)
	private String usuarioRegistro;

	public DateTime getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}  
