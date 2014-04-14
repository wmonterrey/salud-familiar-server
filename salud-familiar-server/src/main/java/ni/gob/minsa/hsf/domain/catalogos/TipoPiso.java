package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerTipoPisoPorCodigo",
	query = "select cat from TipoPiso cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_PISO")
public class TipoPiso  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public TipoPiso() {
    }

  
}


