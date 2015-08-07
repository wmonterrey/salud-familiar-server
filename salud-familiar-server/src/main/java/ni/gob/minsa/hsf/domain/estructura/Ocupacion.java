package ni.gob.minsa.hsf.domain.estructura;

import java.sql.Timestamp;
import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
            name = "obtenerOcupacionPorCodigo",
            query = "select ocup from Ocupacion ocup where ocup.codigo = :pCodigo"
            ),
        @NamedQuery(
            name = "obtenerOcupacionPorId",
            query = "select ocup from Ocupacion ocup where ocup.ocupacionId = :pId"
            )
})
@Entity
@Table(name="OCUPACIONES"
    ,schema="general"
    , uniqueConstraints = @UniqueConstraint(columnNames="CODIGO") 
)
public class Ocupacion  implements java.io.Serializable {


    private static final long serialVersionUID = 1784014777114946625L;
	
    private long ocupacionId;
    private String nombre;
    private String codigo;
    private String observacion;
    private boolean pasivo;
    private String usuarioRegistro;
    private  Timestamp fechaRegistro;
 
    
    public Ocupacion() {
    }

	
    public Ocupacion(long ocupacionId, 
            String nombre, 
            String codigo,
            boolean pasivo, 
            String usuarioRegistro, 
            Timestamp fechaRegistro) {
        this.ocupacionId = ocupacionId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.pasivo = pasivo;
        this.usuarioRegistro = usuarioRegistro;
        this.fechaRegistro = fechaRegistro;
    }
    
    public Ocupacion(long ocupacionId, 
            String nombre,
            String codigo,
            String observacion,
            boolean pasivo, 
            String usuarioRegistro,
            Timestamp fechaRegistro){
        
       this.ocupacionId = ocupacionId;
       this.nombre = nombre;
       this.codigo = codigo;
       this.observacion = observacion;
       this.pasivo = pasivo;
       this.usuarioRegistro = usuarioRegistro;
       this.fechaRegistro = fechaRegistro;
    }
   
    @Id 
    @Column(name="OCUPACION_ID", nullable=false, precision=10, scale=0)
    public long getOcupacionId() {
        return this.ocupacionId;
    }
    
    public void setOcupacionId(long ocupacionId) {
        this.ocupacionId = ocupacionId;
    }
    
    @Column(name="NOMBRE", nullable=false, length=400)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="CODIGO", nullable=false, length=40)
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @Column(name="OBSERVACION", length=800)
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    @Column(name="PASIVO", nullable=false, precision=1, scale=0)
    public boolean isPasivo() {
        return this.pasivo;
    }
    
    public void setPasivo(boolean pasivo) {
        this.pasivo = pasivo;
    }
    
    @Column(name="USUARIO_REGISTRO", nullable=false, length=400)
    public String getUsuarioRegistro() {
        return this.usuarioRegistro;
    }
    
    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
    
    @Column(name="FECHA_REGISTRO", nullable=false)
    public Timestamp getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    } 
    @Override
	public String toString(){
		return nombre;
	}
    @Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ocupacion))
			return false;
		
		Ocupacion castOther = (Ocupacion) other;
		
		return (this.getOcupacionId() == castOther.getOcupacionId())
				&& (this.getCodigo().equals(castOther.getCodigo())) 
						&& (this.getNombre().equals(castOther.getNombre()));
	}
}


