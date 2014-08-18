package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import ni.gob.minsa.hsf.auth.config.Authority;
import ni.gob.minsa.hsf.auth.config.AuthorityId;
import ni.gob.minsa.hsf.auth.config.User;
import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.UnidadesService;
import ni.gob.minsa.hsf.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/users/*")
public class UsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	@Resource(name="unidadesService")
	private UnidadesService unidadesService;
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	
	
	
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	List<User> usuarios = usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	return "users/list";
	}	
	
    /**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/admin/{username}")
    public ModelAndView showUser(@PathVariable("username") String username) {
        ModelAndView mav = new ModelAndView("users/user");
        User user = this.usuarioService.getUser(username);
        mav.addObject("user",user);
        return mav;
    }
    
    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		User user = new User();
		List<Nivel> niveles = catalogoService.getNiveles();
		model.addAttribute("user",user);
		model.addAttribute("niveles",niveles);
		return "users/create";
	}

	@RequestMapping(value = "/admin/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid User user, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "users/create";
		}
		else if (this.usuarioService.checkUser(user.getUsername())){
			result.rejectValue("username", "error.user", "Ya existe este nombre de usuario en la base de datos");
			return "users/create";
		} else {
			user.setCreated(new Date());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			user.setUsuario(authentication.getName());
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encodedPass = encoder.encode(user.getPassword());
			user.setPassword(encodedPass);
			this.usuarioService.addUser(user);
			AuthorityId authId = new AuthorityId();
			authId.setUsername(user.getUsername());
			authId.setAuthority("ROLE_WEB");
			Authority auth = new Authority();
			auth.setAuthId(authId);
			auth.setUser(user);
			this.usuarioService.addAuthority(auth);
			status.setComplete();
			return "redirect:/users/admin/"+ user.getUsername();
		}
	}
    
    @RequestMapping(value = "/admin/{username}/edit", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
		User usertoEdit = this.usuarioService.getUser(username);
		model.addAttribute(usertoEdit);
		return "users/UpdateUserForm";		
	}

	@RequestMapping(value = "/admin/{username}/edit", method = RequestMethod.PUT)
	public String processUpdateUserForm(@Valid User user, BindingResult result, SessionStatus status, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "users/UpdateUserForm";
		} else {
			user.setCreated(new Date());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			user.setUsuario(authentication.getName());
			AuthorityId authId = new AuthorityId();
			authId.setUsername(user.getUsername());
			authId.setAuthority("ROLE_WEB");
			Authority auth = new Authority();
			auth.setAuthId(authId);
			auth.setUser(user);
			this.usuarioService.addAuthority(auth);
			this.usuarioService.updateUser(user);
			status.setComplete();
			redirectAttributes.addFlashAttribute("SUCCESS", "Cambios guardados correctamente!");
			return "redirect:/usuarios/admin/{username}";
		}
	}
}
