// -----------------------------------------------
// Catalogo.java
// -----------------------------------------------

package ni.gob.minsa.hsf.domain.estructura;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DiscriminatorOptions;


@Entity
@Table(name="CATALOGOS", schema="HSF")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DEPENDENCIA",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force = true)
public class Catalogo extends BaseEntidadCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

    public Catalogo() {
    }

}