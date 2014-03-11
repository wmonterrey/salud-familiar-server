package ni.gob.minsa.hsf;

import java.util.List;
import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Comunidad;
import ni.gob.minsa.hsf.domain.Divisionpolitica;
import ni.gob.minsa.hsf.domain.Sector;
import ni.gob.minsa.hsf.domain.Tblcatgen;
import ni.gob.minsa.hsf.service.ComunidadService;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;
import ni.gob.minsa.hsf.service.SectorService;
import ni.gob.minsa.hsf.service.TblcatgenService;

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
	@Resource(name="tblcatenService")
	private TblcatgenService tblcatenService;
	
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
    @RequestMapping(value = "comunidades/{sector}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Comunidad> fetchComunidadesJson(@PathVariable String sector) {
        logger.info("Obteniendo los comunidades en JSON");
        List<Comunidad> comunidades = comunidadService.getComunidades(sector);
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
    public @ResponseBody List<Tblcatgen> fetchCatGensJson() {
        logger.info("Obteniendo los catgens en JSON");
        List<Tblcatgen> catgens = tblcatenService.getCatGens();
        if (catgens == null){
        	logger.debug("Nulo");
        }
        
        return catgens;	
    }
    
}
