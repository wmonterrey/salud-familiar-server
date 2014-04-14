package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerProfesionPorCodigo",
	query = "select cat from Profesion cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_PROFESION")
public class Profesion  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public Profesion() {
    }

  
}


