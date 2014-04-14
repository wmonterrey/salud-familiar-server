package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.service.PersonaService;

@Controller
@RequestMapping("/movil/*")
public class PersonaController {
	
	@Resource(name="personaService")
	private PersonaService personaService;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);
	
    @RequestMapping(value = "personas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Persona> fetchPersonasJson() {
        logger.info("Obteniendo las personas en JSON");
        List<Persona> personas = personaService.getPersonas();
        if (personas == null){
        	logger.debug("Nulo");
        }
        else{
        	for (Persona persona : personas) {
        		persona.setFamilia(null);
        		persona.setEnfAgudas(null);
        		persona.setEnfCronicas(null);
        		persona.setEnfSocioC(null);
        	}
        }
        return personas;	
    }
}
