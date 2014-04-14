package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerFactRiesgoModPorCodigo",
	query = "select cat from FactRiesgoMod cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_FRMOD")
public class FactRiesgoMod  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public FactRiesgoMod() {
    }

  
}


