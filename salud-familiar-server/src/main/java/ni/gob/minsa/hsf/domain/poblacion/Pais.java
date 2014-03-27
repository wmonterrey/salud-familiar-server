package ni.gob.minsa.hsf.domain.poblacion;


import java.sql.Timestamp;
import javax.persistence.*;


@Entity
@NamedQueries({
        @NamedQuery(
            name = "paisPorCodigoAlfados",
            query = "select p from Pais p where p.codigoAlfados = :pCodigo"
            ),
        @NamedQuery(
            name = "paises",
            query = "select p from Pais p"
            )
        
})
@Table(name="PAISES"
    ,schema="HSF"
    , uniqueConstraints = {@UniqueConstraint(columnNames={"CODIGO_NUMERICO", "CODIGO_ALFADOS", "CODIGO_ALFATRES", "CODIGO_ISO"})} 
)
public class Pais implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private long paisId;
    private String nombre;
    private String codigoNumerico;
    private String codigoAlfados;
    private String codigoAlfatres;
    private String codigoIso;
    private Timestamp fechaRegistro;
    private String usuarioRegistro;
    
    public Pais() {
    }

    public Pais(long paisId, String nombre,
    		String codigoNumerico,
    		String codigoAlfados,
    		String codigoAlfatres, 
    		String codigoIso, 
    		Timestamp fechaRegistro, 
    		String usuarioRegistro) {
    	
       this.paisId = paisId;
       this.nombre = nombre;
       this.codigoNumerico = codigoNumerico;
       this.codigoAlfados = codigoAlfados;
       this.codigoAlfatres = codigoAlfatres;
       this.codigoIso = codigoIso;
       this.fechaRegistro = fechaRegistro;
       this.usuarioRegistro = usuarioRegistro;
    }
   
    @Id 
    @Column(name="PAIS_ID", nullable=false, precision=10, scale=0)
    public long getPaisId() {
        return this.paisId;
    }
    
    public void setPaisId(long paisId) {
        this.paisId = paisId;
    }
    
    @Column(name="NOMBRE", nullable=false, length=100)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="CODIGO_NUMERICO", unique=true, nullable=false, length=80)
    public String getCodigoNumerico() {
        return this.codigoNumerico;
    }
    
    public void setCodigoNumerico(String codigoNumerico) {
        this.codigoNumerico = codigoNumerico;
    }
    
    @Column(name="CODIGO_ALFADOS", unique=true, nullable=false, length=8)
    public String getCodigoAlfados() {
        return this.codigoAlfados;
    }
    
    public void setCodigoAlfados(String codigoAlfados) {
        this.codigoAlfados = codigoAlfados;
    }
    
    @Column(name="CODIGO_ALFATRES", unique=true, nullable=false, length=12)
    public String getCodigoAlfatres() {
        return this.codigoAlfatres;
    }
    
    public void setCodigoAlfatres(String codigoAlfatres) {
        this.codigoAlfatres = codigoAlfatres;
    }
    
    @Column(name="CODIGO_ISO", nullable=false, length=80)
    public String getCodigoIso() {
        return this.codigoIso;
    }
    
    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }
    
    @Column(name="FECHA_REGISTRO", nullable=false, length=7)
    public Timestamp getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Column(name="USUARIO_REGISTRO", nullable=false, length=20)
    public String getUsuarioRegistro() {
        return this.usuarioRegistro;
    }
    
    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

}
