package ni.gob.minsa.hsf.web.controller;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.estructura.Cie10;
import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.service.Cie10Service;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;
import ni.gob.minsa.hsf.service.SectoresService;
import ni.gob.minsa.hsf.service.UnidadesService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web de peticiones relacionadas a listas
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/opciones/*")
public class OpcionesController {
	
	private static final Logger logger = LoggerFactory.getLogger(OpcionesController.class);
	@Resource(name="divPoliticaService")
	private DivisionPoliticaService divPoliticaService;
	@Resource(name="unidadesService")
	private UnidadesService unidadesService;
	@Resource(name="sectorService")
	private SectoresService sectorService;
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
	@Resource(name="cie10Service")
	private Cie10Service cie10Service;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	/**
     * Retorna una lista de unidades. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
     */
    @RequestMapping(value = "unidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Unidades> fetchUnidadesJson(@RequestParam(value = "muniId", required = true) String municipio) {
        logger.info("Obteniendo las unidades en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Unidades> unidades = unidadesService.getUnidadesMunicipio(municipio, usuario);
        if (unidades == null){
        	logger.debug("Nulo");
        }
        return unidades;	
    }
    
    /**
     * Retorna una lista de enfermedades. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de Cie10
     */
    @RequestMapping(value = "enfermedades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Cie10> fetchEnfermedadesJson(@RequestParam(value = "filtro", required = true) String filtro) {
        logger.info("Obteniendo las enfermedades en JSON");
        List<Cie10> enfermedades = cie10Service.getCie10Filtered(filtro);
        if (enfermedades == null){
        	logger.debug("Nulo");
        }
        return enfermedades;	
    }
        
    /**
     * Retorna una lista de divisionpolitica. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
     */
    @RequestMapping(value = "municipios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Divisionpolitica> fetchMunicipiosJson(@RequestParam(value = "entidadId", required = false, defaultValue="0" ) long entidad) {
        logger.info("Obteniendo los municipios en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Divisionpolitica> municipios = divPoliticaService.getMunicipios(entidad , usuario);
        if (municipios == null){
        	logger.debug("Nulo");
        }
        return municipios;	
    }
    
    /**
     * Retorna una lista de sectores. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de sectores
     */
    @RequestMapping(value = "sectores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Sectores> fetchSectoresJson(@RequestParam(value = "municipioId", required = true) String municipio) {
        logger.info("Obteniendo los sectores en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Sectores> sectores = sectorService.getSectoresMunicipio(municipio,usuario);
        if (sectores == null){
        	logger.debug("Nulo");
        }
        return sectores;	
    }
    
    /**
     * Retorna una lista de comunidades. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de comunidades
     */
    @RequestMapping(value = "comunidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Comunidades> fetchComunidadesJson(@RequestParam(value = "sectorId", required = true) String sector) {
        logger.info("Obteniendo las comunidades en JSON");
        List<Comunidades> comunidades = comunidadService.getComunidadesSector(sector);
        if (comunidades == null){
        	logger.debug("Nulo");
        }
        return comunidades;	
    }
    
}
