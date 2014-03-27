package ni.gob.minsa.hsf.domain.estructura;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import ni.gob.minsa.hsf.domain.poblacion.DivisionPolitica;



@Entity
@Table(name="ENTIDADES_ADTVAS", schema="HSF")
@NamedQueries({
	@NamedQuery( 
			name="entidadesAdtvasActivas",
			query="select tea from EntidadAdtva tea " +
					"where tea.pasivo='0' " +
					"order by tea.nombre"),
})
public class EntidadAdtva implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Comparator<EntidadAdtva> ORDEN_NOMBRE =
			new Comparator<EntidadAdtva>() {
        @Override
				public int compare(EntidadAdtva e1, EntidadAdtva e2) {
					return e1.nombre.compareTo(e2.nombre);
				}
	};

	@Id
	@Column(name="ENTIDAD_ADTVA_ID", updatable=false)
	private long entidadAdtvaId;
        
    @Column(name="CODIGO", updatable=false)
	private long codigo;

	private String email;

	private String fax;

        @Temporal( TemporalType.DATE)
	@Column(name="FECHA_REGISTRO", updatable=false)
	private Date fechaRegistro;

	private BigDecimal latitud;

	private BigDecimal longitud;

	private DivisionPolitica municipio;

	private String nombre;

	private String pasivo;

	private String telefono;

	@Column(name="USUARIO_REGISTRO", updatable=false)
	private String usuarioRegistro;

	//asociacion de autoreferencia bi-direccional muchos a uno
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPENDENCIA")
	private EntidadAdtva entidadSuperior;

	//asociacion de autoreferencia bi-direccional muchos a uno
	@OneToMany(mappedBy="entidadSuperior")
	private Set<EntidadAdtva> entidadesDependientes;

	//asociación bidireccional uno a muchos con Unidad
	@OneToMany(mappedBy="entidadAdtva",targetEntity=Unidad.class)
	private Set<Unidad> unidades;

	
    public EntidadAdtva() {
    }

    /**
     * Obtiene el identificador la Entidad Administrativa
     * 
     * @return Identificador unico de la Entidad Administrativa
     */
	public long getEntidadAdtvaId() {
		return this.entidadAdtvaId;
	}

	/**
	 * Establece el identificador de la Entidad Administrativa
	 * 
	 * @param entidadAdtvaId Entero largo con el identificador de la Entidad Administrativa
	 */
	public void setEntidadAdtvaId(long entidadAdtvaId) {
		this.entidadAdtvaId = entidadAdtvaId;
	}

	/**
	 * Obtiene el codigo institucional asignado a la
	 * entidad administrativa
	 * 
	 * @return Entero largo con el codigo institucional de la entidad
	 */
	public long getCodigo() {
		return this.codigo;
	}
	/**
	 * Establece el codigo institucional asignado a la 
	 * entidad administrativa, el cual debe ser unico y no nulo
	 * 
	 * @param codigo Entero largo con el codigo institucional de la entidad administrativa
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el correo electronico de la entidad administrativa. 
	 * Retorna nulo en caso de no tenerlo declarado.
	 * 
	 * @return Cadena de caracteres con el correo electronico
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Establece el correo electronico de la entidad administrativa
	 * y utilizada por esta para comunicacion institucional.
	 * 
	 * @param email Cadena de caracteres con el correo electronico
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene el numero de fax de la entidad administrativa. En caso de
	 * existir mas de un numero de fax, se retorna una cadena de caracteres
	 * con los numeros de fax separados por comas.  Los numeros de fax no
	 * utilizaran ningun caracter de separacion o patron, tal como el guion
	 * o el punto.
	 * 
	 * @return Cadena de caracteres con uno o mas numeros de fax
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * Establece el numero de fax de la entidad administrativa.  En caso
	 * de existir mas de un numero de fax, estos deberan ser registrados
	 * utilizando una coma como caracter de separacion.
	 * 
	 * @param fax Cadena de caracteres con uno o mas numeros de fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Obtiene la fecha en la cual se registró la entidad administravia
	 * 
	 * @return Fecha de Registro de la Entidad
	 */
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	/** 
	 * Establece la fecha en la cual se registró la entidad administrativa.
	 * La fecha es establecida a nivel de base de datos de forma automatica
	 * utilizando la fecha del sistema y por tanto, la fecha que aqui se
	 * establezca no sera utilizada.
	 * 
	 * @param fechaRegistro Fecha de Registro
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	// asociacion uni-directional muchos a uno con Divisionpolitica
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MUNICIPIO",referencedColumnName="CODIGO_NACIONAL")
	public DivisionPolitica getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(DivisionPolitica municipio) {
		this.municipio = municipio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(String pasivo) {
		this.pasivo = pasivo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	/**
	 * Obtiene la entidad administrativa de la cual depende la entidad 
	 * instanciada, es decir, la entidad administrativa superior o nodo
	 * padre.
	 * 
	 * @return Objeto con la entidad administrativa superior o nodo padre
	 */
	public EntidadAdtva getEntidadSuperior() {
		return this.entidadSuperior;
	}

	/**
	 * Establece la entidad administrativa de la cual depende la entidad
	 * instanciada, es decir, la entidad administrativa superior o nodo
	 * padre.
	 * <p>
	 * <b>Importante:</b>Esta propiedad no es utilizada en esta version
	 * del aplicativo y por tanto todas la entidades administrativas se
	 * corresponden con los SILAIS existentes.
	 * 
	 * @param entidadSuperior Objeto con la entidad adminstrativa padre
	 */
	public void setEntidadSuperior(EntidadAdtva entidadSuperior) {
		this.entidadSuperior = entidadSuperior;
	}
	
	/**
	 * Obtiene un conjunto de objetos de todas las entidades administrativas
	 * que dependen de la entidad instanciada.
	 * 
	 * @return Conjunto de objetos del tipo {@link EntidadAdtva}
	 */
	public Set<EntidadAdtva> getEntidadesDependientes() {
		return this.entidadesDependientes;
	}

	public void setEntidadesDependientes(Set<EntidadAdtva> entidadesDependientes) {
		this.entidadesDependientes = entidadesDependientes;
	}
	
	// esta asignacion es importante
	@Override
	public String toString() {
		return nombre;
	}
	/**
	 * Obtiene el conjunto de objetos {@link Unidad} que responden 
	 * administrativamente a la entidad instanciada.
	 * 
	 * @param unidades Conjunto de Objetos {@link Unidad}
	 */
	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}
	/**
	 * Establece el conjunto de objetos {@link Unidad} que responden 
	 * administrativamente a la entidad instanciada.
	 * 
	 * @return Conjunto de Objetos {@link Unidad}
	 */
	public Set<Unidad> getUnidades() {
		return unidades;
	}
        
        
	
}