package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerDepExcretasPorCodigo",
	query = "select cat from DepExcretas cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_DEPEXC")
public class DepExcretas  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public DepExcretas() {
    }

  
}


