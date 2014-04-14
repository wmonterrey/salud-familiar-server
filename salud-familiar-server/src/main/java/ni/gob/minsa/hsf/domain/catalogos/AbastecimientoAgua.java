package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerAbastecimientoAguaPorCodigo",
	query = "select cat from AbastecimientoAgua cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ABASTAGUA")
public class AbastecimientoAgua  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public AbastecimientoAgua() {
    }

  
}


