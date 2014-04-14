package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerTipoParedPorCodigo",
	query = "select cat from TipoPared cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_PARED")
public class TipoPared  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public TipoPared() {
    }

  
}


