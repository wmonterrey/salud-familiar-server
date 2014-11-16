package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.Areas;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.ReportesService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserSistema;

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
@RequestMapping("/report/*")
public class ReportesController {
	private static final Logger logger = LoggerFactory.getLogger(ReportesController.class);
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	@Resource(name="reportesService")
	private ReportesService reportesService;
	
	@RequestMapping(value = "visitbyday", method = RequestMethod.GET)
    public String initReport1Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reportes por fecha");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/byday";
	}
	
	/**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "visitsbyday", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> fetchVisitasDiaJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad,
    		@RequestParam(value = "desde", required = true) String fec1,
    		@RequestParam(value = "hasta", required = true) String fec2) throws ParseException {
        logger.info("Obteniendo las visitas en JSON");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatter.parse(fec1);
        Date date2 = formatter.parse(fec2);
        List<Object> visitas = reportesService.visitasDia(codArea, codSilais, codMunicipio, codUnidad, codSector, codComunidad, date1.getTime(), date2.getTime());
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;
        	
    }
	
}
