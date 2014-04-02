package ni.gob.minsa.hsf.domain;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerOcupacionPorCodigo",
	query = "select cat from Ocupacion cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_OCUPA")
public class Ocupacion  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Ocupacion() {
    }

  
}


