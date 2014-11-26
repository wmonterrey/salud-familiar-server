package ni.gob.minsa.hsf.domain.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class Consolidado implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private Long familias;
	private BigDecimal viviendas;
	private Long personas;
	private Long familiasEst;
	private Long familiasDisp;
	private Long personasDisp;
	


	public Consolidado(String codigo, String nombre, Long familias,
			BigDecimal viviendas, Long personas, Long familiasEst,
			Long familiasDisp, Long personasDisp) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.familias = familias;
		this.viviendas = viviendas;
		this.personas = personas;
		this.familiasEst = familiasEst;
		this.familiasDisp = familiasDisp;
		this.personasDisp = personasDisp;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getFamilias() {
		return familias;
	}

	public void setFamilias(Long familias) {
		this.familias = familias;
	}

	public BigDecimal getViviendas() {
		return viviendas;
	}

	public void setViviendas(BigDecimal viviendas) {
		this.viviendas = viviendas;
	}

	public Long getPersonas() {
		return personas;
	}

	public void setPersonas(Long personas) {
		this.personas = personas;
	}

	public Long getFamiliasEst() {
		return familiasEst;
	}

	public void setFamiliasEst(Long familiasEst) {
		this.familiasEst = familiasEst;
	}

	public Long getFamiliasDisp() {
		return familiasDisp;
	}

	public void setFamiliasDisp(Long familiasDisp) {
		this.familiasDisp = familiasDisp;
	}

	public Long getPersonasDisp() {
		return personasDisp;
	}

	public void setPersonasDisp(Long personasDisp) {
		this.personasDisp = personasDisp;
	}

	

}
