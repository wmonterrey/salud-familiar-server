package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerTipoTechoPorCodigo",
	query = "select cat from TipoTecho cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_TECHO")
public class TipoTecho  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public TipoTecho() {
    }

  
}


