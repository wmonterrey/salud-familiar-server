package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerReligionPorCodigo",
	query = "select cat from Religion cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_RELIG")
public class Religion  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Religion() {
    }

  
}


