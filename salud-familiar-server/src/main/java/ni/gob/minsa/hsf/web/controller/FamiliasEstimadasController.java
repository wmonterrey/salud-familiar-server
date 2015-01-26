package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.domain.report.FamEstComunidad;
import ni.gob.minsa.hsf.domain.report.FamEstDivPol;
import ni.gob.minsa.hsf.domain.report.FamEstEntidad;
import ni.gob.minsa.hsf.domain.report.FamEstSector;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.SectoresService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.service.report.FamEstComunidadService;
import ni.gob.minsa.hsf.service.report.FamEstDivPolService;
import ni.gob.minsa.hsf.service.report.FamEstEntidadService;
import ni.gob.minsa.hsf.service.report.FamEstSectorService;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avilés;
 */
@Controller
@RequestMapping("/catalog/*")
public class FamiliasEstimadasController {
	private static final Logger logger = LoggerFactory.getLogger(FamiliasEstimadasController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="famEstEntidadService")
	private FamEstEntidadService famEstEntidadService;
	@Resource(name="famEstDivPolService")
	private FamEstDivPolService famEstDivPolService;
	@Resource(name="famEstSectorService")
	private FamEstSectorService famEstSectorService;
	@Resource(name="famEstComunidadService")
	private FamEstComunidadService famEstComunidadService;
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	@Resource(name="divPoliticaService")
	private DivisionPoliticaService divPoliticaService;
	@Resource(name="sectorService")
	private SectoresService sectorService;
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
	
	@RequestMapping(value = "famest/entidad", method = RequestMethod.GET)
    public String obtenerFamEstimadasEntidad(Model model) throws ParseException { 	
    	logger.debug("Mostrando familias estimadas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<FamEstEntidad> famestentidad = famEstEntidadService.getFamEstEntidad(usuario);
    	model.addAttribute("famestentidades", famestentidad);
    	return "catalogos/famEstimadas/entidad";
	}	
	
	@RequestMapping(value = "famest/addEntidad", method = RequestMethod.GET)
	public String initCreationEntForm(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	FamEstEntidad famEstEntidad = new FamEstEntidad();
    	model.addAttribute("entidades", entidades);
    	model.addAttribute("famEstEntidad", famEstEntidad);
		return "catalogos/famEstimadas/formEntidad";
	}
	
	@RequestMapping(value = "famest/editEntidad/{famEstEntidadId}", method = RequestMethod.GET)
	public String initEditionEntForm(@PathVariable("famEstEntidadId") String famEstEntidadId, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	FamEstEntidad famEstEntidad = this.famEstEntidadService.getFamEstEntidad(famEstEntidadId);
    	model.addAttribute("entidades", entidades);
    	model.addAttribute("famEstEntidad", famEstEntidad);
		return "catalogos/famEstimadas/formEntidad";
	}
	
	@RequestMapping("famest/delEntidad/{famEstEntidadId}")
	    public String borrarFamEstEntidad(@PathVariable("famEstEntidadId") String famEstEntidadId, 
	    		RedirectAttributes redirectAttributes) {
	    	  	this.famEstEntidadService.delFamEstEntidad(famEstEntidadId);
		    	return "redirect:/catalog/famest/entidad";
	}
	
	@RequestMapping( value="famest/saveEntidad", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationEntForm( @RequestParam(value="entidad", required=true ) Long entidad
	        , @RequestParam( value="famEstimadas", required=true ) Long famEstimadas
	        , @RequestParam( value="famEstEntidadId", required=true) String famEstEntidadId	        
	        )
	{
		FamEstEntidad famEstEntidad = new FamEstEntidad();
		if (famEstEntidadId.equals("")){
			famEstEntidadId = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
		}
		else{
			famEstEntidad =  this.famEstEntidadService.getFamEstEntidad(famEstEntidadId);
		}
		famEstEntidad.setEntidad(this.entidadAdtvaService.getEntidadesAdtvas(entidad));
		famEstEntidad.setFamEstimadas(famEstimadas);
		famEstEntidad.setFamEstEntidadId(famEstEntidadId);
		this.famEstEntidadService.addFFamEstEntidad(famEstEntidad);
		return createJsonResponse(famEstEntidad);
		
	}
	
	@RequestMapping(value = "famest/divpol", method = RequestMethod.GET)
    public String obtenerFamEstimadasDivPol(Model model) throws ParseException { 	
    	logger.debug("Mostrando familias estimadas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "catalogos/famEstimadas/divpol";
	}
	
	
	/**
     * Retorna una lista de FamEstimadasDivPol. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de FamEstimadasDivPol
     */
    @RequestMapping(value = "famest/updivpol", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamEstDivPol> fetchFamEstimadasDivPolJson(@RequestParam(value = "entidadId", required = false, defaultValue="0" ) long entidad) {
        logger.info("Obteniendo los FamEstDivPol en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FamEstDivPol> famEstimadas = this.famEstDivPolService.getFamEstDivPol(usuario, entidad);
        if (famEstimadas == null){
        	logger.debug("Nulo");
        }
        return famEstimadas;	
    }
    
    @RequestMapping(value = "famest/addDivPol/{codSilais}", method = RequestMethod.GET)
	public String initCreationDivForm(@PathVariable("codSilais") long silais, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<Divisionpolitica> munis = this.divPoliticaService.getMunicipios(silais, usuario);
    	FamEstDivPol famEstDivPol = new FamEstDivPol();
    	model.addAttribute("munis", munis);
    	model.addAttribute("famEstDivPol", famEstDivPol);
		return "catalogos/famEstimadas/formDivPol";
	}
    
    @RequestMapping(value = "famest/editDivPol/{famEstDivPolId}", method = RequestMethod.GET)
	public String initEditionDivForm(@PathVariable("famEstDivPolId") String famEstDivPolId, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	FamEstDivPol famEstDivPol = this.famEstDivPolService.getFamEstDivPol(famEstDivPolId);
    	List<Divisionpolitica> munis = this.divPoliticaService.getMunicipios(famEstDivPol.getMunicipio().getDependenciaSilais().getCodigo(), usuario);
    	model.addAttribute("munis", munis);
    	model.addAttribute("famEstDivPol", famEstDivPol);
		return "catalogos/famEstimadas/formDivPol";
	}
    
    @RequestMapping("famest/delDivPol/{famEstDivPolId}")
    public String borrarFamEstDivPol(@PathVariable("famEstDivPolId") String famEstDivPolId, 
    		RedirectAttributes redirectAttributes) {
    	  	this.famEstDivPolService.delFamEstDivPol(famEstDivPolId);
	    	return "redirect:/catalog/famest/divpol";
    }
    
    @RequestMapping( value="famest/saveDivPol", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationDivForm( @RequestParam(value="municipio", required=true ) String codMunicipio
	        , @RequestParam( value="famEstimadas", required=true ) Long famEstimadas
	        , @RequestParam( value="famEstDivPolId", required=true) String famEstDivPolId	        
	        )
	{
    	FamEstDivPol famEstDivPol = new FamEstDivPol();
		if (famEstDivPolId.equals("")){
			famEstDivPolId = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
		}
		else{
			famEstDivPol =  this.famEstDivPolService.getFamEstDivPol(famEstDivPolId);
		}
		famEstDivPol.setMunicipio(this.divPoliticaService.getDivisionpolitica(codMunicipio));
		famEstDivPol.setFamEstimadas(famEstimadas);
		famEstDivPol.setFamEstDivPolId(famEstDivPolId);
		this.famEstDivPolService.addFamEstDivPol(famEstDivPol);
		return createJsonResponse(famEstDivPol);
		
	}
    
    @RequestMapping(value = "famest/sector", method = RequestMethod.GET)
    public String obtenerFamEstSector(Model model) throws ParseException { 	
    	logger.debug("Mostrando familias estimadas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "catalogos/famEstimadas/sectores";
	}
    
    /**
     * Retorna una lista de FamEstSector. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de FamEstSector
     */
    @RequestMapping(value = "famest/upsector", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamEstSector> fetchFamEstSectorJson(@RequestParam(value = "codMunicipio", required = true) String codMunicipio) {
        logger.info("Obteniendo los FamEstSector en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FamEstSector> famEstimadas = this.famEstSectorService.getFamEstSector(usuario, codMunicipio);
        if (famEstimadas == null){
        	logger.debug("Nulo");
        }
        return famEstimadas;	
    }
    
    @RequestMapping(value = "famest/addSector/{codDivPol}", method = RequestMethod.GET)
	public String initCreationSecForm(@PathVariable("codDivPol") String codDivPol, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<Sectores> sectores = this.sectorService.getSectoresMunicipio(codDivPol, usuario);
    	FamEstSector famEstSector = new FamEstSector();
    	model.addAttribute("sectores", sectores);
    	model.addAttribute("famEstSector", famEstSector);
		return "catalogos/famEstimadas/formSector";
	}
    
    @RequestMapping(value = "famest/editSector/{famEstSectorId}", method = RequestMethod.GET)
	public String initEditionSecForm(@PathVariable("famEstSectorId") String famEstSectorId, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	FamEstSector famEstSector = this.famEstSectorService.getFamEstSector(famEstSectorId);
    	List<Sectores> sectores = this.sectorService.getSectoresMunicipio(famEstSector.getSector().getMunicipio().getCodigoNacional(), usuario);
    	model.addAttribute("sectores", sectores);
    	model.addAttribute("famEstSector", famEstSector);
		return "catalogos/famEstimadas/formSector";
	}
    
    @RequestMapping("famest/delSector/{famEstSectorId}")
    public String borrarFamEstSector(@PathVariable("famEstSectorId") String famEstSectorId, 
    		RedirectAttributes redirectAttributes) {
    	  	this.famEstSectorService.delFamEstSector(famEstSectorId);
	    	return "redirect:/catalog/famest/sector";
    }
    
    @RequestMapping( value="famest/saveSector", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationSecForm( @RequestParam(value="sector", required=true ) String codSector
	        , @RequestParam( value="famEstimadas", required=true ) Long famEstimadas
	        , @RequestParam( value="famEstSectorId", required=true) String famEstSectorId	        
	        )
	{
    	FamEstSector famEstSector = new FamEstSector();
		if (famEstSectorId.equals("")){
			famEstSectorId = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
		}
		else{
			famEstSector =  this.famEstSectorService.getFamEstSector(famEstSectorId);
		}
		famEstSector.setSector(this.sectorService.getSector(codSector));
		famEstSector.setFamEstimadas(famEstimadas);
		famEstSector.setFamEstSectorId(famEstSectorId);
		this.famEstSectorService.addFamEstSector(famEstSector);
		return createJsonResponse(famEstSector);
		
	}
    
    
    @RequestMapping(value = "famest/comunidad", method = RequestMethod.GET)
    public String obtenerFamEstComunidad(Model model) throws ParseException { 	
    	logger.debug("Mostrando familias estimadas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("entidades", entidades);
    	return "catalogos/famEstimadas/comunidades";
	}
    
    /**
     * Retorna una lista de FamEstComunidad. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de FamEstComunidad
     */
    @RequestMapping(value = "famest/upcomunidad", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamEstComunidad> fetchFamEstComunidadJson(@RequestParam(value = "codSector", required = true) String codSector) {
        logger.info("Obteniendo los FamEstComunidad en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FamEstComunidad> famEstimadas = this.famEstComunidadService.getFamEstComunidad(usuario, codSector);
        if (famEstimadas == null){
        	logger.debug("Nulo");
        }
        return famEstimadas;	
    }
    
    @RequestMapping(value = "famest/addComunidad/{codSector}", method = RequestMethod.GET)
	public String initCreationComForm(@PathVariable("codSector") String codSector, Model model) {
    	List<Comunidades> comunidades = this.comunidadService.getComunidadesSector(codSector);
    	FamEstComunidad famEstComunidad = new FamEstComunidad();
    	model.addAttribute("comunidades", comunidades);
    	model.addAttribute("famEstComunidad", famEstComunidad);
		return "catalogos/famEstimadas/formComunidad";
	}
    
    @RequestMapping(value = "famest/editComunidad/{famEstComuId}", method = RequestMethod.GET)
	public String initEditionComForm(@PathVariable("famEstComuId") String famEstComuId, Model model) {    	
    	FamEstComunidad famEstComunidad = this.famEstComunidadService.getFamEstComunidad(famEstComuId);
    	List<Comunidades> comunidades = this.comunidadService.getComunidadesSector(famEstComunidad.getComunidad().getSector().getCodigo());
    	model.addAttribute("comunidades", comunidades);
    	model.addAttribute("famEstComunidad", famEstComunidad);
		return "catalogos/famEstimadas/formComunidad";
	}
    
    @RequestMapping("famest/delComunidad/{famEstComuId}")
    public String borrarFamEstComunidad(@PathVariable("famEstComuId") String famEstComuId, 
    		RedirectAttributes redirectAttributes) {
    	  	this.famEstComunidadService.delFamEstComunidad(famEstComuId);
	    	return "redirect:/catalog/famest/comunidad";
    }
    
    @RequestMapping( value="famest/saveComunidad", method=RequestMethod.POST)
	public ResponseEntity<String> processCreationComForm( @RequestParam(value="comunidad", required=true ) String codComunidad
	        , @RequestParam( value="famEstimadas", required=true ) Long famEstimadas
	        , @RequestParam( value="famEstComuId", required=true) String famEstComuId	        
	        )
	{
    	FamEstComunidad famEstComunidad = new FamEstComunidad();
		if (famEstComuId.equals("")){
			famEstComuId = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
		}
		else{
			famEstComunidad =  this.famEstComunidadService.getFamEstComunidad(famEstComuId);
		}
		famEstComunidad.setComunidad(this.comunidadService.getComunidad(codComunidad));
		famEstComunidad.setFamEstimadas(famEstimadas);
		famEstComunidad.setFamEstComuId(famEstComuId);
		this.famEstComunidadService.addFamEstComunidad(famEstComunidad);
		return createJsonResponse(famEstComunidad);
		
	}
	
	private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson( o );
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
}
