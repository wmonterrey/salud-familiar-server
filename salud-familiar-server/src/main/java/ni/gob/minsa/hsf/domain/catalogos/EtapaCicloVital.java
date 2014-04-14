package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerEtapaCicloVitalPorCodigo",
	query = "select cat from EtapaCicloVital cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ECV")
public class EtapaCicloVital  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public EtapaCicloVital() {
    }

  
}


