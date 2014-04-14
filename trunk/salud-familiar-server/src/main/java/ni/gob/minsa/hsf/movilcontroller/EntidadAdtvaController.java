package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;

@Controller
@RequestMapping("/movil/*")
public class EntidadAdtvaController {
	
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	
	private static final Logger logger = LoggerFactory.getLogger(EntidadAdtvaController.class);
	
    @RequestMapping(value = "entidadatvas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EntidadesAdtvas> fetchEntidadesAdtvasJson() {
        logger.info("Obteniendo las EntidadesAdtvas en JSON");
        List<EntidadesAdtvas> entidadesAdtvas = entidadAdtvaService.getEntidadesAdtvas();
        if (entidadesAdtvas == null){
        	logger.debug("Nulo");
        }        
        return entidadesAdtvas;	
    }
}
