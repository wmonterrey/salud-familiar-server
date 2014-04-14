package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerCombCocinarPorCodigo",
	query = "select cat from CombCocinar cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_COMB")
public class CombCocinar  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public CombCocinar() {
    }

  
}


