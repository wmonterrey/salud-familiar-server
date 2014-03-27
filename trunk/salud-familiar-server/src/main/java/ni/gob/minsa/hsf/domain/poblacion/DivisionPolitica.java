package ni.gob.minsa.hsf.domain.poblacion;

//-----------------------------------------------
//DivisionPolitica.java
//-----------------------------------------------

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Unidad;


@NamedQueries({
     @NamedQuery(
         name = "divPoliticaPorCodigoNac",
         query = "select div from DivisionPolitica div where div.codigoNacional = :pCodigo"
         ),
     @NamedQuery(
         name = "divPoliticaPorId",
         query = "select div from DivisionPolitica div where div.divisionPoliticaId = :pId"
         ),
      @NamedQuery(
         name = "departamentoPorId",
         query = "select div from DivisionPolitica div where div.divisionPoliticaId = :pId and "
                + "div.departamento is null"
         ),
      @NamedQuery(
         name = "departamentoPorCodigo",
         query = "select div from DivisionPolitica div where div.codigoNacional = :Codigo and "
                 + "div.departamento is null"
         ),
       @NamedQuery(
         name = "municipioPorId",
         query = "select div from DivisionPolitica div where div.divisionPoliticaId = :pId and "
                + "div.departamento is not null"
         ),
      @NamedQuery(
         name = "municipioPorCodigo",
         query = "select div from DivisionPolitica div where div.codigoNacional = :Codigo and "
                 + "div.departamento is not null"
         ),
      @NamedQuery(
         name = "departamentos",
         query = "select div from DivisionPolitica div where div.departamento is null"
      ),
      @NamedQuery(
         name = "municipiosPorDepartamento",
         query = "select muni from DivisionPolitica muni where "
                  + "muni.departamento.divisionPoliticaId = :pDepartamentoId"
      ),
      @NamedQuery(
         name = "municipiosPorCodigoDepartamento",
         query = "select muni from DivisionPolitica muni where "
                  + "muni.departamento.codigoNacional = :pCodigoDepartamento"
      )    
})
@Entity
@Table(name="DIVISIONPOLITICA", schema="HSF")
public class DivisionPolitica implements Serializable {
 private static final long serialVersionUID = 1L;

 @Id
 @Column(name="DIVISIONPOLITICA_ID", updatable=false)
 private long divisionPoliticaId;

 private BigDecimal administracion;

 @Column(name="CODIGO_ISO")
 private String codigoIso;

 @Column(name="CODIGO_NACIONAL")
 private String codigoNacional;

 @Temporal( TemporalType.TIMESTAMP)
 @Column(name="FECHA_REGISTRO", updatable=false)
 private Date fechaRegistro;

 private BigDecimal latitud;

 private BigDecimal longitud;

 private String nombre;

 private String pasivo;

 @Column(name="USUARIO_REGISTRO",updatable=false)
 private String usuarioRegistro;

 //bi-directional many-to-one association to Divisionpolitica
 @ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
 @JoinColumn(name="DEPENDENCIA")
 private DivisionPolitica departamento;

 //bi-directional many-to-one association to Divisionpolitica
 @OneToMany(mappedBy="departamento",fetch=FetchType.LAZY)
 private Set<DivisionPolitica> municipios;

 //bi-directional many-to-one association to Unidade
 @OneToMany(mappedBy="municipio",fetch=FetchType.LAZY)
 private Set<Unidad> unidades;

 public DivisionPolitica() {
 }

	public long getDivisionPoliticaId() {
		return this.divisionPoliticaId;
	}

	public void setDivisionpoliticaId(long divisionPoliticaId) {
		this.divisionPoliticaId = divisionPoliticaId;
	}

	public BigDecimal getAdministracion() {
		return this.administracion;
	}

	public void setAdministracion(BigDecimal administracion) {
		this.administracion = administracion;
	}

	public String getCodigoIso() {
		return this.codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	public String getCodigoNacional() {
		return this.codigoNacional;
	}

	public void setCodigoNacional(String codigoNacional) {
		this.codigoNacional = codigoNacional;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

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

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public DivisionPolitica getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(DivisionPolitica departamento) {
		this.departamento = departamento;
	}
	
	public Set<DivisionPolitica> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Set<DivisionPolitica> municipios) {
		this.municipios = municipios;
	}
	
	public Set<Unidad> getUnidades() {
		return this.unidades;
	}

	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}
	
}
	
