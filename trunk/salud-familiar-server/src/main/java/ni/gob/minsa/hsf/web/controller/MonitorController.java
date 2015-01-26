package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import javax.annotation.Resource;

import ni.gob.minsa.hsf.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador web de peticiones relacionadas a hsf
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/monitor/*")
public class MonitorController {
	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value = "activity", method = RequestMethod.GET)
    public String initMonitorActivity(Model model) throws ParseException { 	
    	logger.debug("Inicia monitoreo de usuarios por fecha");
    	return "monitor/actividad";
	}
	
	@RequestMapping(value = "noactivity", method = RequestMethod.GET)
    public String initMonitorNoActivity(Model model) throws ParseException { 	
    	logger.debug("Inicia monitoreo de usuarios no activos por fecha");
    	return "monitor/noactividad";
	}
}
