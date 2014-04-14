package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerFactRiesgoSocPorCodigo",
	query = "select cat from FactRiesgoSoc cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_FRSOC")
public class FactRiesgoSoc  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public FactRiesgoSoc() {
    }

  
}


