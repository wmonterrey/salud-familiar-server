package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerTamanoFamPorCodigo",
	query = "select cat from TamanoFam cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_TAMFAM")
public class TamanoFam  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public TamanoFam() {
    }

  
}


