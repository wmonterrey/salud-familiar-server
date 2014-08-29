package ni.gob.minsa.hsf.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.gob.minsa.hsf.domain.audit.Auditable;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;


@Entity
@Table(name = "HSF_FAMILIAS", catalog = "HSF", uniqueConstraints = @UniqueConstraint(columnNames = "COD_FAMILIA"))
public class Familia extends BaseMetaData implements Auditable{
	
	private String idFamilia;
	private String codFamilia;
	private Comunidades comunidad;
	private Integer numVivienda;
	private Integer numFamilia;
	private String direccion;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String telefonoContacto;
	
	
	public Familia() {
		
	}

	public Familia(String idFamilia, String codFamilia, Comunidades comunidad,
			Integer numVivienda, Integer numFamilia, String direccion) {
		super();
		this.idFamilia = idFamilia;
		this.codFamilia = codFamilia;
		this.numVivienda = numVivienda;
		this.numFamilia = numFamilia;
		this.direccion = direccion;
	}

	@Id
	@Column(name = "ID_FAMILIA", nullable = false, length = 50)
	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	@Column(name = "COD_FAMILIA", nullable = false, length = 30)
	public String getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(String codFamilia) {
		this.codFamilia = codFamilia;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="COMUNIDAD", referencedColumnName="CODIGO")
	@ForeignKey(name = "FAMILIAS_COMUNIDAD_FK")
	public Comunidades getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}

	@Column(name = "NUM_VIVIENDA", nullable = false)
	public Integer getNumVivienda() {
		return numVivienda;
	}

	public void setNumVivienda(Integer numVivienda) {
		this.numVivienda = numVivienda;
	}

	@Column(name = "NUM_FAMILIA", nullable = false)
	public Integer getNumFamilia() {
		return numFamilia;
	}

	public void setNumFamilia(Integer numFamilia) {
		this.numFamilia = numFamilia;
	}

	@Column(name = "DIRECCION", nullable = true, length = 500)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name = "LATITUD", nullable = true)
	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	@Column(name = "LONGITUD", nullable = true)
	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Column(name = "TELEFONO_CONTACTO", nullable = true, length = 20)
	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("fechaRegistro")||fieldname.matches("usuarioRegistro")||fieldname.matches("numVivienda")||fieldname.matches("numFamilia")){
			return false;
		}
		return true;
	}

}
