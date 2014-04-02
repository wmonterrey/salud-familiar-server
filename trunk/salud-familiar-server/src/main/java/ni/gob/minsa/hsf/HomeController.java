package ni.gob.minsa.hsf;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Escolaridad;
import ni.gob.minsa.hsf.domain.Etnia;
import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.GrupoDispensarial;
import ni.gob.minsa.hsf.domain.Ocupacion;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.Religion;
import ni.gob.minsa.hsf.domain.Sexo;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.FamiliaService;
import ni.gob.minsa.hsf.service.PersonaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/*")
public class HomeController {
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
	@Resource(name="familiaService")
	private FamiliaService familiaService;
	@Resource(name="personaService")
	private PersonaService personaService;
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        logger.info("Historia Salud Familiar Iniciada");
        return "home";
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        logger.info("Testing");
        Comunidades comunidad = comunidadService.getComunidad(6223);
        Familia familia = new Familia();
        familia.setIdFamilia("00213133245-55467");
        familia.setCodFamilia("22424888624-2555");
        familia.setComunidad(comunidad);
        familia.setNumFamilia(4);
        familia.setNumVivienda(6);
        familia.setDireccion("DHHAHDJAD");
        
        Sexo sex = catalogoService.getSexo("SEXO|M");
        Etnia etnia = catalogoService.getEtnia("ETNIA|MTIZO");
        Escolaridad escda = catalogoService.getEscda("ESCDA|EDUSC");
        Ocupacion ocupacion = catalogoService.getOcupacion("HSF_OCUPA|EMP");
        Religion religion = catalogoService.getReligion("HSF_RELIG|CAT");
        GrupoDispensarial gd = catalogoService.getGrupoDispensarial("HSF_GD|GII");
        
        
        Persona persona = new Persona();
        persona.setIdPersona("00213133245-55467-01");
        persona.setCodPersona("22424888624-2555-01");
        persona.setIdPersonaSis(345);
        persona.setFamilia(familia);
        persona.setSexo(sex);
        persona.setEtnia(etnia);
        persona.setEscolaridad(escda);
        persona.setOcupacion(ocupacion);
        persona.setReligion(religion);
        persona.setPrimerApellido("AVILES");
        persona.setPrimerNombre("WILLIAM");
        persona.setGrupoDisp(gd);
        persona.setFallecido("No");
        
        
        familiaService.addFamilia(familia);
        personaService.addPersona(persona);
        return "home";
    }
    
    @RequestMapping(value = "/movil/ingreso", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getMessage() {
    	logger.info("Accessando a la aplicacion");
    	return "Acceso a la aplicaci�n concedido por el servidor.";
    }
    
}