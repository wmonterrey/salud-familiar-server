package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerCalidadAguaPorCodigo",
	query = "select cat from CalidadAgua cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_CALAGUA")
public class CalidadAgua  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public CalidadAgua() {
    }

  
}


