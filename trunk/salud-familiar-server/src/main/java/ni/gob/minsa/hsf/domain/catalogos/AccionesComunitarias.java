package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerAccionesComunitariasPorCodigo",
	query = "select cat from AccionesComunitarias cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ACOM")
public class AccionesComunitarias  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public AccionesComunitarias() {
    }

  
}


