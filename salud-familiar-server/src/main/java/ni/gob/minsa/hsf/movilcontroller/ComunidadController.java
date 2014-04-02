package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.service.ComunidadesService;

@Controller
@RequestMapping("/movil/*")
public class ComunidadController {
	
	@Resource(name="comunidadService")
	private ComunidadesService comunidadService;
	
	private static final Logger logger = LoggerFactory.getLogger(ComunidadController.class);
	
    @RequestMapping(value = "comunidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Comunidades> fetchComunidadesJson() {
        logger.info("Obteniendo las comunidades en JSON");
        List<Comunidades> comunidades = comunidadService.getComunidades();
        if (comunidades == null){
        	logger.debug("Nulo");
        }
        else{
        	for (Comunidades comunidad : comunidades) {
        		comunidad.setFamilias(null);
        	}
        }
        
        return comunidades;	
    }
}
