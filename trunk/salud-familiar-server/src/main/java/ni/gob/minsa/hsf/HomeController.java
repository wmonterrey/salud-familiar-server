package ni.gob.minsa.hsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.service.DashboardService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/*")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Resource(name="dashboardService")
	private DashboardService dashboardService;
    @Resource(name="usuarioService")
	private UsuarioService usuarioService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("Historia Salud Familiar Iniciada");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        DateTime dt2 = new DateTime();
        DateTime dt1 = dt2.minusDays(30);
        long d1 =dt1.getMillis();
        long d2 =dt2.getMillis();
        List<Object> visitasDia = dashboardService.visitasDia(usuario,d1,d2);
        List<Object> visitasArea = dashboardService.visitasArea(usuario,d1,d2);
        model.addAttribute("visitasDia", visitasDia);
        model.addAttribute("visitasArea", visitasArea);
        model.addAttribute("usuario", usuario);
        return "home";
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(fec1);
        Date date2 = formatter.parse(fec2);
        Map<String, List<Object>> mapModel = new HashMap<String, List<Object>>();
        List<Object> visitasDia = dashboardService.visitasDia(usuario,date1.getTime(),date2.getTime());
        List<Object> visitasArea = dashboardService.visitasArea(usuario,date1.getTime(),date2.getTime());
        mapModel.put("lista1", visitasDia);
        mapModel.put("lista2", visitasArea);
        return mapModel;
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
    
    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
    	model.addAttribute("error", "true");
		return "login";
	}
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String noAcceso() {
		return "403"; 
	}
	
	@RequestMapping(value="/404", method = RequestMethod.GET)
	public String noEncontrado() { 
		return "404";
	}
    
    @RequestMapping(value = "/movil/ingreso", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getMessage() {
    	logger.info("Accessando a la aplicacion");
    	return "Acceso a la aplicación concedido por el servidor.";
    }
    
	@RequestMapping( value="keepsession")
	public @ResponseBody String keepSession()
	{	
		return "OK";
	}
    
}
