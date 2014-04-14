package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerCrisisParanormativaPorCodigo",
	query = "select cat from CrisisParanormativa cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_CRSPNR")
public class CrisisParanormativa  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public CrisisParanormativa() {
    }

  
}


