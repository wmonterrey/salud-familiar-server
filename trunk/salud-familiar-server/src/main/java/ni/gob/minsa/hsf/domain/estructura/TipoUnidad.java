package ni.gob.minsa.hsf.domain.estructura;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="TIPOS_UNIDADES", schema="HSF")
@NamedQueries({
	@NamedQuery(
			name="tiposUnidadesActivas",
			query="select ttu from TipoUnidad ttu " +
					"where ttu.pasivo='0' " +
					"order by ttu.nombre")
})
public class TipoUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIPO_UNIDAD_ID", updatable=false)
	private long tipoUnidadId;

	private String nombre;

	@Column(name="ORDEN",insertable=true,nullable=true,unique=false)
	private Long orden;
	
	private String pasivo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO", updatable=false)
	private Date fechaRegistro;

	@Column(name="USUARIO_REGISTRO")
	private String usuarioRegistro;

	@Column(name="CODIGO",insertable=true,nullable=false)
	private long codigo;
	
	//bi-directional many-to-one association to Unidad
	@OneToMany(mappedBy="tipoUnidad")
	private Set<Unidad> unidades;

    public TipoUnidad() {
    }

	public long getTipounidadId() {
		return this.tipoUnidadId;
	}

	public void setTipounidadId(long tipoUnidadId) {
		this.tipoUnidadId = tipoUnidadId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Unidad> getUnidades() {
		return this.unidades;
	}

	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Long getOrden() {
		return orden;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public String getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(String pasivo) {
		this.pasivo = pasivo;
	}
	
	// esta asignacion es importante
	@Override
	public String toString() {
		return nombre;
	}
}