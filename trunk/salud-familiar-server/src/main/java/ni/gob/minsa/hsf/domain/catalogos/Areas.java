package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;
@NamedQueries({
@NamedQuery(
	name = "obtenerAreasPorCodigo",
	query = "select cat from Areas cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_AREAS")
public class Areas extends Catalogo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Areas() {
		
	}

}
