package ni.gob.minsa.hsf.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
// default package
// Generated 06-17-2013 02:38:16 PM by Hibernate Tools 3.4.0.CR1

/**
 * TblviviendaId generated by hbm2java
 */
@Embeddable
public class TblviviendaId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idVivienda;
	private int idPob;
	private String codeVivienda;
	private String codeManzana;

	public TblviviendaId() {
	}

	public TblviviendaId(int idVivienda, int idPob, String codeVivienda,
			String codeManzana) {
		this.idVivienda = idVivienda;
		this.idPob = idPob;
		this.codeVivienda = codeVivienda;
		this.codeManzana = codeManzana;
	}

	@Column(name = "id_vivienda", nullable = false)
	public int getIdVivienda() {
		return this.idVivienda;
	}

	public void setIdVivienda(int idVivienda) {
		this.idVivienda = idVivienda;
	}

	@Column(name = "id_pob", nullable = false)
	public int getIdPob() {
		return this.idPob;
	}

	public void setIdPob(int idPob) {
		this.idPob = idPob;
	}

	@Column(name = "code_vivienda", nullable = false, length = 5)
	public String getCodeVivienda() {
		return this.codeVivienda;
	}

	public void setCodeVivienda(String codeVivienda) {
		this.codeVivienda = codeVivienda;
	}

	@Column(name = "code_manzana", nullable = false, length = 5)
	public String getCodeManzana() {
		return this.codeManzana;
	}

	public void setCodeManzana(String codeManzana) {
		this.codeManzana = codeManzana;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblviviendaId))
			return false;
		TblviviendaId castOther = (TblviviendaId) other;

		return (this.getIdVivienda() == castOther.getIdVivienda())
				&& (this.getIdPob() == castOther.getIdPob())
				&& ((this.getCodeVivienda() == castOther.getCodeVivienda()) || (this
						.getCodeVivienda() != null
						&& castOther.getCodeVivienda() != null && this
						.getCodeVivienda().equals(castOther.getCodeVivienda())))
				&& ((this.getCodeManzana() == castOther.getCodeManzana()) || (this
						.getCodeManzana() != null
						&& castOther.getCodeManzana() != null && this
						.getCodeManzana().equals(castOther.getCodeManzana())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdVivienda();
		result = 37 * result + this.getIdPob();
		result = 37
				* result
				+ (getCodeVivienda() == null ? 0 : this.getCodeVivienda()
						.hashCode());
		result = 37
				* result
				+ (getCodeManzana() == null ? 0 : this.getCodeManzana()
						.hashCode());
		return result;
	}

}
