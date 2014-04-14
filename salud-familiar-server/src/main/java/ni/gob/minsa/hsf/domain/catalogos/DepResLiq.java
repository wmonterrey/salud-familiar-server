package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerDepResLiqPorCodigo",
	query = "select cat from DepResLiq cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_DEPRLIQ")
public class DepResLiq  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public DepResLiq() {
    }

  
}


