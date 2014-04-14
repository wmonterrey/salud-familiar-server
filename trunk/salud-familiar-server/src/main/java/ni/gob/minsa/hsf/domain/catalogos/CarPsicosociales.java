package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerCarPsicosocialesPorCodigo",
	query = "select cat from CarPsicosociales cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_CPSC")
public class CarPsicosociales  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public CarPsicosociales() {
    }

  
}


