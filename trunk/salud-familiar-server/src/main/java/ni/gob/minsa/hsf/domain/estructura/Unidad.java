package ni.gob.minsa.hsf.domain.estructura;

//-----------------------------------------------
//Unidad.java
//-----------------------------------------------

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import ni.gob.minsa.hsf.domain.poblacion.DivisionPolitica;


@Entity
@Table(name="UNIDADES", schema="HSF")
@NamedQueries({
	@NamedQuery( 
			name="unidadesActivas",
			query="select tu from Unidad tu " +
					"where tu.pasivo='0' " +
					"order by tu.nombre"),
	@NamedQuery(							
			name="unidadesActivasPorEntidadAdtva",
			query="select tu from Unidad tu " +
					"where tu.pasivo='0' and " +
					"tu.entidadAdtva.entidadAdtvaId=:pEntidadId " +
					"order by tu.nombre"),
	@NamedQuery(							
			name="unidadesActivasPorEntidadYTipo",
			query="select tu from Unidad tu " +
					"where tu.pasivo='0' and " +
					"tu.entidadAdtva.entidadAdtvaId=:pEntidadId and " +
					"tu.tipoUnidad.tipoUnidadId=:pTipoUnidadId " +
					"order by tu.nombre"),
        @NamedQuery(
                       name="unidadPorFiltroNomUnd",
                       query="select tu from Unidad tu "
                        + " where tu.nombre like '%' || :pFiltroUnidad || '%' and tu.pasivo='0' "),
        @NamedQuery(
                       name="unidadPorFiltroNomUndDepartamento",
                       query="select tu from Unidad tu "
                        + " left join tu.municipio muni  "
                        + " left join muni.departamento depa"
                        + " where tu.nombre like '%' || :pFiltroUnidad || '%' and tu.pasivo='0' and depa.divisionPoliticaId = :pDepartamentoId "),
        @NamedQuery(
                       name="unidadPorFiltroNomUndMunicipio",
                       query="select tu from Unidad tu "
                        + " left join tu.municipio muni where tu.nombre like '%' || :pFiltroUnidad || '%' and tu.pasivo='0' "
                        + " and muni.codigoNacional = :pCodigoMunicipio "),        
        @NamedQuery(
                       name="unidadPorCodigo",
                       query="select tu from Unidad tu where tu.codigo = :pCodigo"),
        @NamedQuery( 
                        name="EntidadAdtvaPorCodigoMunicipio",
                        query = "select tu from Unidad tu left join tu.municipio muni where muni.codigoNacional = :pCodigoMunicipio "
                        + "and rowNum < 2"),
        @NamedQuery(
                        name="UnidadesPorCodigoMunicipio",
                        query = "select tu from Unidad tu left join tu.municipio muni where muni.codigoNacional = :pCodigoMunicipio and tu.pasivo='0'"
        + "order by tu.nombre "
                    )
})
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNIDADES_UNIDADID_GENERATOR", sequenceName="UNIDADES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDADES_UNIDADID_GENERATOR")
	@Column(name="UNIDAD_ID", updatable=false)
	private long unidadId;

	@Column(name="CODIGO",nullable=false, insertable=true,unique=true)
	private long codigo;

	private String conectividad;

	@Column(name="DECLARA_SECTOR")
	private String declaraSector;

	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	private String direccion;

	private String email;

	private String fax;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO", updatable=false)
	private Date fechaRegistro;

	@Column(name="GRUPO_ECONOMICO",length=1)
	private String grupoEconomico;

	private BigDecimal latitud;

	private BigDecimal longitud;

	@Column(name="NOMBRE",nullable=false,unique=true,updatable=true,insertable=true,length=100)
	private String nombre;

	@Column(name="PASIVO",nullable=false,unique=false,updatable=true,insertable=true,length=1)
	private String pasivo;

	private String telefono;

	@Column(name="USUARIO_REGISTRO")
	private String usuarioRegistro;
	
	//bi-directional many-to-one association to Divisionpolitica
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MUNICIPIO",referencedColumnName="CODIGO_NACIONAL")
	private DivisionPolitica municipio;

	//bi-directional many-to-one association to Tipounidade
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_UNIDAD",referencedColumnName="CODIGO")
	private TipoUnidad tipoUnidad;

	//bi-directional many-to-one association to Unidade
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ENTIDAD_ADTVA", referencedColumnName="CODIGO")
	private EntidadAdtva entidadAdtva;

	//asociaci�n unidireccional muchos a uno con Regimen
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REGIMEN",referencedColumnName="CODIGO")
	private Regimen regimen;

	//asociaci�n unidireccional muchos a uno con CategoriaUnidad
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORIA",referencedColumnName="CODIGO")
	private CategoriaUnidad categoriaUnidad;

    public Unidad() {
    }

    /**
     * Obtiene el identificador del objeto Unidad
     * 
     * @return Identificador del objeto Unidad
     */
	public long getUnidadId() {
		return this.unidadId;
	}

	/**
	 * Establece el identificador del objeto Unidad
	 * 
	 * @param unidadId Entero largo con el identifador del objeto
	 */
	public void setUnidadId(long unidadId) {
		this.unidadId = unidadId;
	}

	/**
	 * Obtiene el C�digo institucional para la unidad y que sirve de identificador
	 * para la relaci�n con otros objetos
	 * 
	 * @return C�digo institucional de la unidad
	 */
	public long getCodigo() {
		return this.codigo;
	}

	/**
	 * Establece el C�digo institucional para la unidad y que sirve de identificador
	 * para la relaci�n con otros objetos
	 * 
	 * @param codigo Entero largo que identifica a la unidad institucionalmente
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el valor que indica si la unidad tiene conexi�n con
	 * el sistema.
	 * 
	 * @return Cadena de caracteres con valor 0 � 1
	 */
	public String getConectividad() {
		return this.conectividad;
	}

	/**
	 * Establece el valor que indica si la unidad tiene 
	 * conectividad con el sistema, independientemente si dispone 
	 * de conexi�n a internet en la unidad de forma directa o utiliza
	 * otros medios para su conexi�n. 
	 * <p>
	 * Valor: 1=Si; 0=No
	 * 
	 * @param conectividad Cadena de caracteres con 0 � 1
	 */
	public void setConectividad(String conectividad) {
		this.conectividad = conectividad;
	}

	/**
	 * Obtiene el caracter 0 (No) o 1 (Si) que indica si
	 * la unidad tiene que declarar el sector al momento de
	 * registrar la actividad y metas de poblaci�n.
	 * 
	 * @return Cadena de caracteres con 0 � 1
	 */
	public String getDeclaraSector() {
		return this.declaraSector;
	}

	/**
	 * Establece si la unidad tiene que declarar el sector al momento 
	 * de registrar la actividad y metas de poblaci�n.  Por ejemplo, 
	 * para aquellas unidades, tal como los hospitales que operativamente 
	 * no pueden declarar el sector al cual pertenece la poblaci�n 
	 * atendida, deben configurarse con Declara Sector=No (0).
	 * <p>
	 * Valor 0: No; valor 1, Si.
	 * 
	 * @param declaraSector Cadena de caracteres con 0 � 1
	 */
	public void setDeclaraSector(String declaraSector) {
		this.declaraSector = declaraSector;
	}

	/**
	 * Obtiene la direcci�n en la cual se encuentran ubicadas las
	 * instalaciones centrales de la unidad de salud.
	 * 
	 * @return Cadena de caracteres con la direcci�n de la unidad
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * Establece la direcci�n en la cual se encuentran ubicadas las
	 * instalaciones centrales de la unidad de salud.
	 * 
	 * @param direccion Cadena de caracteres con la direcci�n de la unidad
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene el correo electr�nico o email utilizado por
	 * la unidad de salud para comunicaci�n institucional
	 * 
	 * @return Cadena de caracteres con el correo electr�nico
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Establece el correo electr�nico o email utilizado por la
	 * unidad para comunicaci�n institucional.
	 * 
	 * @param email Cadena de caracteres con el correo electr�nico
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene el n�mero de fax utilizado por la unidad de
	 * salud para comunicaci�n institucional
	 * 
	 * @return Cadena de caracteres con uno o mas n�meros de fax
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * Establece el n�mero de fax que es utilizado por la unidad
	 * de salud para comunicaci�n institucional.  Si existe m�s de
	 * un n�mero de fax estos deben ser separados por una coma y
	 * un espacio.
	 * 
	 * @param fax Cadena de caracteres con el n�mero de fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Obtiene la fecha en la cual se registr� la unidad
	 * 
	 * @return Fecha de Registro de la Unidad de Salud
	 */
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	/** 
	 * Establece la fecha en la cual se registr� la unidad.
	 * La fecha es establecida a nivel de base de datos de forma autom�tica
	 * utilizando la fecha del sistema y por tanto, la fecha que aqu� se
	 * establezca no ser� utilizada.
	 * 
	 * @param fechaRegistro Fecha de Registro
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getGrupoEconomico() {
		return this.grupoEconomico;
	}

	public void setGrupoEconomico(String grupoEconomico) {
		this.grupoEconomico = grupoEconomico;
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

	public DivisionPolitica getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(DivisionPolitica municipio) {
		this.municipio = municipio;
	}
	
	public TipoUnidad getTipoUnidad() {
		return this.tipoUnidad;
	}

	public void setTipoUnidad(TipoUnidad tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}
		
	// esta asignaci�n es importante para la creaci�n el arbol
	@Override
	public String toString() {
		return nombre;
	}

	public void setEntidadAdtva(EntidadAdtva entidadAdtva) {
		this.entidadAdtva = entidadAdtva;
	}

	public EntidadAdtva getEntidadAdtva() {
		return entidadAdtva;
	}

	public void setRegimen(Regimen regimen) {
		this.regimen = regimen;
	}

	public Regimen getRegimen() {
		return regimen;
	}

	public void setCategoriaUnidad(CategoriaUnidad categoriaUnidad) {
		this.categoriaUnidad = categoriaUnidad;
	}

	public CategoriaUnidad getCategoriaUnidad() {
		return categoriaUnidad;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	
}