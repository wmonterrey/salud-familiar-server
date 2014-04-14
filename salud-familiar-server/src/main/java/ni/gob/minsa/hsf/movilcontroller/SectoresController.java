package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.poblacion.Sectores;
import ni.gob.minsa.hsf.service.SectoresService;

@Controller
@RequestMapping("/movil/*")
public class SectoresController {
	
	@Resource(name="sectorService")
	private SectoresService sectorService;
	
	private static final Logger logger = LoggerFactory.getLogger(SectoresController.class);
	
    @RequestMapping(value = "sectores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Sectores> fetchSectoresJson() {
        logger.info("Obteniendo los Sectores en JSON");
        List<Sectores> sectores = sectorService.getSectores();
        if (sectores == null){
        	logger.debug("Nulo");
        }        
        return sectores;	
    }
}
