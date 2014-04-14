package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.service.UnidadesService;

@Controller
@RequestMapping("/movil/*")
public class UnidadesController {
	
	@Resource(name="unidadesService")
	private UnidadesService unidadesService;
	
	private static final Logger logger = LoggerFactory.getLogger(UnidadesController.class);
	
    @RequestMapping(value = "unidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Unidades> fetchUnidadesJson() {
        logger.info("Obteniendo las Unidades en JSON");
        List<Unidades> unidades = unidadesService.getUnidades();
        if (unidades == null){
        	logger.debug("Nulo");
        }        
        return unidades;	
    }
}
