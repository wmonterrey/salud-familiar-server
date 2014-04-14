package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerCulturaSanitariaPorCodigo",
	query = "select cat from CulturaSanitaria cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_CULT")
public class CulturaSanitaria  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public CulturaSanitaria() {
    }

  
}


