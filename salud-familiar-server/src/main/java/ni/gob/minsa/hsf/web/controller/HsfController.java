package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.catalogos.Escolaridad;
import ni.gob.minsa.hsf.domain.catalogos.Etnia;
import ni.gob.minsa.hsf.domain.catalogos.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.catalogos.Ocupacion;
import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.catalogos.Religion;
import ni.gob.minsa.hsf.domain.catalogos.Sexo;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.FamiliaService;
import ni.gob.minsa.hsf.service.PersonaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	@Resource(name="familiaService")
	private FamiliaService familiaService;
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
	
	@RequestMapping( value="newHsf", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationForm( @RequestParam(value="numPersona", required=true ) String numPersona
			, @RequestParam( value="nombres", required=true ) String nombres
			, @RequestParam( value="primerApellido", required=true ) String primerApellido
			, @RequestParam( value="segundoApellido", required=true ) String segundoApellido
	        )
	{
		
		Familia familia = familiaService.getFamilia("00213133245-55467");
		
		
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
