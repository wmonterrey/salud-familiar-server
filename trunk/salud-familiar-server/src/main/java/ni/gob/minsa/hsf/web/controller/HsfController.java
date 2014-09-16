package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.CaractHigSanitarias;
import ni.gob.minsa.hsf.domain.Enfermedades;
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
import ni.gob.minsa.hsf.service.CaractHigSanitariasService;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.Cie10Service;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.EnfermedadesService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.FactSocioEconomicosService;
import ni.gob.minsa.hsf.service.FamiliaService;
import ni.gob.minsa.hsf.service.FuncFamiliarService;
import ni.gob.minsa.hsf.service.PersonaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
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
    	return "hsf/create";
	}
	
	@RequestMapping( value="newFamiliaVisita", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFamiliaForm( @RequestParam(value="comunidad", required=true ) String comunidad
			, @RequestParam( value="numVivienda", required=true ) Integer numVivienda
			, @RequestParam( value="numFamilia", required=true ) Integer numFamilia
			, @RequestParam( value="direccion", required=true ) String direccion
			, @RequestParam( value="idFamilia", required=false, defaultValue="" ) String idFamilia
			, @RequestParam( value="idVisita", required=false, defaultValue="" ) String idVisita
			, @RequestParam( value="numFicha", required=true ) Integer numFicha
			, @RequestParam( value="personaVisita", required=true) String personaVisita
			, @RequestParam( value="personaVisitaProfesion", required=true) String personaVisitaProfesion
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita
			
	        ) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Familia familia = new Familia();
		familia.setComunidad(comunidadService.getComunidad(comunidad));
		familia.setNumVivienda(numVivienda);
		familia.setNumFamilia(numFamilia);
		familia.setDireccion(direccion);
		familia.setCodFamilia(comunidad+"-"+numVivienda+"-"+numFamilia);
		if (idFamilia.equals("")){
			idFamilia = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		familia.setIdFamilia(idFamilia);
		familia.setCreatedBy(authentication.getName());
		familia.setCreated(new Date());
		familia.setPasive('0');
		familiaService.addFamilia(familia);
		
		
		Visita visita = new Visita();
		visita.setFamilia(familia);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaVisita);
		visita.setFechaVisita(date);
		visita.setNumFicha(numFicha);
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
		visita.setPasive('0');
		visita.setMovilInfo(movilInfo);
		visitaService.addVisita(visita);
		return createJsonResponse(visita);
	}
	
	@RequestMapping( value="newCarHigSan", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationCaractHigForm( @RequestParam( value="idVisita", required=true) String idVisita
			, @RequestParam( value="hacinamiento", required=true) String hacinamiento
			, @RequestParam( value="animalesDom", required=true) String animalesDom
			, @RequestParam( value="idCaractHig", required=false, defaultValue="" ) String idCaractHig
	        ) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CaractHigSanitarias carHigSan =  new CaractHigSanitarias();
		carHigSan.setVisita(visitaService.getVisita(idVisita));
		carHigSan.setHacinamiento(hacinamiento);
		carHigSan.setAnimalesDom(animalesDom);
		if (idCaractHig.equals("")){
			idCaractHig = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		carHigSan.setIdCaractHig(idCaractHig);
		carHigSan.setCreated(new Date());
		carHigSan.setCreatedBy(authentication.getName());
		carHigSan.setPasive('0');
		caractHigSanitariasService.addCaractHigSanitarias(carHigSan);
		return createJsonResponse(carHigSan);
	}
	
	@RequestMapping( value="newFactSocEc", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFactSocEcForm( @RequestParam( value="idVisita", required=true) String idVisita
			, @RequestParam( value="tipoPiso", required=true) String tipoPiso
			, @RequestParam( value="tipoTecho", required=true) String tipoTecho
			, @RequestParam( value="idFactSocioEc", required=false, defaultValue="" ) String idFactSocioEc
	        ) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		FactSocioEconomicos factSocEc =  new FactSocioEconomicos();
		factSocEc.setVisita(visitaService.getVisita(idVisita));
		factSocEc.setTipoPiso(catalogoService.getTipoPiso(tipoPiso));
		factSocEc.setTipoTecho(catalogoService.getTipoTecho(tipoTecho));
		if (idFactSocioEc.equals("")){
			idFactSocioEc = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		factSocEc.setIdFactSocioEc(idFactSocioEc);
		factSocEc.setCreated(new Date());
		factSocEc.setCreatedBy(authentication.getName());
		factSocEc.setPasive('0');
		factSocioEconomicosService.addFactSocioEconomicos(factSocEc);
		return createJsonResponse(factSocEc);
	}
	
	@RequestMapping( value="newFuncFam", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationFuncFamForm( @RequestParam( value="idVisita", required=true) String idVisita
			, @RequestParam( value="tamFamilia", required=true) String tamFamilia
			, @RequestParam( value="ontogenesis", required=true) String ontogenesis
			, @RequestParam( value="idFuncFamiliar", required=false, defaultValue="" ) String idFuncFamiliar
	        ) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		FuncFamiliar funcFam =  new FuncFamiliar();
		funcFam.setVisita(visitaService.getVisita(idVisita));
		funcFam.setTamFamilia(catalogoService.getTamanoFam(tamFamilia));
		funcFam.setOntogenesis(catalogoService.getOntogenesis(ontogenesis));
		if (idFuncFamiliar.equals("")){
			idFuncFamiliar = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
		funcFam.setIdFuncFamiliar(idFuncFamiliar);
		funcFam.setCreated(new Date());
		funcFam.setCreatedBy(authentication.getName());
		funcFam.setPasive('0');
		funcFamiliarService.addFuncFamiliar(funcFam);
		return createJsonResponse(funcFam);
	}
	
	@RequestMapping( value="newPersona", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationPersonaForm( @RequestParam(value="idFamiliaPerson", required=true ) String idFamiliaPerson
			, @RequestParam(value="numPersona", required=true ) Integer numPersona
			, @RequestParam( value="nombres", required=true ) String nombres
			, @RequestParam( value="primerApellido", required=true ) String primerApellido
			, @RequestParam( value="segundoApellido", required=true ) String segundoApellido
			, @RequestParam( value="cedula", required=false, defaultValue="" ) String cedula
			, @RequestParam( value="fechaNacimiento", required=false, defaultValue="") String fechaNacimiento
			, @RequestParam( value="actaNacimiento", required=false) String actaNacimiento
			, @RequestParam(value="edad", required=false, defaultValue="" ) Integer edad
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
		Familia familia = familiaService.getFamilia(idFamiliaPerson);    
		Persona persona = new Persona();
		if (idPersona.equals("")){
        	idPersona = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
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
        persona.setEdad(edad);
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
        persona.setFallecido('0');
        persona.setCreated(new Date());
        persona.setPasive('0');
        persona.setCreatedBy(authentication.getName());
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
		Persona persona = personaService.getPersona(idPersonaEnf);
		Enfermedades enf = new Enfermedades();
		enf.setPersona(persona);
		enf.setEnfermedad(cie10Service.getCie10(enfermedad));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fechaOcurrencia);
		enf.setFechaOcurrencia(date);
        if (idEnfermedad.equals("")){
        	idEnfermedad = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
		}
        enf.setIdEnfermedad(idEnfermedad);
        enf.setPersonaAtendio(catalogoService.getProfesion(personaAtendio));
        enf.setCreated(new Date());
        enf.setCreatedBy(authentication.getName());
        enf.setPasive('0');
        enfermedadesService.addEnfermedades(enf);
		return createJsonResponse(enf);
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
