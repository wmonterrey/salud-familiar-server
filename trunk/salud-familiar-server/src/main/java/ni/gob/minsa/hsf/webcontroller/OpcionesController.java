package ni.gob.minsa.hsf.webcontroller;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.service.ComunidadesService;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;
import ni.gob.minsa.hsf.service.SectoresService;
import ni.gob.minsa.hsf.service.UnidadesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
     * Retorna una lista de unidades. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
     */
    @RequestMapping(value = "unidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Unidades> fetchUnidadesJson(@RequestParam(value = "entidadId", required = true) long entidad) {
        logger.info("Obteniendo las unidades en JSON");
        List<Unidades> unidades = unidadesService.getUnidadesEntidad(entidad);
        if (unidades == null){
        	logger.debug("Nulo");
        }
        return unidades;	
    }
    
    
    /**
     * Retorna una lista de divisionpolitica. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de unidades
     */
    @RequestMapping(value = "municipios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Divisionpolitica> fetchMunicipiosJson(@RequestParam(value = "entidadId", required = true) long entidad) {
        logger.info("Obteniendo los municipios en JSON");
        List<Divisionpolitica> municipios = divPoliticaService.getMunicipios(entidad);
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
        List<Sectores> sectores = sectorService.getSectoresMunicipio(municipio);
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
