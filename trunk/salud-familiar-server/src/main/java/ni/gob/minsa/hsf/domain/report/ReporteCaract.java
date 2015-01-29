package ni.gob.minsa.hsf.domain.report;

import java.io.Serializable;

public class ReporteCaract implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String valor;
	private Long cuenta;
	private float porcentaje;
	
	public ReporteCaract(String valor, Long cuenta, Float porcentaje) {
		super();
		this.valor = valor;
		this.cuenta = cuenta;
		this.porcentaje = porcentaje;
	}

	public ReporteCaract() {
		
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	

	
	
}
