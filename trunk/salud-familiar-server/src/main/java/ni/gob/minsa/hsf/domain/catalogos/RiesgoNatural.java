package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerRiesgoNaturalPorCodigo",
	query = "select cat from RiesgoNatural cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_RIESGONAT")
public class RiesgoNatural  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public RiesgoNatural() {
    }

  
}


