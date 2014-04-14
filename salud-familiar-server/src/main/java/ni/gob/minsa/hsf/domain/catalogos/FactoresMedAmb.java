package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerFactoresMedAmbPorCodigo",
	query = "select cat from FactoresMedAmb cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_FACTMA")
public class FactoresMedAmb  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public FactoresMedAmb() {
    }

  
}


