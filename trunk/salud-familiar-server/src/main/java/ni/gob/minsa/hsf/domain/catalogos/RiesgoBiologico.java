package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerRiesgoBiologicoPorCodigo",
	query = "select cat from RiesgoBiologico cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_RIESGOBIO")
public class RiesgoBiologico  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public RiesgoBiologico() {
    }

  
}


