package ni.gob.minsa.hsf.domain.estructura;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;


@Entity
@Table(name="CATEGORIAS_UNIDADES",schema="HSF")
public class CategoriaUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CATEGORIA_UNIDAD_ID", updatable=false)
	private long categoriaUnidadId;

	private BigDecimal codigo;

	private String nombre;

	private String pasivo;

	@Column(name="URL_IMAGEN")
	private String urlImagen;

    public CategoriaUnidad() {
    }

	public long getCategoriaUnidadId() {
		return this.categoriaUnidadId;
	}

	public void setCategoriaUnidadId(long categoriaUnidadId) {
		this.categoriaUnidadId = categoriaUnidadId;
	}

	public BigDecimal getCodigo() {
		return this.codigo;
	}

	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPasivo() {
		return this.pasivo;
	}

	public void setPasivo(String pasivo) {
		this.pasivo = pasivo;
	}

	public String getUrlImagen() {
		return this.urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

}