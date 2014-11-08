package ni.gob.minsa.hsf.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.UserAccess;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	/**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("profile")
    public ModelAndView showUser() {
        ModelAndView mav = new ModelAndView("users/user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSistema user = this.usuarioService.getUser(authentication.getName());
        List<UserAccess> accesosUsuario = usuarioService.getUserAccess(authentication.getName());
        mav.addObject("user",user);
        mav.addObject("accesses",accesosUsuario);
        return mav;
    }
	
	
	@RequestMapping(value="checkcredential", method=RequestMethod.GET)
	public @ResponseBody boolean getCredential(@RequestParam String userName) {
	    return this.usuarioService.checkCredential(userName);
	}
	
	@RequestMapping(value = "chgpass", method = RequestMethod.GET)
    public String initChangePassForm() {
	    return "users/chgpass";
    }
	
	@RequestMapping(value = "forcechgpass", method = RequestMethod.GET)
    public String initForceChangePassForm() {
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
	
    @RequestMapping(value = "edit", method = RequestMethod.GET)
	public String initUpdateUserForm(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserSistema usertoEdit = this.usuarioService.getUser(authentication.getName());
		model.addAttribute("user",usertoEdit);
		return "users/edit";
	}
    
    
    @RequestMapping( value="editUser", method=RequestMethod.POST)
	public @ResponseBody String processUpdateUserForm( @RequestParam(value="username", required=true ) String userName
	        , @RequestParam( value="completeName", required=true ) String completeName
	        , @RequestParam( value="email", required=true ) String email
	        )
	{
		UserSistema user = usuarioService.getUser(userName);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		user.setModifiedBy(authentication.getName());
		user.setModified(new Date());
		user.setCompleteName(completeName);
		user.setEmail(email);
		this.usuarioService.updateUser(user);
		return user.getUsername();
	}
	
}
