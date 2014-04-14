package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerDepBasuraPorCodigo",
	query = "select cat from DepBasura cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_DEPBAS")
public class DepBasura  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public DepBasura() {
    }

  
}


