package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.service.MonitoreoService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web de peticiones relacionadas a hsf
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/monitor/*")
public class MonitorController {
	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Resource(name="monitoreoService")
	private MonitoreoService monitoreoService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value = "activity", method = RequestMethod.GET)
    public String initMonitorActivity(Model model) throws ParseException { 	
    	logger.debug("Inicia monitoreo de usuarios por fecha");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        DateTime dt2 = new DateTime();
        DateTime dt1 = dt2.minusDays(30);
        long d1 =dt1.getMillis();
        long d2 =dt2.getMillis();
    	List<Object> accesosUsuarios = monitoreoService.getUserAccess(usuario,d1,d2);
    	List<Object> accesosDias = monitoreoService.getDaysAccess(usuario,d1,d2);
    	model.addAttribute("accesosUsuarios", accesosUsuarios);
    	model.addAttribute("accesosDias", accesosDias);
    	return "monitor/actividad";
	}
	
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
	@RequestMapping(value = "updateDashboard", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, List<Object>> fetchDashboardJson(@RequestParam(value = "start", required = true) String fec1,
    		@RequestParam(value = "end", required = true) String fec2) throws ParseException {
        logger.info("Actualizando dashboard");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        DateTime dt1 = new DateTime(Integer.parseInt(fec1.substring(0, 4)),Integer.parseInt(fec1.substring(5, 7)),Integer.parseInt(fec1.substring(8, 10)),0,0,0,0);
        DateTime dt2 = new DateTime(Integer.parseInt(fec2.substring(0, 4)),Integer.parseInt(fec2.substring(5, 7)),Integer.parseInt(fec2.substring(8, 10)),23,59,59,999);
        Map<String, List<Object>> mapModel = new HashMap<String, List<Object>>();
        List<Object> accesosUsers = monitoreoService.getUserAccess(usuario,dt1.getMillis(),dt2.getMillis());
        List<Object> accesosDias = monitoreoService.getDaysAccess(usuario,dt1.getMillis(),dt2.getMillis());
        mapModel.put("lista1", accesosUsers);
        mapModel.put("lista2", accesosDias);
        return mapModel;
    }
	
	@RequestMapping(value = "noactivity", method = RequestMethod.GET)
    public String initMonitorNoActivity(Model model) throws ParseException { 	
    	logger.debug("Inicia monitoreo de usuarios no activos por fecha");
    	return "monitor/noactividad";
	}
}
