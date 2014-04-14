package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerElectricidadPorCodigo",
	query = "select cat from Electricidad cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ELECT")
public class Electricidad  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Electricidad() {
    }

  
}


