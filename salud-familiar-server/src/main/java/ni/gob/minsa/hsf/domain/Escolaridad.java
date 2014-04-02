package ni.gob.minsa.hsf.domain;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerEscolaridadPorCodigo",
	query = "select cat from Escolaridad cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="ESCDA")
public class Escolaridad  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Escolaridad() {
    }

  
}


