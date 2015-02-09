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
import ni.gob.minsa.hsf.domain.Eventos;
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
import ni.gob.minsa.hsf.domain.catalogos.Evento;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoNoMod;
import ni.gob.minsa.hsf.domain.catalogos.FactRiesgoSoc;
import ni.gob.minsa.hsf.domain.catalogos.FactoresMedAmb;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
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
import ni.gob.minsa.hsf.domain.estructura.Ocupacion;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
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
import ni.gob.minsa.hsf.service.EventosService;
import ni.gob.minsa.hsf.service.FactSocioEconomicosService;
import ni.gob.minsa.hsf.service.FamiliaService;
import ni.gob.minsa.hsf.service.FuncFamiliarService;
import ni.gob.minsa.hsf.service.HsfService;
import ni.gob.minsa.hsf.service.OcupacionService;
import ni.gob.minsa.hsf.service.PersonaService;
import ni.gob.minsa.hsf.service.SectoresService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.service.VisitaService;
import ni.gob.minsa.hsf.users.model.UserSistema;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Resource(name="ocupacionService")
	private OcupacionService ocupacionService;
	@Resource(name="familiaService")
	private FamiliaService familiaService;
	@Resource(name="visitaService")
	private VisitaService visitaService;
	@Resource(name="eventosService")
	private EventosService eventosService;
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
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="hsfService")
	private HsfService hsfService;
	
	@RequestMapping(value = "newHsf", method = RequestMethod.GET)
    public String initCreationForm(Model model) throws ParseException { 	
    	logger.debug("Crear nueva HSF");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Profesion> profesiones = catalogoService.getProfesiones();
    	List<SiNoNs> sinons = catalogoService.getSiNoNs();
    	List<Sexo> sexos = catalogoService.getSexo();
    	List<Etnia> etnias = catalogoService.getEtnia();
    	List<Escolaridad> escolaridades = catalogoService.getEscda();
    	List<Ocupacion> ocupaciones = ocupacionService.getAllOcupaciones();
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
		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Comunidades com = comunidadService.getComunidad(comunidad);
    	if(hsfService.verificarPermisoDatos(com, usuario)){
			Familia familia = new Familia();
			familia.setComunidad(com);
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
			familia.setDispensarizada(esDispensarizada(idFamilia));
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
    	else{
    		return null;
    	}
	}
	
	@RequestMapping( value="newVisita", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationVisitaForm( @RequestParam(value="comunidad", required=true ) String comunidad
			, @RequestParam( value="idFamilia", required=true) String idFamilia
			, @RequestParam( value="personaVisita", required=true) String personaVisita
			, @RequestParam( value="personaVisitaProfesion", required=true) String personaVisitaProfesion
			, @RequestParam( value="motivoVisita", required=false, defaultValue="" ) String motivoVisita
			, @RequestParam( value="problemasEncontrados", required=false, defaultValue="" ) String problemasEncontrados
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Comunidades com = comunidadService.getComunidad(comunidad);
    	if(hsfService.verificarPermisoDatos(com, usuario)){
			Visita visita = new Visita();
			Familia familia = this.familiaService.getFamilia(idFamilia);
			visita.setFamilia(familia);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse(fechaVisita);
			visita.setFechaVisita(date);
			visita.setPersonaVisita(personaVisita);
			visita.setMotivoVisita(motivoVisita);
			visita.setProblemasEncontrados(problemasEncontrados);
			visita.setPersonaVisitaProfesion(catalogoService.getProfesion(personaVisitaProfesion));
			MovilInfo movilInfo = new MovilInfo();
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) authentication.getDetails();
			movilInfo.setDeviceid(wad.getRemoteAddress());
			String idVisita = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			visita.setIdVisita(idVisita);
			visita.setCreatedBy(authentication.getName());
			visita.setCreated(new Date());
			visita.setMovilInfo(movilInfo);
			visita.setTipoVisita('0');
			visitaService.addVisita(visita);
			return createJsonResponse(visita);
    	}
    	else{
    		return null;
    	}
	}
	
	@RequestMapping( value="newEvento", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationEventoForm( @RequestParam(value="comunidad", required=true ) String comunidad
			, @RequestParam( value="idPersona", required=true) String idPersona
			, @RequestParam( value="evento", required=true) String evento
			, @RequestParam( value="observacion", required=false, defaultValue="" ) String observacion
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita, @RequestParam( value="fechaEvento", required=true) String fechaEvento) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Comunidades com = comunidadService.getComunidad(comunidad);
    	if(hsfService.verificarPermisoDatos(com, usuario)){
			Eventos eventoData = new Eventos();
			Persona persona = this.personaService.getPersona(idPersona);
			eventoData.setPersona(persona);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dateVisita = formatter.parse(fechaVisita);
			eventoData.setFechaVisita(dateVisita);
			Date dateEvento = formatter.parse(fechaEvento);
			eventoData.setFechaEvento(dateEvento);
			eventoData.setEvento(this.catalogoService.getEvento(evento));
			eventoData.setObservacion(observacion);
			String idEvento = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
			eventoData.setIdEvento(idEvento);
			eventoData.setCreatedBy(authentication.getName());
			eventoData.setCreated(new Date());
			eventosService.addEventos(eventoData);
			if(eventoData.getEvento().getCodigo().equals("HSF_EVENTO|FALL")){
				persona.setFallecido('1');
				persona.setFechaFallecimiento(eventoData.getFechaEvento());
				persona.setPasive('1');
				personaService.addPersona(persona);
			}
			else if(eventoData.getEvento().getCodigo().equals("HSF_EVENTO|TRAS")){
				persona.setPasive('1');
				personaService.addPersona(persona);
			}
			else if(eventoData.getEvento().getCodigo().equals("HSF_EVENTO|NF")){
				persona.setPasive('1');
				personaService.addPersona(persona);
			}
			return createJsonResponse(eventoData);
    	}
    	else{
    		return null;
    	}
	}
	
	private char esDispensarizada(String idFamilia) {
        List<Persona> personas = this.personaService.getPersonas(idFamilia);
        for(Persona persona : personas){
        	if (persona.getGrupoDisp().getCodigo().equals("HSF_GD|NING")) return '0';
        }
        return '1';
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		Comunidades com = comunidadService.getComunidad(comunidad);
    	if(hsfService.verificarPermisoDatos(com, usuario)){
			Familia familia = this.familiaService.getFamilia(idFamilia);
			familia.setComunidad(com);
			familia.setNumVivienda(numVivienda);
			familia.setNumFamilia(numFamilia);
			familia.setNumFicha(numFicha);
			familia.setDireccion(direccion);
			familia.setCodFamilia(comunidad+"-"+numVivienda+"-"+numFamilia);
			familia.setInfoCompleta(estaCompleta(idFamilia));
			familia.setDispensarizada(esDispensarizada(idFamilia));
			familiaService.addFamilia(familia);
			return createJsonResponse(familia);
    	}
    	else{
    		return null;
    	}
	}
	
	@RequestMapping( value="editVisita", method=RequestMethod.POST)
	public ResponseEntity<String> processEditionVisitaForm(@RequestParam( value="idVisita", required=true) String idVisita
			, @RequestParam( value="personaVisita", required=true) String personaVisita
			, @RequestParam( value="personaVisitaProfesion", required=true) String personaVisitaProfesion
			, @RequestParam( value="fechaVisita", required=true) String fechaVisita) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		Visita visita = this.visitaService.getVisita(idVisita);
		Comunidades com = visita.getFamilia().getComunidad();
    	if(hsfService.verificarPermisoDatos(com, usuario)){
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
    	else{
    		return null;
    	}
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
		Familia familia = this.familiaService.getFamilia(idFamilia);
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
			CaractHigSanitarias carHigSan =  new CaractHigSanitarias();
			if (idCaractHig.equals("")){
				idCaractHig = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				carHigSan.setCreated(new Date());
				carHigSan.setCreatedBy(authentication.getName());
			}
			else{
				carHigSan =  this.caractHigSanitariasService.getCaractHigSanitarias(idCaractHig);
			}
			carHigSan.setFamilia(familia);
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
		else{
    		return null;
    	}
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
		Familia familia = this.familiaService.getFamilia(idFamilia);
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
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
		else{
    		return null;
    	}
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
		Familia familia = this.familiaService.getFamilia(idFamilia);
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
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
		else{
    		return null;
    	}
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
			, @RequestParam(value="accion", required=false, defaultValue="") String opcion
			, @RequestParam( value="idPersona", required=false, defaultValue="" ) String idPersona
	        ) throws ParseException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		Familia familia = familiaService.getFamilia(idFamiliaPerson);
		if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
			Persona persona = new Persona();
			if (idPersona.equals("")){
				idPersona = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				persona.setCreated(new Date());
				persona.setCreatedBy(authentication.getName());
			}
			else{
				persona =  this.personaService.getPersona(idPersona);
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
	        persona.setEtnia(catalogoService.getEtnia(etnia));
	        persona.setSexo(catalogoService.getSexo(sexo));
	        persona.setEscolaridad(catalogoService.getEscda(escolaridad));
	        persona.setOcupacion(ocupacionService.getOcupacionByCodigo(ocupacion));
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
	        if (grupoDisp.equals("HSF_GD|NING")) {
	        	familia.setDispensarizada('0');
	        	familiaService.addFamilia(familia);
	        }
	        personaService.addPersona(persona);
	        if (opcion.equals("3")){
	        	Eventos eventoData = new Eventos();
	        	eventoData.setPersona(persona);
	        	Visita visita = this.visitaService.getUltimaVisita(familia.getIdFamilia());
	        	eventoData.setFechaVisita(visita.getFechaVisita());
	        	eventoData.setFechaEvento(persona.getFechaNacimiento());
	        	eventoData.setEvento(this.catalogoService.getEvento("HSF_EVENTO|NAC"));
	        	String idEvento = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				eventoData.setIdEvento(idEvento);
				eventoData.setCreatedBy(authentication.getName());
				eventoData.setCreated(new Date());
				eventosService.addEventos(eventoData);
	        }
	        else if(opcion.equals("4")){
	        	Eventos eventoData = new Eventos();
	        	eventoData.setPersona(persona);
	        	Visita visita = this.visitaService.getUltimaVisita(familia.getIdFamilia());
	        	eventoData.setFechaVisita(visita.getFechaVisita());
	        	eventoData.setFechaEvento(visita.getFechaVisita());
	        	eventoData.setEvento(this.catalogoService.getEvento("HSF_EVENTO|NR"));
	        	String idEvento = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				eventoData.setIdEvento(idEvento);
				eventoData.setCreatedBy(authentication.getName());
				eventoData.setCreated(new Date());
				eventosService.addEventos(eventoData);
	        }
			return createJsonResponse(persona);
		}
		else{
    		return null;
    	}
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
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		Persona persona = personaService.getPersona(idPersonaEnf);
		if(hsfService.verificarPermisoDatos(persona.getFamilia().getComunidad(), usuario)){
			Enfermedades enf = new Enfermedades();
			if (idEnfermedad.equals("")){
				idEnfermedad = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				enf.setCreated(new Date());
				enf.setCreatedBy(authentication.getName());
			}
			else{
				enf =  this.enfermedadesService.getEnfermedades(idEnfermedad);
			}
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
		else{
    		return null;
    	}
	}
	
	@RequestMapping(value="quitarenf", method=RequestMethod.GET)
	public @ResponseBody boolean anularEnfermedad(@RequestParam String idEnfermedad) {
	    return this.enfermedadesService.quitarEnfermedad(idEnfermedad);
	}
	
	@RequestMapping(value="quitarenfsoc", method=RequestMethod.GET)
	public @ResponseBody boolean anularEnfermedadSoc(@RequestParam String idEnfermedadSoc) {
	    return this.enfermedadesSocioCultService.quitarEnfermedad(idEnfermedadSoc);
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
		UserSistema usuario = usuarioService.getUser(authentication.getName());
		Persona persona = personaService.getPersona(idPersonaEnf);
		if(hsfService.verificarPermisoDatos(persona.getFamilia().getComunidad(), usuario)){
			EnfermedadesSocioCult enf = new EnfermedadesSocioCult();
			if (idEnfermedad.equals("")){
				idEnfermedad = new UUID(authentication.getName().hashCode(),new Date().hashCode()).toString();
				enf.setCreated(new Date());
				enf.setCreatedBy(authentication.getName());
			}
			else{
				enf =  this.enfermedadesSocioCultService.getEnfermedadesSocioCult(idEnfermedad);
			}
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
		else{
    		return null;
    	}
	}
	
    @RequestMapping(value = "editHsf/{idFamilia}/{opcion}", method = RequestMethod.GET)
	public String initUpdateFamForm(@PathVariable("idFamilia") String idFamilia, @PathVariable("opcion") int opcion, Model model) {
    	Familia familia = this.familiaService.getFamilia(idFamilia);
		if(familia!=null){
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
				List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
				Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector().getCodigo());
		        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio().getCodigoNacional());
		        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais().getCodigo());
		        model.addAttribute("accion",opcion);
				model.addAttribute("familia",familia);
				model.addAttribute("entidades",entidades);
				model.addAttribute("sector",sector);
				model.addAttribute("municipio",municipio);
				model.addAttribute("silais",silais);
				return "hsf/editFamilia";
    		}
    		else{
    			return "403";
    		}
		}
		else{
			return "404";
		}
	}
    
    @RequestMapping(value = "editVisita/{idVisita}", method = RequestMethod.GET)
	public String initUpdateVisitForm(@PathVariable("idVisita") String idVisita, Model model) {
    	Visita visita = this.visitaService.getVisita(idVisita);
		if(visita!=null){
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());    
    		if(hsfService.verificarPermisoDatos(visita.getFamilia().getComunidad(), usuario)){
		        List<Profesion> profesiones = catalogoService.getProfesiones();
				model.addAttribute("visita",visita);
				model.addAttribute("profesiones",profesiones);
				return "hsf/editVisita";
    		}
    		else{
    			return "403";
    		}
		}
		else{
			return "404";
		}
	}
    
    @RequestMapping(value = "editPersona/{idPersona}/{opcion}", method = RequestMethod.GET)
	public String initUpdatePersonForm(@PathVariable("idPersona") String idPersona, @PathVariable("opcion") int opcion, Model model) {
    	Persona persona = this.personaService.getPersona(idPersona);
		if(persona!=null){
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());    
    		if(hsfService.verificarPermisoDatos(persona.getFamilia().getComunidad(), usuario)){
    			Visita visita = this.visitaService.getUltimaVisita(persona.getFamilia().getIdFamilia());
    			model.addAttribute("fechaDeVisita",visita.getFechaVisita());
				List<SiNoNs> sinons = catalogoService.getSiNoNs();
				List<Etnia> etnias = catalogoService.getEtnia();
				List<Sexo> sexos = catalogoService.getSexo();
		    	List<Escolaridad> escolaridades = catalogoService.getEscda();
		    	List<Ocupacion> ocupaciones = ocupacionService.getAllOcupaciones();
		    	List<Religion> religiones = catalogoService.getReligion();
		    	List<FactRiesgoNoMod> frnms = catalogoService.getFactRiesgoNoMod();
		    	List<FactRiesgoMod> frms = catalogoService.getFactRiesgoMod();
		    	List<FactRiesgoSoc> frss = catalogoService.getFactRiesgoSoc();
		    	List<Discapacidad> discps = catalogoService.getDiscapacidad();
		    	List<GrupoDispensarial> gds = catalogoService.getGrupoDispensarial();
		    	List<EnfSocioC> enfermedadessocs = catalogoService.getEnfSocioC();
		    	List<Enfermedades> enfermedades = enfermedadesService.getEnfermedadesPersona(idPersona);
		    	List<EnfermedadesSocioCult> enfermedadesSoc = enfermedadesSocioCultService.getEnfermedadesSocioCultPersona(idPersona);
		    	List<Profesion> profesiones = catalogoService.getProfesiones();
				model.addAttribute("sinons",sinons);
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
		    	model.addAttribute("enfermedades", enfermedades);
		    	model.addAttribute("enfermedadesSoc", enfermedadesSoc);
		    	model.addAttribute("enfermedadessocs", enfermedadessocs);
		    	model.addAttribute("profesiones", profesiones);
				model.addAttribute("persona",persona);
				model.addAttribute("accion",opcion);
				return "hsf/editPersona";
    		}
    		else{
    			return "403";
    		}
		}
		else{
			return "404";
		}
	}
    
    
    @RequestMapping(value = "newPersona/{idFamilia}/{opcion}", method = RequestMethod.GET)
	public String initNewPersonForm(@PathVariable("idFamilia") String idFamilia, @PathVariable("opcion") int opcion, Model model) {
    	Familia familia = this.familiaService.getFamilia(idFamilia);
    	Visita visita = this.visitaService.getUltimaVisita(idFamilia);
    	if(familia ==null){
    		return "404";
    	}
		else{
			Integer numPer = personaService.getCodePersona(idFamilia);
			Persona persona = new Persona();
			persona.setFamilia(familia);
			persona.setNumPersona(numPer);
			List<SiNoNs> sinons = catalogoService.getSiNoNs();
			List<Etnia> etnias = catalogoService.getEtnia();
			List<Sexo> sexos = catalogoService.getSexo();
	    	List<Escolaridad> escolaridades = catalogoService.getEscda();
	    	List<Ocupacion> ocupaciones = ocupacionService.getAllOcupaciones();
	    	List<Religion> religiones = catalogoService.getReligion();
	    	List<FactRiesgoNoMod> frnms = catalogoService.getFactRiesgoNoMod();
	    	List<FactRiesgoMod> frms = catalogoService.getFactRiesgoMod();
	    	List<FactRiesgoSoc> frss = catalogoService.getFactRiesgoSoc();
	    	List<Discapacidad> discps = catalogoService.getDiscapacidad();
	    	List<GrupoDispensarial> gds = catalogoService.getGrupoDispensarial();
	    	List<EnfSocioC> enfermedadessocs = catalogoService.getEnfSocioC();
	    	List<Profesion> profesiones = catalogoService.getProfesiones();
			model.addAttribute("sinons",sinons);
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
	    	model.addAttribute("enfermedadessocs", enfermedadessocs);
	    	model.addAttribute("profesiones", profesiones);
			model.addAttribute("persona",persona);
			model.addAttribute("accion",opcion);
			model.addAttribute("fechaDeVisita",visita.getFechaVisita());
			return "hsf/editPersona";
		}
	}
    
    @RequestMapping(value = "editChs/{idCaractHig}/{opcion}", method = RequestMethod.GET)
	public String initUpdateCHSForm(@PathVariable("idCaractHig") String idCaractHig, @PathVariable("opcion") int opcion, Model model) {
    	CaractHigSanitarias chs = this.caractHigSanitariasService.getCaractHigSanitarias(idCaractHig);
    	Familia familia = this.familiaService.getFamilia(idCaractHig);
    	Visita visita = this.visitaService.getUltimaVisita(idCaractHig);
    	if(chs==null && familia ==null){
    		return "404";
    	}
		else{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Comunidades com = null;
			if(chs==null){
				chs= new CaractHigSanitarias();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
				com=familia.getComunidad();
			}
			else{
				model.addAttribute("identFamilia",chs.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",chs.getFechaUltimaVisita());
				com=chs.getFamilia().getComunidad();
			}
			if(hsfService.verificarPermisoDatos(com, usuario)){
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
		    	model.addAttribute("accion",opcion);
				return "hsf/editChs";
    		}
    		else{
    			return "403";
    		}
		}
	}
    
    @RequestMapping(value = "editFse/{idFactSocioEc}/{opcion}", method = RequestMethod.GET)
	public String initUpdateFSEForm(@PathVariable("idFactSocioEc") String idFactSocioEc, @PathVariable("opcion") int opcion, Model model) {
    	FactSocioEconomicos fse = this.factSocioEconomicosService.getFactSocioEconomicos(idFactSocioEc);
    	Familia familia = this.familiaService.getFamilia(idFactSocioEc);
    	Visita visita = this.visitaService.getUltimaVisita(idFactSocioEc);
		if(fse==null && familia ==null){
			return "404";
    	}
		else{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());  
			Comunidades com = null;
			if(fse==null){
				fse= new FactSocioEconomicos();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
				com=familia.getComunidad();
			}
			else{
				model.addAttribute("identFamilia",fse.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",fse.getFechaUltimaVisita());
				com=fse.getFamilia().getComunidad();
			}
			if(hsfService.verificarPermisoDatos(com, usuario)){
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
		    	model.addAttribute("accion",opcion);
				return "hsf/editFse";
    		}
    		else{
    			return "403";
    		}
		}
	}
    
    @RequestMapping(value = "editFf/{idFuncFamiliar}/{opcion}", method = RequestMethod.GET)
	public String initUpdateFFForm(@PathVariable("idFuncFamiliar") String idFuncFamiliar, @PathVariable("opcion") int opcion, Model model) {
    	FuncFamiliar ff = this.funcFamiliarService.getFuncFamiliar(idFuncFamiliar);
    	Familia familia = this.familiaService.getFamilia(idFuncFamiliar);
    	Visita visita = this.visitaService.getUltimaVisita(idFuncFamiliar);
    	if(ff==null && familia==null){
			return "404";
    	}
    	else{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		Comunidades com = null;
			if(ff==null){
				ff= new FuncFamiliar();
				model.addAttribute("identFamilia",familia.getIdFamilia());
				model.addAttribute("fechaDeVisita",visita.getFechaVisita());
				com=familia.getComunidad();
			}
			else{
				model.addAttribute("identFamilia",ff.getFamilia().getIdFamilia());
				model.addAttribute("fechaDeVisita",ff.getFechaUltimaVisita());
				com=ff.getFamilia().getComunidad();
			}
			if(hsfService.verificarPermisoDatos(com, usuario)){
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
		    	model.addAttribute("accion",opcion);
				return "hsf/editFf";
    		}
    		else{
    			return "403";
    		}
		}
	}
    
    @RequestMapping(value = "searchHsf", method = RequestMethod.GET)
    public String initSearchForm(Model model) throws ParseException { 	
    	logger.debug("Buscar una HSF");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "hsf/search";
	}
    
    @RequestMapping(value = "newVisit", method = RequestMethod.GET)
    public String initNewVisitForm(Model model) throws ParseException { 	
    	logger.debug("Buscar una Familia para una nueva visita");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "hsf/search2";
	}
	
	/**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "hsfs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visita> fetchFamiliasJson(@RequestParam(value = "codComunidad", required = true) String comunidad,
    		@RequestParam(value = "numVivienda", required = false, defaultValue = "") Integer numVivienda,
    		@RequestParam(value = "numFamilia", required = false, defaultValue = "") Integer numFamilia,
    		@RequestParam(value = "numFicha", required = false, defaultValue = "") Integer numFicha,
    		@RequestParam(value = "fechaVisitaDesde", required = false, defaultValue = "") String fechaVisitaDesde,
    		@RequestParam(value = "fechaVisitaHasta", required = false, defaultValue = "") String fechaVisitaHasta) throws ParseException {
        logger.info("Obteniendo las visitas en JSON");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        Date date2 = null;
		if (!fechaVisitaDesde.equals("")) date1 = formatter.parse(fechaVisitaDesde);
		if (!fechaVisitaHasta.equals("")) date2 = formatter.parse(fechaVisitaHasta);
        List<Visita> visitas = visitaService.getVisitas(comunidad, numVivienda, numFamilia, numFicha, date1, date2);
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;	
    }
    
    /**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "hsfsact", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visita> fetchFamiliasActivasJson(@RequestParam(value = "codComunidad", required = true) String comunidad,
    		@RequestParam(value = "numVivienda", required = false, defaultValue = "") Integer numVivienda,
    		@RequestParam(value = "numFamilia", required = false, defaultValue = "") Integer numFamilia,
    		@RequestParam(value = "numFicha", required = false, defaultValue = "") Integer numFicha,
    		@RequestParam(value = "fechaVisitaDesde", required = false, defaultValue = "") String fechaVisitaDesde,
    		@RequestParam(value = "fechaVisitaHasta", required = false, defaultValue = "") String fechaVisitaHasta) throws ParseException {
        logger.info("Obteniendo las visitas en JSON");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        Date date2 = null;
		if (!fechaVisitaDesde.equals("")) date1 = formatter.parse(fechaVisitaDesde);
		if (!fechaVisitaHasta.equals("")) date2 = formatter.parse(fechaVisitaHasta);
        List<Visita> visitas = visitaService.getVisitasFamActivas(comunidad, numVivienda, numFamilia, numFicha,date1,date2);
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;	
    }
    
    /**
     * Custom handler for displaying a family.
     *
     * @param idFamilia the ID of the familia to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("viewHsf/{idFamilia}")
    public String showVisit(@PathVariable("idFamilia") String idFamilia, Model model) {
        Familia familia = this.familiaService.getFamilia(idFamilia);
        if(familia!=null){
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
		        Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector().getCodigo());
		        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio().getCodigoNacional());
		        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais().getCodigo());
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
        		return "403";
        	}
	        
        }
		else{
			return "404";
		}
    }
    
    
    /**
     * Custom handler for displaying a family.
     *
     * @param idFamilia the ID of the familia to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("updateHsf/{idFamilia}")
    public String showFamilia(@PathVariable("idFamilia") String idFamilia, Model model) {
        Familia familia = this.familiaService.getFamilia(idFamilia);
        if(familia!=null){
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
		        Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector().getCodigo());
		        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio().getCodigoNacional());
		        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais().getCodigo());
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
		        return "hsf/actFamilia";
        	}
        	else{
        		return "403";
        	}
	        
        }
		else{
			return "404";
		}
    }
    
    
    /**
     * Custom handler for displaying a family.
     *
     * @param idFamilia the ID of the familia to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("newVisit/{idFamilia}")
    public String showFamilyVisit(@PathVariable("idFamilia") String idFamilia, Model model) {
        Familia familia = this.familiaService.getFamilia(idFamilia);
        if(familia!=null){
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
		        Sectores sector = this.sectorService.getSector(familia.getComunidad().getSector().getCodigo());
		        Divisionpolitica municipio = this.divPoliticaService.getDivisionpolitica(sector.getMunicipio().getCodigoNacional());
		        EntidadesAdtvas silais = this.entidadAdtvaService.getEntidadesAdtvas(municipio.getDependenciaSilais().getCodigo());
		        List<Profesion> profesiones = catalogoService.getProfesiones();
		        model.addAttribute("familia",familia);
		        model.addAttribute("sector",sector);
		        model.addAttribute("municipio",municipio);
		        model.addAttribute("silais",silais);
		        model.addAttribute("profesiones",profesiones);
		        return "hsf/newVisita";
        	}
        	else{
        		return "403";
        	}
	        
        }
		else{
			return "404";
		}
    }
    
    /**
     * Custom handler for displaying a person.
     *
     * @param idPersona the ID of the persona to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("newEvent/{idPersona}")
    public String showEventForm(@PathVariable("idPersona") String idPersona, Model model) {
    	Persona persona = this.personaService.getPersona(idPersona);
        Familia familia = this.familiaService.getFamilia(persona.getFamilia().getIdFamilia());
        if(familia!=null){
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
        		Visita visita = this.visitaService.getUltimaVisita(familia.getIdFamilia());
		        List<Evento> eventos = catalogoService.getEventos();
		        model.addAttribute("persona",persona);
		        model.addAttribute("fechaDeVisita",visita.getFechaVisita());
		        model.addAttribute("eventos",eventos);
		        return "hsf/newEvento";
        	}
        	else{
        		return "403";
        	}
	        
        }
		else{
			return "404";
		}
    }
    
    /**
     * Custom handler for voiding a family.
     *
     * @param idFamily the ID of the family to avoid
     * @return a String
     */
    @RequestMapping("/delete/anularFamilia/{idFamilia}")
    public String voidFamily(@PathVariable("idFamilia") String idFamilia, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Familia familia = familiaService.getFamilia(idFamilia);
    	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
    		familia.setPasive('1');
    		familiaService.addFamilia(familia);
    		List<Visita> visitas = this.visitaService.getVisitas(idFamilia);
    		for(Visita visita:visitas){
    			visita.setPasive('1');
    			visitaService.addVisita(visita);
    		}
    		List<Persona> personas = this.personaService.getPersonas(idFamilia);
    		for(Persona persona:personas){
    			persona.setPasive('1');
    			List<Enfermedades> enfermedades = enfermedadesService.getEnfermedadesPersona(persona.getIdPersona());
    			for(Enfermedades enfermedad:enfermedades){
    				enfermedad.setPasive('1');
    				enfermedadesService.addEnfermedades(enfermedad);
    			}
		    	List<EnfermedadesSocioCult> enfermedadesSoc = enfermedadesSocioCultService.getEnfermedadesSocioCultPersona(persona.getIdPersona());
		    	for(EnfermedadesSocioCult enfermedadSoc:enfermedadesSoc){
		    		enfermedadSoc.setPasive('1');
		    		enfermedadesSocioCultService.addEnfermedadesSocioCult(enfermedadSoc);
    			}
    			personaService.addPersona(persona);
    		}
    		CaractHigSanitarias carHigSan = this.caractHigSanitariasService.getCaractHigSanitariasFamilia(idFamilia);
    		if(carHigSan!=null){
    			carHigSan.setPasive('1');
    			caractHigSanitariasService.addCaractHigSanitarias(carHigSan);
    		}
	        FactSocioEconomicos factSocEc = this.factSocioEconomicosService.getFactSocioEconomicosFamilia(idFamilia);
	        if(factSocEc!=null){
	        	factSocEc.setPasive('1');
	        	factSocioEconomicosService.addFactSocioEconomicos(factSocEc);
	        }
	        FuncFamiliar funcFam = this.funcFamiliarService.getFuncFamiliarFamilia(idFamilia);
	        if(funcFam!=null){
	        	funcFam.setPasive('1');
	       		funcFamiliarService.addFuncFamiliar(funcFam);
	        }
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
	    	return "redirect:/info/viewHsf/{idFamilia}";	
    	}
    	else{
			return "403";
		}
    }
    
    /**
     * Custom handler for unvoiding a family.
     *
     * @param idFamily the ID of the family to avoid
     * @return a String
     */
    @RequestMapping("/delete/activarFamilia/{idFamilia}")
    public String unvoidFamily(@PathVariable("idFamilia") String idFamilia, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Familia familia = familiaService.getFamilia(idFamilia);
    	if(hsfService.verificarPermisoDatos(familia.getComunidad(), usuario)){
    		familia.setPasive('0');
    		familiaService.addFamilia(familia);
    		List<Visita> visitas = this.visitaService.getVisitas(idFamilia);
    		for(Visita visita:visitas){
    			visita.setPasive('0');
    			visitaService.addVisita(visita);
    		}
    		List<Persona> personas = this.personaService.getPersonas(idFamilia);
    		for(Persona persona:personas){
    			persona.setPasive('0');
    			List<Enfermedades> enfermedades = enfermedadesService.getEnfermedadesPersona(persona.getIdPersona());
    			for(Enfermedades enfermedad:enfermedades){
    				enfermedad.setPasive('0');
    				enfermedadesService.addEnfermedades(enfermedad);
    			}
		    	List<EnfermedadesSocioCult> enfermedadesSoc = enfermedadesSocioCultService.getEnfermedadesSocioCultPersona(persona.getIdPersona());
		    	for(EnfermedadesSocioCult enfermedadSoc:enfermedadesSoc){
		    		enfermedadSoc.setPasive('0');
		    		enfermedadesSocioCultService.addEnfermedadesSocioCult(enfermedadSoc);
    			}
    			personaService.addPersona(persona);
    		}
    		CaractHigSanitarias carHigSan = this.caractHigSanitariasService.getCaractHigSanitariasFamilia(idFamilia);
    		if(carHigSan!=null){
    			carHigSan.setPasive('0');
    			caractHigSanitariasService.addCaractHigSanitarias(carHigSan);
    		}
	        FactSocioEconomicos factSocEc = this.factSocioEconomicosService.getFactSocioEconomicosFamilia(idFamilia);
	        if(factSocEc!=null){
	        	factSocEc.setPasive('0');
	        	factSocioEconomicosService.addFactSocioEconomicos(factSocEc);
	        }
	        FuncFamiliar funcFam = this.funcFamiliarService.getFuncFamiliarFamilia(idFamilia);
	        if(funcFam!=null){
	        	funcFam.setPasive('0');
	       		funcFamiliarService.addFuncFamiliar(funcFam);
	        }
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);    	
	    	return "redirect:/info/viewHsf/{idFamilia}";	
    	}
    	else{
			return "403";
		}
    }
    
    
    /**
     * Custom handler for voiding a persona.
     *
     * @param idPersona the ID of the persona to avoid
     * @return a String
     */
    @RequestMapping("/delete/anularPersona/{idPersona}")
    public String voidPersona(@PathVariable("idPersona") String idPersona, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Persona persona = personaService.getPersona(idPersona);
    	if(hsfService.verificarPermisoDatos(persona.getFamilia().getComunidad(), usuario)){
    		persona.setPasive('1');
    		personaService.addPersona(persona);
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
    		String idFamilia = persona.getFamilia().getIdFamilia();
	    	return "redirect:/info/viewHsf/"+idFamilia;	
    	}
    	else{
			return "403";
		}
    }
    
    /**
     * Custom handler for unvoiding a persona.
     *
     * @param idPersona the ID of the persona to avoid
     * @return a String
     */
    @RequestMapping("/delete/activarPersona/{idPersona}")
    public String unvoidPersona(@PathVariable("idPersona") String idPersona, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Persona persona = personaService.getPersona(idPersona);
    	if(hsfService.verificarPermisoDatos(persona.getFamilia().getComunidad(), usuario)){
    		persona.setPasive('0');
    		personaService.addPersona(persona);
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
    		String idFamilia = persona.getFamilia().getIdFamilia();
	    	return "redirect:/info/viewHsf/"+idFamilia;	
    	}
    	else{
			return "403";
		}
    }
    
    /**
     * Custom handler for voiding a chs.
     *
     * @param idPersona the ID of the chs to avoid
     * @return a String
     */
    @RequestMapping("/delete/anularChs/{idChs}")
    public String voidChs(@PathVariable("idChs") String idChs, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	CaractHigSanitarias chs = caractHigSanitariasService.getCaractHigSanitarias(idChs);
    	if(hsfService.verificarPermisoDatos(chs.getFamilia().getComunidad(), usuario)){
    		chs.setPasive('1');
    		caractHigSanitariasService.addCaractHigSanitarias(chs);
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
    		String idFamilia = chs.getFamilia().getIdFamilia();
	    	return "redirect:/info/viewHsf/"+idFamilia;	
    	}
    	else{
			return "403";
		}
    }
    
    /**
     * Custom handler for voiding a fse.
     *
     * @param idFse the ID of the fse to avoid
     * @return a String
     */
    @RequestMapping("/delete/anularFse/{idFse}")
    public String voidFse(@PathVariable("idFse") String idFse, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	FactSocioEconomicos factSocEc = this.factSocioEconomicosService.getFactSocioEconomicos(idFse);    	
    	if(hsfService.verificarPermisoDatos(factSocEc.getFamilia().getComunidad(), usuario)){
    		factSocEc.setPasive('1');
    		factSocioEconomicosService.addFactSocioEconomicos(factSocEc);
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
    		String idFamilia = factSocEc.getFamilia().getIdFamilia();
	    	return "redirect:/info/viewHsf/"+idFamilia;	
    	}
    	else{
			return "403";
		}
    }
    
    /**
     * Custom handler for voiding a ff.
     *
     * @param idFf the ID of the ff to avoid
     * @return a String
     */
    @RequestMapping("/delete/anularFf/{idFf}")
    public String voidFf(@PathVariable("idFf") String idFf, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	FuncFamiliar funcFam = this.funcFamiliarService.getFuncFamiliar(idFf);  	
    	if(hsfService.verificarPermisoDatos(funcFam.getFamilia().getComunidad(), usuario)){
    		funcFam.setPasive('1');
    		funcFamiliarService.addFuncFamiliar(funcFam);
    		redirectAttributes.addFlashAttribute("procesoCompleto", true);
    		String idFamilia = funcFam.getFamilia().getIdFamilia();
	    	return "redirect:/info/viewHsf/"+idFamilia;	
    	}
    	else{
			return "403";
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
