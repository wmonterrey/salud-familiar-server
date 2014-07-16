package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;
@NamedQueries({
@NamedQuery(
	name = "obtenerNivelPorCodigo",
	query = "select cat from Nivel cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_NIVELES")
public class Nivel extends Catalogo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Nivel() {
		
	}

}
