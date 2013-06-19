package ni.gob.minsa.comunitaria.familiar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Maneja las solicitudes que van a la aplicacion
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/*")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Selecciona la vista principal regresando su nombre.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        logger.info("Historia Salud Familiar Iniciada");
        return "home";
    }
    
    
    @RequestMapping(value = "/movil/ingreso", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getMessage() {
    	logger.info("Accessando a la aplicacion");
    	return "Acceso a la aplicación concedido por el servidor.";
    }
    
}
