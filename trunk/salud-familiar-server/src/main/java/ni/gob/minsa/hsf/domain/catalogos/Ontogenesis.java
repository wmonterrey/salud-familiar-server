package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerOntogenesisPorCodigo",
	query = "select cat from Ontogenesis cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ONTO")
public class Ontogenesis  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Ontogenesis() {
    }

  
}


