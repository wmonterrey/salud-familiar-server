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
@Table(name = "hsf_familias", catalog = "hsf", uniqueConstraints = @UniqueConstraint(columnNames = "COD_FAMILIA"))
public class Familia extends BaseMetaData implements Auditable{
	
	private String idFamilia;
	private String codFamilia;
	private Comunidades comunidad;
	private Integer numVivienda;
	private String numFamilia;
	private Integer numFicha;
	private String direccion;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private String telefonoContacto;
	private char dispensarizada = '0';
	private char infoCompleta = '0';
	
	
	public Familia() {
		
	}

	public Familia(String idFamilia, String codFamilia, Comunidades comunidad,
			Integer numVivienda, String numFamilia, String direccion) {
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

	@Column(name = "COD_FAMILIA", nullable = false, length = 100)
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

	@Column(name = "NUM_VIVIENDA", nullable = true, length = 50)
	public Integer getNumVivienda() {
		return numVivienda;
	}

	public void setNumVivienda(Integer numVivienda) {
		this.numVivienda = numVivienda;
	}

	@Column(name = "NUM_FAMILIA", nullable = false, length = 50)
	public String getNumFamilia() {
		return numFamilia;
	}

	public void setNumFamilia(String numFamilia) {
		this.numFamilia = numFamilia;
	}
	
	@Column(name = "NUM_FICHA", nullable = true)
	public Integer getNumFicha() {
		return numFicha;
	}

	public void setNumFicha(Integer numFicha) {
		this.numFicha = numFicha;
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

	@Column(name = "DISPENSARIZADA", nullable = true)
	public char getDispensarizada() {
		return dispensarizada;
	}

	public void setDispensarizada(char dispensarizada) {
		this.dispensarizada = dispensarizada;
	}
	
	@Column(name = "INFO_COMPLETA", nullable = true)
	public char getInfoCompleta() {
		return infoCompleta;
	}

	public void setInfoCompleta(char infoCompleta) {
		this.infoCompleta = infoCompleta;
	}

	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")||fieldname.matches("dispensarizada")||fieldname.matches("infoCompleta")){
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return idFamilia;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Familia))
			return false;
		
		Familia castOther = (Familia) other;

		return (this.getIdFamilia().equals(castOther.getIdFamilia()));
	}

}
