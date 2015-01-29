package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Enfermedades;
import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.Visita;
import ni.gob.minsa.hsf.domain.catalogos.Areas;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.report.Consolidado;
import ni.gob.minsa.hsf.domain.report.ReporteCaract;
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
    
    @RequestMapping(value = "visitbyarea", method = RequestMethod.GET)
    public String initReport2Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reportes por area");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/byarea";
	}
    
    /**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "visitsbyarea", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> fetchVisitasAreaJson(@RequestParam(value = "area", required = true) String codArea,
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
        List<Object> visitas = reportesService.visitasArea(codArea, codSilais, codMunicipio, codUnidad, codSector, codComunidad, date1.getTime(), date2.getTime());
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;
        	
    }
    
    @RequestMapping(value = "consolidado", method = RequestMethod.GET)
    public String initReport3Form(Model model) throws ParseException { 	
    	logger.debug("Inicia consolidado");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/consolidado";
	}
    
    
    /**
     * Retorna una lista de visitas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
	 * @throws ParseException 
     */
    @RequestMapping(value = "consolidados", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Consolidado> fetchConsolidadoAreaJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad)
    		{
        logger.info("Obteniendo consolidado en JSON");
        List<Consolidado> consolidado = reportesService.getConsolidado(codArea, codSilais, codMunicipio, codUnidad, codSector, codComunidad);
        if (consolidado == null){
        	logger.debug("Nulo");
        }
        return consolidado;
        	
    }
    
    @RequestMapping(value = "embarazo", method = RequestMethod.GET)
    public String initReport4Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de embarazos");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/embarazos";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON 
	 * @throws ParseException 
     */
    @RequestMapping(value = "embarazos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> fetchEmbarazosAreaJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad) {
        logger.info("Obteniendo las embarazos en JSON");
        List<Object> embarazos = reportesService.embarazosArea(codArea, codSilais, codMunicipio, 
        		codUnidad, codSector, codComunidad);
        if (embarazos == null){
        	logger.debug("Nulo");
        }
        return embarazos;
    }
    
    @RequestMapping(value = "cronico", method = RequestMethod.GET)
    public String initReport5Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de cronicos");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/cronicos";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "cronicos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> fetchCronicosAreaJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad) {
        logger.info("Obteniendo los cronicos en JSON");
        List<Object> cronicos = reportesService.cronicosArea(codArea, codSilais, codMunicipio, 
        		codUnidad, codSector, codComunidad);
        if (cronicos == null){
        	logger.debug("Nulo");
        }
        return cronicos;
    }
    
    @RequestMapping(value = "enfermedad", method = RequestMethod.GET)
    public String initReport6Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de enfermedades");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/enfermedades";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "enfermedades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> fetchEnfermedadesJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad) {
        logger.info("Obteniendo los enfermedades en JSON");
        List<Object> enfermedades = reportesService.getEnfermedades(codArea, codSilais, codMunicipio, 
        		codUnidad, codSector, codComunidad);
        if (enfermedades == null){
        	logger.debug("Nulo");
        }
        return enfermedades;
    }
    
    @RequestMapping(value = "family", method = RequestMethod.GET)
    public String initReport7Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de familias");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "report/familias";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "families", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Familia> fetchFamiliasJson(@RequestParam(value = "comunidad", required = true) String codComunidad) {
        logger.info("Obteniendo los familias en JSON");
        List<Familia> familias = reportesService.getFamilias(codComunidad);
        if (familias == null){
        	logger.debug("Nulo");
        }
        return familias;
    }
    
    @RequestMapping(value = "visit", method = RequestMethod.GET)
    public String initReport8Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de visitas");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "report/visitas";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "visits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visita> fetchVisitasJson(@RequestParam(value = "comunidad", required = true) String codComunidad) {
        logger.info("Obteniendo las visitas en JSON");
        List<Visita> visitas = reportesService.getVisitas(codComunidad);
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;
    }
    
    
    @RequestMapping(value = "person", method = RequestMethod.GET)
    public String initReport9Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de personas");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "report/personas";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "persons", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Persona> fetchPersonasJson(@RequestParam(value = "comunidad", required = true) String codComunidad) {
        logger.info("Obteniendo las personas en JSON");
        List<Persona> personas = reportesService.getPersonas(codComunidad);
        if (personas == null){
        	logger.debug("Nulo");
        }
        return personas;
    }
    
    @RequestMapping(value = "ill", method = RequestMethod.GET)
    public String initReport10Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de enfermedades");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "report/enfermos";
	}
	
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "ills", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Enfermedades> fetchEnfermedadesJson(@RequestParam(value = "comunidad", required = true) String codComunidad,
    		@RequestParam(value = "enfermedad", required = true) String codEnfermedad) {
        logger.info("Obteniendo las Enfermedades en JSON");
        List<Enfermedades> enfermedades = reportesService.getEnfermedades(codComunidad, codEnfermedad);
        if (enfermedades == null){
        	logger.debug("Nulo");
        }
        return enfermedades;
    }
    
    @RequestMapping(value = "pregnancy", method = RequestMethod.GET)
    public String initReport11Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de embarazadas");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "report/embarazadas";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON
	 * @throws ParseException 
     */
    @RequestMapping(value = "pregnancies", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Persona> fetchEmbarazosJson(@RequestParam(value = "comunidad", required = true) String codComunidad) {
        logger.info("Obteniendo los embarazos en JSON");
        List<Persona> embarazos = reportesService.getEmbarazos(codComunidad);
        if (embarazos == null){
        	logger.debug("Nulo");
        }
        return embarazos;
    }
    
    @RequestMapping(value = "pers", method = RequestMethod.GET)
    public String initReport12Form(Model model) throws ParseException { 	
    	logger.debug("Inicia reporte de caract de personas");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	List<Areas> areas = catalogoService.getAreas(usuario);
    	model.addAttribute("areas", areas);
    	model.addAttribute("entidades", entidades);
    	return "report/caractpers";
	}
    
    /**
     * Acepta una solicitud GET para JSON
     * @return Un arreglo JSON 
	 * @throws ParseException 
     */
    @RequestMapping(value = "caractpers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ReporteCaract> fetchCaractPersAreaJson(@RequestParam(value = "area", required = true) String codArea,
    		@RequestParam(value = "variable", required = true) String codVariable,
    		@RequestParam(value = "silais", required = false) Long codSilais,
    		@RequestParam(value = "municipio", required = false, defaultValue = "") String codMunicipio,
    		@RequestParam(value = "unidad", required = false) Long codUnidad,
    		@RequestParam(value = "sector", required = false, defaultValue = "") String codSector,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String codComunidad) {
        logger.info("Obteniendo las caracterizacion de personas en JSON");
        
        List<ReporteCaract> datos = reportesService.caratPersonas(codArea, codVariable, codSilais, codMunicipio, 
        		codUnidad, codSector, codComunidad);
        if (datos == null){
        	logger.debug("Nulo");
        }
        return datos;
    }
}
