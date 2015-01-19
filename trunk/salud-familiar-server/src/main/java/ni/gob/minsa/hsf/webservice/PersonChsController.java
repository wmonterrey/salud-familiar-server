package ni.gob.minsa.hsf.webservice;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.ws.PersonChs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web de peticiones relacionadas a web services
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/ws/*")
public class PersonChsController {
	private static final Logger logger = LoggerFactory.getLogger(PersonChsController.class);
	@Resource(name="personChsService")
	private PersonChsService personChsService;
	
	/**
     * Retorna una personaChs. Acepta una solicitud GET para JSON
     * @return Una CaractHigSanitarias JSON.
     */
    @RequestMapping(value = "persona", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody PersonChs fetchPersonChsJson(@RequestParam(value = "cedula", required = true) String cedula) {
        logger.info("Obteniendo la respuesta en JSON");
        PersonChs personChs = personChsService.getData(cedula);
        return personChs;	
    }

}
