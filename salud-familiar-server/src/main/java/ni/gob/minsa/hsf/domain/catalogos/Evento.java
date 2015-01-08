package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;
@NamedQueries({
@NamedQuery(
	name = "obtenerEventoPorCodigo",
	query = "select cat from Evento cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_EVENTO")
public class Evento extends Catalogo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Evento() {
		
	}

}
