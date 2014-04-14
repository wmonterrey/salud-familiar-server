package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerEnfAgudaPorCodigo",
	query = "select cat from EnfAguda cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ENFAGU")
public class EnfAguda  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public EnfAguda() {
    }

  
}


