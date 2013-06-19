package ni.gob.minsa.comunitaria.familiar;

import java.util.List;
import javax.annotation.Resource;

import ni.gob.minsa.comunitaria.familiar.domain.CatGen;
import ni.gob.minsa.comunitaria.familiar.domain.Comunidad;
import ni.gob.minsa.comunitaria.familiar.domain.Divisionpolitica;
import ni.gob.minsa.comunitaria.familiar.domain.Sector;
import ni.gob.minsa.comunitaria.familiar.service.CatGenService;
import ni.gob.minsa.comunitaria.familiar.service.ComunidadService;
import ni.gob.minsa.comunitaria.familiar.service.DivisionPoliticaService;
import ni.gob.minsa.comunitaria.familiar.service.SectorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Maneja las solicitudes que van a la aplicacion relacionadas a json
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/movil/*")
public class EnvioController {
	@Resource(name="divisionPoliticaService")
	private DivisionPoliticaService divisionPoliticaService;
	@Resource(name="sectorService")
	private SectorService sectorService;
	@Resource(name="comunidadService")
	private ComunidadService comunidadService;
	@Resource(name="catGenService")
	private CatGenService catGenService;
	
	private static final Logger logger = LoggerFactory.getLogger(EnvioController.class);
    
    /**
     * Retorna una lista de SILAIS. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de SILAIS
     */
    @RequestMapping(value = "nicaragua", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Divisionpolitica> fetchDivisionJson() {
        logger.info("Obteniendo la division politica en JSON"); 
        List<Divisionpolitica> division = divisionPoliticaService.getDivisionPolitica();
        if (division == null){
        	logger.debug("Nulo");
        }
        return division;	
    }
    
    
    /**
     * Retorna una lista de sectores. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de sectores
     */
    @RequestMapping(value = "sectores/{muni}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Sector> fetchSectoresJson(@PathVariable String muni) {
        logger.info("Obteniendo los sectores en JSON");
        List<Sector> sectores = sectorService.getSectores(muni);
        if (sectores == null){
        	logger.debug("Nulo");
        }
        return sectores;	
    }

    
    /**
     * Retorna una lista de comunidades. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de comunidades
     */
    @RequestMapping(value = "comunidades/{muni}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Comunidad> fetchComunidadesJson(@PathVariable String muni) {
        logger.info("Obteniendo los comunidades en JSON");
        List<Comunidad> comunidades = comunidadService.getComunidades(muni);
        if (comunidades == null){
        	logger.debug("Nulo");
        }
        
        return comunidades;	
    }
    
    /**
     * Retorna una lista de registros de catalogos generales. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de CatGen
     */
    @RequestMapping(value = "catgen", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CatGen> fetchCatGensJson() {
        logger.info("Obteniendo los catgens en JSON");
        List<CatGen> catgens = catGenService.getCatGens();
        if (catgens == null){
        	logger.debug("Nulo");
        }
        
        return catgens;	
    }
    
}
