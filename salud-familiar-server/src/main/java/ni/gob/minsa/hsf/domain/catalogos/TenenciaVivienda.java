package ni.gob.minsa.hsf.domain.catalogos;

import javax.persistence.*;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;

@NamedQueries({
@NamedQuery(
	name = "obtenerTenenciaViviendaPorCodigo",
	query = "select cat from TenenciaVivienda cat where cat.codigo = :pCodigo"
	)
})
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="HSF_TVIV")
public class TenenciaVivienda  extends Catalogo {
		
    private static final long serialVersionUID = 1L;
	
    public TenenciaVivienda() {
    }

  
}


