package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerGDPorCodigo",
	query = "select cat from GrupoDispensarial cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_GD")
public class GrupoDispensarial extends Catalogo {

    private static final long serialVersionUID = 1L;
	
    public GrupoDispensarial() {
    }

}


