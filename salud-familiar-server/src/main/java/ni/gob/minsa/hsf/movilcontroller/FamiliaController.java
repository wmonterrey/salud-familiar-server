package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.service.FamiliaService;

@Controller
@RequestMapping("/movil/*")
public class FamiliaController {
	
	@Resource(name="familiaService")
	private FamiliaService familiaService;
	
	private static final Logger logger = LoggerFactory.getLogger(FamiliaController.class);
	
    @RequestMapping(value = "familias", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Familia> fetchFamiliasJson() {
        logger.info("Obteniendo las familias en JSON");
        List<Familia> familias = familiaService.getFamilias();
        if (familias == null){
        	logger.debug("Nulo");
        }
        else{
        	for (Familia familia : familias) {
        		familia.setComunidad(null);
        		familia.setPersonas(null);
        	}
        }
        return familias;	
    }
}
