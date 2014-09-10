package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;
@NamedQueries({
@NamedQuery(
	name = "obtenerSiNoNsPorCodigo",
	query = "select cat from SiNoNs cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_SN")
public class SiNoNs extends Catalogo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SiNoNs() {
		
	}

}
