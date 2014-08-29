package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.MovilInfo;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.Visita;
import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.FamiliaService;
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
	@Resource(name="personaService")
	private PersonaService personaService;
	
	
	
	@RequestMapping(value = "newHsf", method = RequestMethod.GET)
    public String initCreationForm(Model model) throws ParseException { 	
    	logger.debug("Crear nueva HSF");
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas();
    	List<Profesion> profesiones = catalogoService.getProfesiones();
    	model.addAttribute("entidades", entidades);
    	model.addAttribute("profesiones", profesiones);
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
		visita.setMovilInfo(movilInfo);
		visitaService.addVisita(visita);
		return createJsonResponse(visita);
	}
	
	@RequestMapping( value="newPersona", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationPersonaForm( @RequestParam(value="idFamiliaPerson", required=true ) String idFamiliaPerson
			, @RequestParam(value="numPersona", required=true ) String numPersona
			, @RequestParam( value="nombres", required=true ) String nombres
			, @RequestParam( value="primerApellido", required=true ) String primerApellido
			, @RequestParam( value="segundoApellido", required=true ) String segundoApellido
	        )
	{
		
		Familia familia = familiaService.getFamilia(idFamiliaPerson);
		
		
		Sexo sex = catalogoService.getSexo("SEXO|M");
        Etnia etnia = catalogoService.getEtnia("ETNIA|MTIZO");
        Escolaridad escda = catalogoService.getEscda("ESCDA|EDUSC");
        Ocupacion ocupacion = catalogoService.getOcupacion("HSF_OCUPA|EMP");
        Religion religion = catalogoService.getReligion("HSF_RELIG|CAT");
        GrupoDispensarial gd = catalogoService.getGrupoDispensarial("HSF_GD|GII");
        
		Persona persona = new Persona();
        persona.setIdPersona(numPersona);
        persona.setCodPersona("22424888624-2555-01");
        persona.setNumPersona(1);
        persona.setIdPersonaSis(345);
        persona.setFamilia(familia);
        persona.setSexo(sex);
        persona.setEtnia(etnia);
        persona.setEscolaridad(escda);
        persona.setOcupacion(ocupacion);
        persona.setReligion(religion);
        persona.setPrimerApellido(primerApellido);
        persona.setSegundoApellido(segundoApellido);
        persona.setNombres(nombres);
        persona.setGrupoDisp(gd);
        persona.setFallecido("No");
        personaService.addPersona(persona);
		
		return createJsonResponse(persona);
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
