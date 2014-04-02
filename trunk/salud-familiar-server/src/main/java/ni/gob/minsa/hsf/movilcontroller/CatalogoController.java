package ni.gob.minsa.hsf.movilcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ni.gob.minsa.hsf.domain.estructura.Catalogo;
import ni.gob.minsa.hsf.service.CatalogoService;

@Controller
@RequestMapping("/movil/*")
public class CatalogoController {
	
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogoController.class);
	
    @RequestMapping(value = "catalogos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Catalogo> fetchCatalogosJson() {
        logger.info("Obteniendo los catalogos en JSON");
        List<Catalogo> catalogos = catalogoService.getCatalogos();
        if (catalogos == null){
        	logger.debug("Nulo");
        }
        
        return catalogos;	
    }
	
	

}
