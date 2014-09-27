package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.CaractHigSanitarias;
import ni.gob.minsa.hsf.domain.Enfermedades;
import ni.gob.minsa.hsf.domain.EnfermedadesSocioCult;
import ni.gob.minsa.hsf.domain.FactSocioEconomicos;
import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.FuncFamiliar;
import ni.gob.minsa.hsf.domain.MovilInfo;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.Visita;
import ni.gob.minsa.hsf.domain.catalogos.AbastecimientoAgua;
import ni.gob.minsa.hsf.domain.catalogos.AccionesComunitarias;
import ni.gob.minsa.hsf.domain.catalogos.AnimalesDomesticos;
import ni.gob.minsa.hsf.domain.catalogos.CalidadAgua;
import ni.gob.minsa.hsf.domain.catalogos.CarPsicosociales;
import ni.gob.minsa.hsf.domain.catalogos.CombCocinar;
import ni.gob.minsa.hsf.domain.catalogos.CrisisNormativa;
import ni.gob.minsa.hsf.domain.catalogos.CrisisParanormativa;
import ni.gob.minsa.hsf.domain.catalogos.CulturaSanitaria;
import ni.gob.minsa.hsf.domain.catalogos.DepBasura;
import ni.gob.minsa.hsf.domain.catalogos.DepExcretas;
import ni.gob.minsa.hsf.domain.catalogos.DepResLiq;
import ni.gob.minsa.hsf.domain.catalogos.Discapacidad;
import ni.gob.minsa.hsf.domain.catalogos.Electricidad;
import ni.gob.minsa.hsf.domain.catalogos.EnfSocioC;
import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.EtapaCicloVital;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoNoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoSoc;
import ni.gob.minsa.hsf.domain.catalogos.FactoresMedAmb;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Ontogenesis;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoBiologico;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoMeteorologico;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoNatural;
import ni.gob.minsa.hsf.domain.catalogos.RiesgoSocial;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.catalogos.SiNoNs;
import ni.gob.minsa.hsf.domain.catalogos.TamanoFam;
import ni.gob.minsa.hsf.domain.catalogos.TenenciaVivienda;
import ni.gob.minsa.hsf.domain.catalogos.TipoPared;
import ni.gob.minsa.hsf.domain.catalogos.TipoPiso;
import ni.gob.minsa.hsf.domain.catalogos.TipoTecho;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.service.CaractHigSanitariasService;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.Cie10Service;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;
import ni.gob.minsa.hsf.service.EnfermedadesService;
import ni.gob.minsa.hsf.service.EnfermedadesSocioCultService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.FactSocioEconomicosService;
import ni.gob.minsa.hsf.service.FamiliaService;
import ni.gob.minsa.hsf.service.FuncFamiliarService;
import ni.gob.minsa.hsf.service.PersonaService;
import ni.gob.minsa.hsf.service.SectoresService;
import ni.gob.minsa.hsf.service.VisitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a hsf
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/info/*")
public class HsfController {
	private static final Logger logger = LoggerFactory.getLogger(HsfController.class);
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	@Resource(name="divPoliticaService")
	private DivisionPoliticaService divPoliticaService;
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
	@Resource(name="sectorService")
	private SectoresService sectorService;
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	@Resource(name="familiaService")
	private FamiliaService familiaService;
	@Resource(name="visitaService")
	private VisitaService visitaService;
	@Resource(name="caractHigSanitariasService")
	private CaractHigSanitariasService caractHigSanitariasService;
	@Resource(name="factSocioEconomicosService")
	private FactSocioEconomicosService factSocioEconomicosService;
	@Resource(name="funcFamiliarService")
	private FuncFamiliarService funcFamiliarService;
	@Resource(name="personaService")
	private PersonaService personaService;
	@Resource(name="enfermedadesService")
	private EnfermedadesService enfermedadesService;
	@Resource(name="enfermedadesSocioCultService")
	private EnfermedadesSocioCultService enfermedadesSocioCultService;
	@Resource(name="cie10Service")
	private Cie10Service cie10Service;
	
	@RequestMapping(value = "newHsf", method = RequestMethod.GET)
    public String initCreationForm(Model model) throws ParseException { 	
    	logger.debug("Crear nueva HSF");
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas();
    	List<Profesion> profesiones = catalogoService.getProfesiones();
    	List<SiNoNs> sinons = catalogoService.getSiNoNs();
    	List<Sexo> sexos = catalogoService.getSexo();
    	List<Etnia> etnias = catalogoService.getEtnia();
    	List<Escolaridad> escolaridades = catalogoService.getEscda();
    	List<Ocupacion> ocupaciones = catalogoService.getOcupacion();
    	List<Religion> religiones = catalogoService.getReligion();
    	List<FactRiesgoNoMod> frnms = catalogoService.getFactRiesgoNoMod();
    	List<FactRiesgoMod> frms = catalogoService.getFactRiesgoMod();
    	List<FactRiesgoSoc> frss = catalogoService.getFactRiesgoSoc();
    	List<Discapacidad> discps = catalogoService.getDiscapacidad();
    	List<GrupoDispensarial> gds = catalogoService.getGrupoDispensarial();
    	List<AnimalesDomesticos> animales = catalogoService.getAnimalesDomesticos();
    	List<RiesgoNatural> rgnats = catalogoService.getRiesgoNatural();
    	List<RiesgoMeteorologico> rgmets = catalogoService.getRiesgoMeteorologico();
    	List<RiesgoBiologico> rgbios = catalogoService.getRiesgoBiologico();
    	List<RiesgoSocial> rgsocs = catalogoService.getRiesgoSocial();
    	List<FactoresMedAmb> factoresMedAmbs = catalogoService.getFactoresMedAmb();
    	List<CombCocinar> combCocinars = catalogoService.getCombCocinar();
    	List<AbastecimientoAgua> abastecimientoAguas = catalogoService.getAbastecimientoAgua();
    	List<CalidadAgua> calidadAguas = catalogoService.getCalidadAgua();
    	List<Electricidad> electricidads = catalogoService.getElectricidad();
    	List<DepExcretas> depExcretas = catalogoService.getDepExcretas();
    	List<DepBasura> depBasuras = catalogoService.getDepBasura();
    	List<DepResLiq> depResLiqs = catalogoService.getDepResLiq();
    	List<TipoPiso> pisos = catalogoService.getTipoPiso();
    	List<TipoTecho> techos = catalogoService.getTipoTecho();
    	List<TipoPared> paredes = catalogoService.getTipoPared();
    	List<CulturaSanitaria> culturasSanitaria = catalogoService.getCulturaSanitaria();
    	List<CarPsicosociales> carsPsicosociales = catalogoService.getCarPsicosociales();
    	List<TenenciaVivienda> tenenciasVivienda = catalogoService.getTenenciaVivienda();
    	List<AccionesComunitarias> accionesComunitarias = catalogoService.getAccionesComunitarias();
    	List<TamanoFam> tamanos = catalogoService.getTamanoFam();
    	List<Ontogenesis> ontos = catalogoService.getOntogenesis();
    	List<EtapaCicloVital> etapasCicloVital = catalogoService.getEtapaCicloVital();
    	List<CrisisNormativa> crisisNormativas = catalogoService.getCrisisNormativa();
    	List<CrisisParanormativa> crisisParanormativas = catalogoService.getCrisisParanormativa();
    	List<EnfSocioC> enfermedadessocs = catalogoService.getEnfSocioC();
    	model.addAttribute("entidades", entidades);
    	model.addAttribute("profesiones", profesiones);
    	model.addAttribute("sinons", sinons);
    	model.addAttribute("sexos", sexos);
    	model.addAttribute("etnias", etnias);
    	model.addAttribute("escolaridades", escolaridades);
    	model.addAttribute("ocupaciones", ocupaciones);
    	model.addAttribute("religiones", religiones);
    	model.addAttribute("frnms", frnms);
    	model.addAttribute("frss", frss);
    	model.addAttribute("frms", frms);
    	model.addAttribute("discps", discps);
    	model.addAttribute("gds", gds);
    	model.addAttribute("animales", animales);
    	model.addAttribute("rgnats", rgnats);
    	model.addAttribute("rgmets", rgmets);
    	model.addAttribute("rgbios", rgbios);
    	model.addAttribute("rgsocs", rgsocs);
    	model.addAttribute("factoresMedAmbs", factoresMedAmbs);
    	model.addAttribute("combCocinars", combCocinars);
    	model.addAttribute("abastecimientoAguas", abastecimientoAguas);
    	model.addAttribute("calidadAguas", calidadAguas);
    	model.addAttribute("electricidads", electricidads);
    	model.addAttribute("depExcretas", depExcretas);
    	model.addAttribute("depBasuras", depBasuras);
    	model.addAttribute("depResLiqs", depResLiqs);
    	model.addAttribute("pisos", pisos);
    	model.addAttribute("techos", techos);
    	model.addAttribute("paredes", paredes);
    	model.addAttribute("culturasSanitaria", culturasSanitaria);
    	model.addAttribute("carsPsicosociales", carsPsicosociales);
    	model.addAttribute("tenenciasVivienda", tenenciasVivienda);
    	model.addAttribute("accionesComunitarias", accionesComunitarias);
    	model.addAttribute("tamanos", tamanos);
    	model.addAttribute("ontos", ontos);
    	model.addAttribute("etapasCicloVital", etapasCicloVital);
    	model.addAttribute("crisisNormativas", crisisNormativas);
    	model.addAttribute("crisisParanormativas", crisisParanormativas);
    	model.addAttribute("enfermedadessocs", enfermedadessocs);
    	return "hsf/create";
	}
	
	@RequestMapping( value="newFamiliaVisita", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFamiliaVisitaForm( @RequestParam(value="comunidad", required=true ) String comunidad
			, @RequestParam( value="numVivienda", required=true ) Integer numVivienda
			, @RequestParam( value="numFamilia", required=true ) Integer numFamilia
			, @RequestParam( value="direccion", required=true ) String direccion
			, @RequestParam( value="idFamilia", required=false, defaultValue="" ) String idFamilia
			, @RequestParam( value="idVisita", required=false, defaultValue="" ) String idVisita
			, @RequestParam( value="numFicha", required=true ) Integer numFicha
			, @RequestParam( value="personaVisita", required=true) String personaVisita
			, @RequestParam( value="personaVisitaProfesion", required=true) String personaVisitaProfesion
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Familia familia = new Familia();
		familia.setComunidad(comunidadService.getComunidad(comunidad));
		familia.setNumVivienda(numVivienda);
		familia.setNumFamilia(numFamilia);
		familia.setNumFicha(numFicha);
		familia.setDireccion(direccion);
		familia.setCodFamilia(comunidad+"-"+numVivienda+"-"+numFamilia);
		if (idFamilia.equals("")){
			idFamilia = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		familia.setIdFamilia(idFamilia);
		familia.setCreatedBy(authentication.getName());
		familia.setCreated(new Date());
		familia.setInfoCompleta(estaCompleta(idFamilia));
		familiaService.addFamilia(familia);
		
		
		Visita visita = new Visita();
		visita.setFamilia(familia);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		visita.setFechaVisita(date);
		visita.setPersonaVisita(personaVisita);
		visita.setPersonaVisitaProfesion(catalogoService.getProfesion(personaVisitaProfesion));
		MovilInfo movilInfo = new MovilInfo();
		WebAuthenticationDetails wad  = (WebAuthenticationDetails) authentication.getDetails();
		movilInfo.setDeviceid(wad.getRemoteAddress());
		if (idVisita.equals("")){
			idVisita = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		visita.setIdVisita(idVisita);
		visita.setCreatedBy(authentication.getName());
		visita.setCreated(new Date());
		visita.setMovilInfo(movilInfo);
		visitaService.addVisita(visita);
		return createJsonResponse(visita);
	}
	
	private char estaCompleta(String idFamilia) {
		Familia familia = this.familiaService.getFamilia(idFamilia);
		CaractHigSanitarias carHigSan = this.caractHigSanitariasService.getCaractHigSanitariasFamilia(idFamilia);
        FactSocioEconomicos factSocEc = this.factSocioEconomicosService.getFactSocioEconomicosFamilia(idFamilia);
        FuncFamiliar funcFam = this.funcFamiliarService.getFuncFamiliarFamilia(idFamilia);
        List<Persona> personas = this.personaService.getPersonas(idFamilia);
        List<Visita> visitas = this.visitaService.getVisitas(idFamilia);
        if(familia!=null && carHigSan!=null && factSocEc!=null && funcFam!=null && personas!=null && visitas!=null){
        	return '1';
        }
        else{
        	return '0';
        }
	}

	@RequestMapping( value="editFamilia", method=RequestMethod.POST)
	public ResponseEntity<String> processEditionFamiliaForm( @RequestParam(value="comunidad", required=true ) String comunidad
			, @RequestParam( value="numVivienda", required=true ) Integer numVivienda
			, @RequestParam( value="numFamilia", required=true ) Integer numFamilia
			, @RequestParam( value="direccion", required=true ) String direccion
			, @RequestParam( value="idFamilia", required=true) String idFamilia
			, @RequestParam( value="numFicha", required=true ) Integer numFicha) throws ParseException
	{
		Familia familia = this.familiaService.getFamilia(idFamilia);
		familia.setComunidad(comunidadService.getComunidad(comunidad));
		familia.setNumVivienda(numVivienda);
		familia.setNumFamilia(numFamilia);
		familia.setNumFicha(numFicha);
		familia.setDireccion(direccion);
		familia.setCodFamilia(comunidad+"-"+numVivienda+"-"+numFamilia);
		familia.setInfoCompleta(estaCompleta(idFamilia));
		familiaService.addFamilia(familia);
		return createJsonResponse(familia);
	}
	
	@RequestMapping( value="editVisita", method=RequestMethod.POST)
	public ResponseEntity<String> processEditionVisitaForm(@RequestParam( value="idVisita", required=true) String idVisita
			, @RequestParam( value="personaVisita", required=true) String personaVisita
			, @RequestParam( value="personaVisitaProfesion", required=true) String personaVisitaProfesion
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Visita visita = this.visitaService.getVisita(idVisita);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		visita.setFechaVisita(date);
		visita.setPersonaVisita(personaVisita);
		visita.setPersonaVisitaProfesion(catalogoService.getProfesion(personaVisitaProfesion));
		MovilInfo movilInfo = new MovilInfo();
		WebAuthenticationDetails wad  = (WebAuthenticationDetails) authentication.getDetails();
		movilInfo.setDeviceid(wad.getRemoteAddress());
		visita.setMovilInfo(movilInfo);
		visitaService.addVisita(visita);
		return createJsonResponse(visita);
	}
	
	@RequestMapping( value="newCarHigSan", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationCaractHigForm( @RequestParam( value="idFamilia", required=true) String idFamilia
			, @RequestParam( value="hacinamiento", required=false) String hacinamiento
			, @RequestParam( value="animalesDom", required=false) String animalesDom
			, @RequestParam( value="riesgoNatural", required=false) String riesgoNatural
			, @RequestParam( value="riesgoMeteorologico", required=false) String riesgoMeteorologico
			, @RequestParam( value="riesgoSocial", required=false) String riesgoSocial
			, @RequestParam( value="riesgoBiologico", required=false) String riesgoBiologico
			, @RequestParam( value="factoresMedAmb", required=false) String factoresMedAmb
			, @RequestParam( value="combCocinar", required=false) String combCocinar
			, @RequestParam( value="aAgua", required=false) String aAgua
			, @RequestParam( value="cAgua", required=false) String cAgua
			, @RequestParam( value="electricidad", required=false) String electricidad
			, @RequestParam( value="depExcretas", required=false) String depExcretas
			, @RequestParam( value="depBasura", required=false) String depBasura
			, @RequestParam( value="depResLiq", required=false) String depResLiq
			, @RequestParam( value="obsCaractHig", required=false) String obsCaractHig
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita
			, @RequestParam( value="idCaractHig", required=false, defaultValue="" ) String idCaractHig) throws ParseException 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CaractHigSanitarias carHigSan =  new CaractHigSanitarias();
		if (idCaractHig.equals("")){
			idCaractHig = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			carHigSan.setCreated(new Date());
			carHigSan.setCreatedBy(authentication.getName());
		}
		else{
			carHigSan =  this.caractHigSanitariasService.getCaractHigSanitarias(idCaractHig);
		}
		carHigSan.setFamilia(familiaService.getFamilia(idFamilia));
		carHigSan.setHacinamiento(hacinamiento);
		carHigSan.setAnimalesDom(animalesDom);
		carHigSan.setRiesgoNatural(riesgoNatural);
		carHigSan.setRiesgoMeteorologico(riesgoMeteorologico);
		carHigSan.setRiesgoBiologico(riesgoBiologico);
		carHigSan.setRiesgoSocial(riesgoSocial);
		carHigSan.setFactoresMedAmb(factoresMedAmb);
		carHigSan.setCombCocinar(combCocinar);
		carHigSan.setaAgua(catalogoService.getAbastecimientoAgua(aAgua));
		carHigSan.setcAgua(catalogoService.getCalidadAgua(cAgua));
		carHigSan.setElectricidad(catalogoService.getElectricidad(electricidad));
		carHigSan.setDepExcretas(catalogoService.getDepExcretas(depExcretas));
		carHigSan.setDepBasura(catalogoService.getDepBasura(depBasura));
		carHigSan.setDepResLiq(catalogoService.getDepResLiq(depResLiq));
		carHigSan.setObsCaractHig(obsCaractHig);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		carHigSan.setFechaUltimaVisita(date);
		carHigSan.setIdCaractHig(idCaractHig);
		caractHigSanitariasService.addCaractHigSanitarias(carHigSan);
		return createJsonResponse(carHigSan);
	}
	
	@RequestMapping( value="newFactSocEc", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFactSocEcForm( @RequestParam( value="idFamilia", required=true) String idFamilia
			, @RequestParam( value="tipoPiso", required=false) String tipoPiso
			, @RequestParam( value="tipoTecho", required=false) String tipoTecho
			, @RequestParam( value="tipoPared", required=false) String tipoPared
			, @RequestParam( value="culturaSanitaria", required=false) String culturaSanitaria
			, @RequestParam( value="carPsicosociales", required=false) String carPsicosociales
			, @RequestParam( value="satNecBasicas", required=false) String satNecBasicas
			, @RequestParam( value="tenenciaVivienda", required=false) String tenenciaVivienda
			, @RequestParam( value="accionesComunitarias", required=false) String accionesComunitarias
			, @RequestParam( value="obsFactSocioEc", required=false) String obsFactSocioEc
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita
			, @RequestParam( value="idFactSocioEc", required=false, defaultValue="" ) String idFactSocioEc
	        ) throws ParseException 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		FactSocioEconomicos factSocEc =  new FactSocioEconomicos();
		if (idFactSocioEc.equals("")){
			idFactSocioEc = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			factSocEc.setCreated(new Date());
			factSocEc.setCreatedBy(authentication.getName());
		}
		else{
			factSocEc =  this.factSocioEconomicosService.getFactSocioEconomicos(idFactSocioEc);
		}
		factSocEc.setFamilia(familiaService.getFamilia(idFamilia));
		factSocEc.setTipoPiso(catalogoService.getTipoPiso(tipoPiso));
		factSocEc.setTipoTecho(catalogoService.getTipoTecho(tipoTecho));
		factSocEc.setTipoPared(catalogoService.getTipoPared(tipoPared));
		factSocEc.setCulturaSanitaria(catalogoService.getCulturaSanitaria(culturaSanitaria));
		factSocEc.setCarPsicosociales(catalogoService.getCarPsicosociales(carPsicosociales));
		factSocEc.setSatNecBasicas(satNecBasicas);
		factSocEc.setTenenciaVivienda(catalogoService.getTenenciaVivienda(tenenciaVivienda));
		factSocEc.setAccionesComunitarias(accionesComunitarias);
		factSocEc.setObsFactSocioEc(obsFactSocioEc);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		factSocEc.setFechaUltimaVisita(date);
		factSocEc.setIdFactSocioEc(idFactSocioEc);
		factSocioEconomicosService.addFactSocioEconomicos(factSocEc);
		return createJsonResponse(factSocEc);
	}
	
	@RequestMapping( value="newFuncFam", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFuncFamForm( @RequestParam( value="idFamilia", required=true) String idFamilia
			, @RequestParam( value="tamFamilia", required=false) String tamFamilia
			, @RequestParam( value="ontogenesis", required=false) String ontogenesis
			, @RequestParam( value="etapaCicloVital", required=false) String etapaCicloVital
			, @RequestParam( value="crisisNormativa", required=false) String crisisNormativa
			, @RequestParam( value="crisisParanormativa", required=false) String crisisParanormativa
			, @RequestParam( value="usoMedTradicional", required=false) String usoMedTradicional
			, @RequestParam( value="obsFuncFamiliar", required=false) String obsFuncFamiliar
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita
			, @RequestParam( value="idFuncFamiliar", required=false, defaultValue="" ) String idFuncFamiliar
	        ) throws ParseException 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		FuncFamiliar funcFam =  new FuncFamiliar();
		if (idFuncFamiliar.equals("")){
			idFuncFamiliar = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			funcFam.setCreated(new Date());
			funcFam.setCreatedBy(authentication.getName());
		}
		else{
			funcFam =  this.funcFamiliarService.getFuncFamiliar(idFuncFamiliar);
		}
		funcFam.setFamilia(familiaService.getFamilia(idFamilia));
		funcFam.setTamFamilia(catalogoService.getTamanoFam(tamFamilia));
		funcFam.setOntogenesis(catalogoService.getOntogenesis(ontogenesis));
		funcFam.setEtapaCicloVital(catalogoService.getEtapaCicloVital(etapaCicloVital));
		funcFam.setCrisisNormativa(crisisNormativa);
		funcFam.setCrisisParanormativa(crisisParanormativa);
		funcFam.setUsoMedTradicional(usoMedTradicional);
		funcFam.setObsFuncFamiliar(obsFuncFamiliar);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		funcFam.setFechaUltimaVisita(date);
		funcFam.setIdFuncFamiliar(idFuncFamiliar);
		funcFamiliarService.addFuncFamiliar(funcFam);
		return createJsonResponse(funcFam);
	}
	
	@RequestMapping( value="newPersona", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationPersonaForm( @RequestParam(value="idFamiliaPerson", required=true ) String idFamiliaPerson
			, @RequestParam(value="numPersona", required=true ) Integer numPersona
			, @RequestParam( value="nombres", required=true ) String nombres
			, @RequestParam( value="primerApellido", required=true ) String primerApellido
			, @RequestParam( value="segundoApellido", required=false ) String segundoApellido
			, @RequestParam( value="cedula", required=false) String cedula
			, @RequestParam( value="fechaNacimiento", required=false) String fechaNacimiento
			, @RequestParam( value="actaNacimiento", required=false) String actaNacimiento
			, @RequestParam(value="etnia", required=false ) String etnia
			, @RequestParam(value="sexo", required=false ) String sexo
			, @RequestParam(value="escolaridad", required=false ) String escolaridad
			, @RequestParam(value="ocupacion", required=false ) String ocupacion
			, @RequestParam(value="religion", required=false ) String religion
			, @RequestParam(value="embarazada", required=false ) String embarazada
			, @RequestParam(value="cpnActualizado", required=false ) String cpnActualizado
			, @RequestParam(value="mujerEdadFertil", required=false ) String mujerEdadFertil
			, @RequestParam(value="planFamiliar", required=false ) String planFamiliar
			, @RequestParam(value="men1A", required=false ) String men1A
			, @RequestParam(value="men1AVPCD", required=false ) String men1AVPCD
			, @RequestParam(value="factRiesgoMod", required=false ) String factRiesgoMod
			, @RequestParam(value="factRiesgoNoMod", required=false ) String factRiesgoNoMod
			, @RequestParam(value="factRiesgoSocial", required=false ) String factRiesgoSocial
			, @RequestParam(value="discapacidades", required=false ) String discapacidades
			, @RequestParam(value="grupoDisp", required=false ) String grupoDisp
			, @RequestParam( value="idPersona", required=false, defaultValue="" ) String idPersona
	        ) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Persona persona = new Persona();
		if (idPersona.equals("")){
			idPersona = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			persona.setCreated(new Date());
			persona.setCreatedBy(authentication.getName());
		}
		else{
			persona =  this.personaService.getPersona(idPersona);
		}
		Familia familia = familiaService.getFamilia(idFamiliaPerson);    
		persona.setIdPersona(idPersona);
		persona.setFamilia(familia);
        persona.setCodPersona(familia.getCodFamilia()+"-"+numPersona);
        persona.setNumPersona(numPersona);
        persona.setNombres(nombres);
        persona.setPrimerApellido(primerApellido);
        persona.setSegundoApellido(segundoApellido);
        persona.setCedula(cedula);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaNacimiento);
		persona.setFechaNacimiento(date);
		persona.setActaNacimiento(actaNacimiento);
        persona.setEtnia(catalogoService.getEtnia(etnia));
        persona.setSexo(catalogoService.getSexo(sexo));
        persona.setEscolaridad(catalogoService.getEscda(escolaridad));
        persona.setOcupacion(catalogoService.getOcupacion(ocupacion));
        persona.setReligion(catalogoService.getReligion(religion));
        persona.setEmbarazada(embarazada);
        persona.setCpnActualizado(cpnActualizado);
        persona.setMujerEdadFertil(mujerEdadFertil);
        persona.setPlanFamiliar(planFamiliar);
        persona.setMen1A(men1A);
        persona.setMen1AVPCD(men1AVPCD);
        persona.setFactRiesgoMod(factRiesgoMod);
        persona.setFactRiesgoNoMod(factRiesgoNoMod);
        persona.setFactRiesgoSocial(factRiesgoSocial);
        persona.setDiscapacidades(discapacidades);
        persona.setGrupoDisp(catalogoService.getGrupoDispensarial(grupoDisp));
        personaService.addPersona(persona);
		
		return createJsonResponse(persona);
	}
	
	@RequestMapping( value="newEnfermedad", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationEnfermedadForm( @RequestParam(value="idPersonaEnf", required=true ) String idPersonaEnf
			, @RequestParam( value="enfermedad", required=true ) String enfermedad
			, @RequestParam( value="personaAtendio", required=true ) String personaAtendio
			, @RequestParam( value="fechaOcurrencia", required=true) String fechaOcurrencia
			, @RequestParam( value="idEnfermedad", required=false, defaultValue="" ) String idEnfermedad
	        ) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Enfermedades enf = new Enfermedades();
		if (idEnfermedad.equals("")){
			idEnfermedad = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			enf.setCreated(new Date());
			enf.setCreatedBy(authentication.getName());
		}
		else{
			enf =  this.enfermedadesService.getEnfermedades(idEnfermedad);
		}
		Persona persona = personaService.getPersona(idPersonaEnf);
		enf.setPersona(persona);
		enf.setEnfermedad(cie10Service.getCie10(enfermedad));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaOcurrencia);
		enf.setFechaOcurrencia(date);
        enf.setIdEnfermedad(idEnfermedad);
        enf.setPersonaAtendio(catalogoService.getProfesion(personaAtendio));
        enfermedadesService.addEnfermedades(enf);
		return createJsonResponse(enf);
	}
	
	@RequestMapping( value="newEnfermedadSC", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationEnfermedadSCForm( @RequestParam(value="idPersonaEnfSoc", required=true ) String idPersonaEnf
			, @RequestParam( value="enfermedadsoc", required=true ) String enfermedad
			, @RequestParam( value="personaAtendioSoc", required=true ) String personaAtendio
			, @RequestParam( value="fechaOcurrenciaSoc", required=true) String fechaOcurrencia
			, @RequestParam( value="idEnfermedadSoc", required=false, defaultValue="" ) String idEnfermedad
	        ) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EnfermedadesSocioCult enf = new EnfermedadesSocioCult();
		if (idEnfermedad.equals("")){
			idEnfermedad = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			enf.setCreated(new Date());
			enf.setCreatedBy(authentication.getName());
		}
		else{
			enf =  this.enfermedadesSocioCultService.getEnfermedadesSocioCult(idEnfermedad);
		}
		Persona persona = personaService.getPersona(idPersonaEnf);
		enf.setPersona(persona);
		enf.setEnfermedad(catalogoService.getEnfSocioC(enfermedad));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaOcurrencia);
		enf.setFechaOcurrencia(date);
        enf.setIdEnfSocioC(idEnfermedad);
        enf.setPersonaAtendio(catalogoService.getProfesion(personaAtendio));
        enfermedadesSocioCultService.addEnfermedadesSocioCult(enf);
		return createJsonResponse(enf);
	}
	
    @RequestMapping(value = "editHsf/{idFamilia}", method = RequestMethod.GET)
	public String initUpdateFamForm(@PathVariable("idFamilia") String idFamilia, Model model) {
    	Familia familia = this.familiaService.getFamilia(idFamilia);
		if(familia!=null){
			List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas();
			Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector());
	        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio());
	        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais());
			model.addAttribute("familia",familia);
			model.addAttribute("entidades",entidades);
			model.addAttribute("sector",sector);
			model.addAttribute("municipio",municipio);
			model.addAttribute("silais",silais);
			return "hsf/editFamilia";
		}
		else{
			return "404";
		}
	}
    
    @RequestMapping(value = "editVisita/{idVisita}", method = RequestMethod.GET)
	public String initUpdateVisitForm(@PathVariable("idVisita") String idVisita, Model model) {
    	Visita visita = this.visitaService.getVisita(idVisita);
		if(visita!=null){
	        List<Profesion> profesiones = catalogoService.getProfesiones();
			model.addAttribute("visita",visita);
			model.addAttribute("profesiones",profesiones);
			return "hsf/editVisita";
		}
		else{
			return "404";
		}
	}
    
    @RequestMapping(value = "editChs/{idCaractHig}", method = RequestMethod.GET)
	public String initUpdateCHSForm(@PathVariable("idCaractHig") String idCaractHig, Model model) {
    	CaractHigSanitarias chs = this.caractHigSanitariasService.getCaractHigSanitarias(idCaractHig);
    	Familia familia = this.familiaService.getFamilia(idCaractHig);
    	Visita visita = this.visitaService.getUltimaVisita(idCaractHig);
    	if(chs==null && familia ==null){
    		return "404";
    	}
		else{
			if(chs==null){
				chs= new CaractHigSanitarias();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
			}
			else{
				model.addAttribute("identFamilia",chs.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",chs.getFechaUltimaVisita());
			}
			List<SiNoNs> sinons = this.catalogoService.getSiNoNs();
			List<AnimalesDomesticos> animales = catalogoService.getAnimalesDomesticos();
	    	List<RiesgoNatural> rgnats = catalogoService.getRiesgoNatural();
	    	List<RiesgoMeteorologico> rgmets = catalogoService.getRiesgoMeteorologico();
	    	List<RiesgoBiologico> rgbios = catalogoService.getRiesgoBiologico();
	    	List<RiesgoSocial> rgsocs = catalogoService.getRiesgoSocial();
	    	List<FactoresMedAmb> factoresMedAmbs = catalogoService.getFactoresMedAmb();
	    	List<CombCocinar> combCocinars = catalogoService.getCombCocinar();
	    	List<AbastecimientoAgua> abastecimientoAguas = catalogoService.getAbastecimientoAgua();
	    	List<CalidadAgua> calidadAguas = catalogoService.getCalidadAgua();
	    	List<Electricidad> electricidads = catalogoService.getElectricidad();
	    	List<DepExcretas> depExcretas = catalogoService.getDepExcretas();
	    	List<DepBasura> depBasuras = catalogoService.getDepBasura();
	    	List<DepResLiq> depResLiqs = catalogoService.getDepResLiq();
			model.addAttribute("chs",chs);
			model.addAttribute("sinons",sinons);
			model.addAttribute("animales", animales);
	    	model.addAttribute("rgnats", rgnats);
	    	model.addAttribute("rgmets", rgmets);
	    	model.addAttribute("rgbios", rgbios);
	    	model.addAttribute("rgsocs", rgsocs);
	    	model.addAttribute("factoresMedAmbs", factoresMedAmbs);
	    	model.addAttribute("combCocinars", combCocinars);
	    	model.addAttribute("abastecimientoAguas", abastecimientoAguas);
	    	model.addAttribute("calidadAguas", calidadAguas);
	    	model.addAttribute("electricidads", electricidads);
	    	model.addAttribute("depExcretas", depExcretas);
	    	model.addAttribute("depBasuras", depBasuras);
	    	model.addAttribute("depResLiqs", depResLiqs);
			return "hsf/editChs";
		}
	}
    
    @RequestMapping(value = "editFse/{idFactSocioEc}", method = RequestMethod.GET)
	public String initUpdateFSEForm(@PathVariable("idFactSocioEc") String idFactSocioEc, Model model) {
    	FactSocioEconomicos fse = this.factSocioEconomicosService.getFactSocioEconomicos(idFactSocioEc);
    	Familia familia = this.familiaService.getFamilia(idFactSocioEc);
    	Visita visita = this.visitaService.getUltimaVisita(idFactSocioEc);
		if(fse==null && familia ==null){
			return "404";
    	}
		else{
			if(fse==null){
				fse= new FactSocioEconomicos();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
			}
			else{
				model.addAttribute("identFamilia",fse.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",fse.getFechaUltimaVisita());
			}
			List<SiNoNs> sinons = catalogoService.getSiNoNs();
			List<TipoPiso> pisos = catalogoService.getTipoPiso();
	    	List<TipoTecho> techos = catalogoService.getTipoTecho();
	    	List<TipoPared> paredes = catalogoService.getTipoPared();
	    	List<CulturaSanitaria> culturasSanitaria = catalogoService.getCulturaSanitaria();
	    	List<CarPsicosociales> carsPsicosociales = catalogoService.getCarPsicosociales();
	    	List<TenenciaVivienda> tenenciasVivienda = catalogoService.getTenenciaVivienda();
	    	List<AccionesComunitarias> accionesComunitarias = catalogoService.getAccionesComunitarias();
			model.addAttribute("fse",fse);
			model.addAttribute("sinons",sinons);
			model.addAttribute("pisos", pisos);
	    	model.addAttribute("techos", techos);
	    	model.addAttribute("paredes", paredes);
	    	model.addAttribute("culturasSanitaria", culturasSanitaria);
	    	model.addAttribute("carsPsicosociales", carsPsicosociales);
	    	model.addAttribute("tenenciasVivienda", tenenciasVivienda);
	    	model.addAttribute("accionesComunitarias", accionesComunitarias);
			return "hsf/editFse";
		}
	}
    
    @RequestMapping(value = "editFf/{idFuncFamiliar}", method = RequestMethod.GET)
	public String initUpdateFFForm(@PathVariable("idFuncFamiliar") String idFuncFamiliar, Model model) {
    	FuncFamiliar ff = this.funcFamiliarService.getFuncFamiliar(idFuncFamiliar);
    	Familia familia = this.familiaService.getFamilia(idFuncFamiliar);
    	Visita visita = this.visitaService.getUltimaVisita(idFuncFamiliar);
    	if(ff==null && familia==null){
			return "404";
    	}
    	else{
			if(ff==null){
				ff= new FuncFamiliar();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
			}
			else{
				model.addAttribute("identFamilia",ff.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",ff.getFechaUltimaVisita());
			}
			List<SiNoNs> sinons = catalogoService.getSiNoNs();
			List<TamanoFam> tamanos = catalogoService.getTamanoFam();
	    	List<Ontogenesis> ontos = catalogoService.getOntogenesis();
	    	List<EtapaCicloVital> etapasCicloVital = catalogoService.getEtapaCicloVital();
	    	List<CrisisNormativa> crisisNormativas = catalogoService.getCrisisNormativa();
	    	List<CrisisParanormativa> crisisParanormativas = catalogoService.getCrisisParanormativa();
	    	model.addAttribute("ff",ff);
			model.addAttribute("sinons",sinons);
	    	model.addAttribute("tamanos", tamanos);
	    	model.addAttribute("ontos", ontos);
	    	model.addAttribute("etapasCicloVital", etapasCicloVital);
	    	model.addAttribute("crisisNormativas", crisisNormativas);
	    	model.addAttribute("crisisParanormativas", crisisParanormativas);
			return "hsf/editFf";
		}
	}
    
    @RequestMapping(value = "searchHsf", method = RequestMethod.GET)
    public String initSearchForm(Model model) throws ParseException { 	
    	logger.debug("Buscar una HSF");
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas();
    	model.addAttribute("entidades", entidades);
    	return "hsf/search";
	}
	
	/**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "hsfs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Familia> fetchFamiliasJson(@RequestParam(value = "codComunidad", required = true) String comunidad,
    		@RequestParam(value = "numVivienda", required = false, defaultValue = "") Integer numVivienda,
    		@RequestParam(value = "numFamilia", required = false, defaultValue = "") Integer numFamilia,
    		@RequestParam(value = "numFicha", required = false, defaultValue = "") Integer numFicha) throws ParseException {
        logger.info("Obteniendo las visitas en JSON");
        List<Familia> familias = familiaService.getFamilias(comunidad, numVivienda, numFamilia, numFicha);
        if (familias == null){
        	logger.debug("Nulo");
        }
        return familias;	
    }
    
    /**
     * Custom handler for displaying a visit.
     *
     * @param idVisit the ID of the visit to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("viewHsf/{idFamilia}")
    public String showVisit(@PathVariable("idFamilia") String idFamilia, Model model) {
        Familia familia = this.familiaService.getFamilia(idFamilia);
        if(familia!=null){
	        Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector());
	        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio());
	        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais());
	        CaractHigSanitarias carHigSan = this.caractHigSanitariasService.getCaractHigSanitariasFamilia(idFamilia);
	        FactSocioEconomicos factSocEc = this.factSocioEconomicosService.getFactSocioEconomicosFamilia(idFamilia);
	        FuncFamiliar funcFam = this.funcFamiliarService.getFuncFamiliarFamilia(idFamilia);
	        List<Persona> personas = this.personaService.getPersonas(idFamilia);
	        List<Visita> visitas = this.visitaService.getVisitas(idFamilia);
	        List<SiNoNs> sinons = this.catalogoService.getSiNoNs();
	        List<AnimalesDomesticos> animales = this.catalogoService.getAnimalesDomesticos();
	        List<RiesgoNatural> rgnats = this.catalogoService.getRiesgoNatural();
	    	List<RiesgoMeteorologico> rgmets = this.catalogoService.getRiesgoMeteorologico();
	    	List<RiesgoBiologico> rgbios = this.catalogoService.getRiesgoBiologico();
	    	List<RiesgoSocial> rgsocs = this.catalogoService.getRiesgoSocial();
	    	List<FactoresMedAmb> factoresMedAmbs = this.catalogoService.getFactoresMedAmb();
	    	List<CombCocinar> combCocinars = this.catalogoService.getCombCocinar();
	    	List<AccionesComunitarias> accionesComunitarias = this.catalogoService.getAccionesComunitarias();
	    	List<CrisisNormativa> crisisNormativas = this.catalogoService.getCrisisNormativa();
	    	List<CrisisParanormativa> crisisParanormativas = this.catalogoService.getCrisisParanormativa();
	        model.addAttribute("familia",familia);
	        model.addAttribute("sector",sector);
	        model.addAttribute("municipio",municipio);
	        model.addAttribute("silais",silais);
	        model.addAttribute("carHigSan",carHigSan);
	        model.addAttribute("factSocEc",factSocEc);
	        model.addAttribute("funcFam",funcFam);
	        model.addAttribute("personas",personas);
	        model.addAttribute("visitas",visitas);
	        model.addAttribute("sinons",sinons);
	        model.addAttribute("animales",animales);
	        model.addAttribute("rgnats",rgnats);
	        model.addAttribute("rgmets",rgmets);
	        model.addAttribute("rgbios",rgbios);
	        model.addAttribute("rgsocs",rgsocs);
	        model.addAttribute("factoresMedAmbs",factoresMedAmbs);
	        model.addAttribute("combCocinars",combCocinars);
	        model.addAttribute("accionesComunitarias",accionesComunitarias);
	        model.addAttribute("crisisNormativas",crisisNormativas);
	        model.addAttribute("crisisParanormativas",crisisParanormativas);
	        return "hsf/familia";
        }
		else{
			return "404";
		}
    }
	
	private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson( o );
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
	
	
}
