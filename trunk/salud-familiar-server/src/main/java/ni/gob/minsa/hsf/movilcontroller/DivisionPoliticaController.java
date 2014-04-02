package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.poblacion.Divisionpolitica;
import ni.gob.minsa.hsf.service.DivisionPoliticaService;

@Controller
@RequestMapping("/movil/*")
public class DivisionPoliticaController {
	
	@Resource(name="divPoliticaService")
	private DivisionPoliticaService divPoliticaService;
	
	private static final Logger logger = LoggerFactory.getLogger(DivisionPoliticaController.class);

    @RequestMapping(value = "divpolitica", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Divisionpolitica> fetchDivisionPoliticaJson() {
        logger.info("Obteniendo las Divisionpolitica en JSON");
        List<Divisionpolitica> divpolitica = divPoliticaService.getDivisionpoliticas();
        if (divpolitica == null){
        	logger.debug("Nulo");
        }
        return divpolitica;	
    }
	
	

}
