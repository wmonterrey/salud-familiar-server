package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerEnfSocioCPorCodigo",
	query = "select cat from EnfSocioC cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_ENFSOC")
public class EnfSocioC  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public EnfSocioC() {
    }

  
}


