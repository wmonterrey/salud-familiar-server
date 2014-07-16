package ni.gob.minsa.hsf.converters;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.service.CatalogoService;

import org.springframework.core.convert.converter.Converter;

public class StringToNivel implements Converter<String, Nivel> {

	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	
	@Override
	public Nivel convert(String nivel) {
		return catalogoService.getNivel(nivel);
	}

}
