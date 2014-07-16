package ni.gob.minsa.hsf.webcontroller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.Profesion;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador web de peticiones relacionadas a usuarios
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
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas();
    	List<Profesion> profesiones = catalogoService.getProfesiones();
    	model.addAttribute("entidades", entidades);
    	model.addAttribute("profesiones", profesiones);
    	return "hsf/create";
	}	
	
}
