package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerDiscapacidadPorCodigo",
	query = "select cat from Discapacidad cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_DISCAP")
public class Discapacidad  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Discapacidad() {
    }

  
}


