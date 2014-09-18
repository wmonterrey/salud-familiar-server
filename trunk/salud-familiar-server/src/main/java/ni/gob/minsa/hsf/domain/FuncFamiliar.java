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
import ni.gob.minsa.hsf.domain.catalogos.EtapaCicloVital;
import ni.gob.minsa.hsf.domain.catalogos.Ontogenesis;
import ni.gob.minsa.hsf.domain.catalogos.TamanoFam;
import ni.gob.minsa.hsf.domain.estructura.BaseMetaData;
import ni.gob.minsa.hsf.domain.estructura.Catalogo;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "HSF_FUNC_FAM", catalog = "HSF")
public class FuncFamiliar extends BaseMetaData implements Auditable{
	
	
	private String idFuncFamiliar;
	private Visita visita;
	private TamanoFam tamFamilia;
	private Ontogenesis ontogenesis;
	private EtapaCicloVital etapaCicloVital;
	private String crisisNormativa;
	private String crisisParanormativa;
	private String usoMedTradicional;
	private String obsFuncFamiliar;
	 
	public FuncFamiliar() {
		
	}
	
	
	@Id
	@Column(name = "ID_FUNC_FAM", nullable = false, length = 50)
	public String getIdFuncFamiliar() {
		return idFuncFamiliar;
	}



	public void setIdFuncFamiliar(String idFuncFamiliar) {
		this.idFuncFamiliar = idFuncFamiliar;
	}


	@OneToOne(optional=false)
	@JoinColumn(name="ID_VISITA")
	@ForeignKey(name = "FUNCFAM_VISITAS_FK")
	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_TAMANO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FUNCFAM_TAMANO_FK")
	public TamanoFam getTamFamilia() {
		return tamFamilia;
	}


	public void setTamFamilia(TamanoFam tamFamilia) {
		this.tamFamilia = tamFamilia;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ONTO",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FUNCFAM_ONTO_FK")
	public Ontogenesis getOntogenesis() {
		return ontogenesis;
	}


	public void setOntogenesis(Ontogenesis ontogenesis) {
		this.ontogenesis = ontogenesis;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Catalogo.class)
    @JoinColumn(name="CODIGO_ETAPA",referencedColumnName="CODIGO", nullable=true)
	@ForeignKey(name = "FUNCFAM_ETAPA_FK")
	public EtapaCicloVital getEtapaCicloVital() {
		return etapaCicloVital;
	}


	public void setEtapaCicloVital(EtapaCicloVital etapaCicloVital) {
		this.etapaCicloVital = etapaCicloVital;
	}

	@Column(name = "CRISIS_NORMATIVA", nullable = true, length = 255)
	public String getCrisisNormativa() {
		return crisisNormativa;
	}


	public void setCrisisNormativa(String crisisNormativa) {
		this.crisisNormativa = crisisNormativa;
	}

	@Column(name = "CRISIS_PARANORM", nullable = true, length = 255)
	public String getCrisisParanormativa() {
		return crisisParanormativa;
	}


	public void setCrisisParanormativa(String crisisParanormativa) {
		this.crisisParanormativa = crisisParanormativa;
	}

	@Column(name = "MEDICINA_TRAD", nullable = true, length = 255)
	public String getUsoMedTradicional() {
		return usoMedTradicional;
	}


	public void setUsoMedTradicional(String usoMedTradicional) {
		this.usoMedTradicional = usoMedTradicional;
	}


	public String getObsFuncFamiliar() {
		return obsFuncFamiliar;
	}

	@Column(name = "OBSERVACIONES", nullable = true, length = 255)
	public void setObsFuncFamiliar(String obsFuncFamiliar) {
		this.obsFuncFamiliar = obsFuncFamiliar;
	}
	
	@Override
	public String toString(){
		return idFuncFamiliar;
	}
	
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")){
			return false;
		}
		return true;
	}

}
