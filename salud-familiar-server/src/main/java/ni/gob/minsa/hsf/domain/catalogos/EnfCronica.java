package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerEnfCronicaPorCodigo",
	query = "select cat from EnfCronica cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ENFCRO")
public class EnfCronica  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public EnfCronica() {
    }

  
}


