package ni.gob.minsa.hsf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ni.gob.minsa.hsf.domain.audit.Auditable;
import ni.gob.minsa.hsf.domain.catalogos.CarPsicosociales;
import ni.gob.minsa.hsf.domain.catalogos.CulturaSanitaria;
import ni.gob.minsa.hsf.domain.catalogos.TenenciaVivienda;
import ni.gob.minsa.hsf.domain.catalogos.TipoPared;
import ni.gob.minsa.hsf.domain.catalogos.TipoPiso;
import ni.gob.minsa.hsf.domain.catalogos.TipoTecho;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "hsf_fact_socioec", catalog = "hsf")
public class FactSocioEconomicos extends BaseMetaData implements Auditable{
	
	
	private String idFactSocioEc;
	private Visita visita;
	private TipoPiso tipoPiso;
	private TipoTecho tipoTecho;
	private TipoPared tipoPared;
	private CulturaSanitaria culturaSanitaria;
	private CarPsicosociales carPsicosociales;
	private String satNecBasicas;
	private TenenciaVivienda tenenciaVivienda;
	private String accionesComunitarias;
	private String obsFactSocioEc;
	
	public FactSocioEconomicos() {
		
	}

	@Id
	@Column(name = "ID_FACT_SOCIOEC", nullable = false, length = 50)
	public String getIdFactSocioEc() {
		return idFactSocioEc;
	}

	public void setIdFactSocioEc(String idFactSocioEc) {
		this.idFactSocioEc = idFactSocioEc;
	}

	@OneToOne(optional=false)
	@JoinColumn(name="ID_VISITA")
	@ForeignKey(name = "SOCIOEC_VISITAS_FK")
	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PISO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_PISO_FK")
	public TipoPiso getTipoPiso() {
		return tipoPiso;
	}

	public void setTipoPiso(TipoPiso tipoPiso) {
		this.tipoPiso = tipoPiso;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_TECHO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_TECHO_FK")
	public TipoTecho getTipoTecho() {
		return tipoTecho;
	}

	public void setTipoTecho(TipoTecho tipoTecho) {
		this.tipoTecho = tipoTecho;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PARED",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_PARED_FK")
	public TipoPared getTipoPared() {
		return tipoPared;
	}

	public void setTipoPared(TipoPared tipoPared) {
		this.tipoPared = tipoPared;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_CULTURA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_CULTURA_FK")
	public CulturaSanitaria getCulturaSanitaria() {
		return culturaSanitaria;
	}

	public void setCulturaSanitaria(CulturaSanitaria culturaSanitaria) {
		this.culturaSanitaria = culturaSanitaria;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_PSICO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_PSICO_FK")
	public CarPsicosociales getCarPsicosociales() {
		return carPsicosociales;
	}

	public void setCarPsicosociales(CarPsicosociales carPsicosociales) {
		this.carPsicosociales = carPsicosociales;
	}

	@Column(name = "NEC_BASICAS", nullable = true, length = 2)
	public String getSatNecBasicas() {
		return satNecBasicas;
	}

	public void setSatNecBasicas(String satNecBasicas) {
		this.satNecBasicas = satNecBasicas;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_TENENCIA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FACTSOC_TENENCIA_FK")
	public TenenciaVivienda getTenenciaVivienda() {
		return tenenciaVivienda;
	}

	public void setTenenciaVivienda(TenenciaVivienda tenenciaVivienda) {
		this.tenenciaVivienda = tenenciaVivienda;
	}

	@Column(name = "ACCIONES_COMUNITARIAS", nullable = true, length = 255)
	public String getAccionesComunitarias() {
		return accionesComunitarias;
	}

	public void setAccionesComunitarias(String accionesComunitarias) {
		this.accionesComunitarias = accionesComunitarias;
	}
	
	@Column(name = "OBSERVACIONES", nullable = true, length = 255)
	public String getObsFactSocioEc() {
		return obsFactSocioEc;
	}

	public void setObsFactSocioEc(String obsFactSocioEc) {
		this.obsFactSocioEc = obsFactSocioEc;
	}
	
	@Override
	public String toString(){
		return idFactSocioEc;
	}
	
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")){
			return false;
		}
		return true;
	}
}
