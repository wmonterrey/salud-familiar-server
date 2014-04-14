package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerAnimalesDomesticosPorCodigo",
	query = "select cat from AnimalesDomesticos cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ANIMDOM")
public class AnimalesDomesticos  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public AnimalesDomesticos() {
    }

  
}


