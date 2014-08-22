package ni.gob.minsa.hsf.web.controller;

import java.util.Date;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/users/*")
public class UsuariosController {
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value="checkcredential", method=RequestMethod.GET)
	public @ResponseBody boolean getCredential(@RequestParam String userName) {
	    return this.usuarioService.checkCredential(userName);
	}
	
	@RequestMapping(value = "/chgpass", method = RequestMethod.GET)
    public String initChangePassForm() {
	    return "forceChgPass";
    }
	
	@RequestMapping( value="chgPass", method=RequestMethod.POST)
	public @ResponseBody String processChangePassForm( @RequestParam( value="password", required=true ) String password
	        )
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSistema user = usuarioService.getUser(authentication.getName());
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPass = encoder.encode(password);
		user.setModifiedBy(authentication.getName());
		user.setModified(new Date());
		user.setPassword(encodedPass);
		user.setLastCredentialChange(new Date());
		user.setCredentialsNonExpired(true);
		this.usuarioService.updateUser(user);
		return user.getUsername();
	}
	
}
