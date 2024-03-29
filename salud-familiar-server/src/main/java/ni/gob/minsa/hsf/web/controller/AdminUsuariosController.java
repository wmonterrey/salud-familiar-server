package ni.gob.minsa.hsf.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.catalogos.Nivel;
import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.service.CatalogoService;
import ni.gob.minsa.hsf.service.EntidadesAdtvasService;
import ni.gob.minsa.hsf.service.HsfService;
import ni.gob.minsa.hsf.service.UnidadesService;
import ni.gob.minsa.hsf.service.UsuarioService;
import ni.gob.minsa.hsf.users.model.Authority;
import ni.gob.minsa.hsf.users.model.AuthorityId;
import ni.gob.minsa.hsf.users.model.Rol;
import ni.gob.minsa.hsf.users.model.UserAccess;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avil�s
 */
@Controller
@RequestMapping("/admin/users/*")
public class AdminUsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="catalogoService")
	private CatalogoService catalogoService;
	@Resource(name="entidadAdtvaService")
	private EntidadesAdtvasService entidadAdtvaService;
	@Resource(name="unidadesService")
	private UnidadesService unidadesService;
	@Resource(name="hsfService")
	private HsfService hsfService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<UserSistema> usuarios = usuarioService.getUsers(usuario);
    	model.addAttribute("usuarios", usuarios);
    	return "admin/users/list";
	}	
	
    /**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{username}")
    public ModelAndView showUser(@PathVariable("username") String username) {
    	ModelAndView mav;
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        UserSistema user = this.usuarioService.getUser(username, usuario);
        if(user==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/users/user");
            List<UserAccess> accesosUsuario = usuarioService.getUserAccess(username);
            mav.addObject("user",user);
            mav.addObject("accesses",accesosUsuario);
        }
        return mav;
    }
    
    @RequestMapping(value = "newUser", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
    	List<Rol> roles = usuarioService.getRoles();
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<Nivel> niveles = catalogoService.getNiveles(usuario);
    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
    	model.addAttribute("roles", roles);
    	model.addAttribute("niveles", niveles);
    	model.addAttribute("entidades", entidades);
		return "admin/users/create";
	}
    
    
	@RequestMapping( value="newUser", method=RequestMethod.POST)
	public @ResponseBody String processCreationForm( @RequestParam(value="username", required=true ) String userName
			, @RequestParam( value="password", required=true ) String password
			, @RequestParam( value="confirm_password", required=true ) String passwordAgain
	        , @RequestParam( value="completeName", required=true ) String completeName
	        , @RequestParam( value="email", required=true ) String email
	        , @RequestParam( value="nivel", required=true ) String nivel
	        , @RequestParam( value="entidad", required=false ) Long entidad
	        , @RequestParam( value="municipio", required=false ) String municipio
	        , @RequestParam( value="unidad", required=false ) Long unidad
	        , @RequestParam( value="enabled", required=false, defaultValue="false" ) boolean enabled
	        , @RequestParam( value="authorities", required=true ) List<String> authorities
	        )
	{
		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema user = new UserSistema();
		user.setCreatedBy(usuario.getUsername());
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPass = encoder.encode(password);
		user.setUsername(userName);
		user.setPassword(encodedPass);
		user.setCompleteName(completeName);
		user.setEmail(email);
		user.setNivel(catalogoService.getNivel(nivel));
		if (nivel.equals("HSF_NIVELES|SILAIS")){
			user.setEntidad(entidadAdtvaService.getEntidadesAdtvas(entidad));
			user.setUnidad(null);
		}
		else if (nivel.equals("HSF_NIVELES|UNIDAD")){
			user.setEntidad(null);
			user.setUnidad(unidadesService.getUnidades(unidad));
		}
		else{
			user.setEntidad(null);
			user.setUnidad(null);
		}
		user.setEnabled(enabled);
		user.setCreated(new Date());
		user.setLastCredentialChange(new Date());
		if(hsfService.verificarPermisoUsuarios(user,usuario)){
			try{
				this.usuarioService.addUser(user);
				if (authorities!=null){
					for(String auth:authorities){
						AuthorityId authId = new AuthorityId();
						authId.setUsername(user.getUsername());
						authId.setAuthority(auth);
						Authority authority = new Authority();
						authority.setAuthId(authId);
						authority.setUser(user);
						this.usuarioService.addAuthority(authority);
					}
				}
			}
			catch(Exception e){
				return e.getMessage();
			}
		}
		else{
			return "No autorizado";
		}
		return user.getUsername();
	}
	
	/**
     * Custom handler for editing an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "edit/{username}", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema usertoEdit = this.usuarioService.getUser(username,usuario);
		if(usertoEdit!=null){
			model.addAttribute("user",usertoEdit);
			List<Rol> roles = usuarioService.getRoles();
	    	List<Nivel> niveles = catalogoService.getNiveles(usuario);
	    	List<EntidadesAdtvas> entidades = entidadAdtvaService.getEntidadesAdtvas(usuario);
	    	model.addAttribute("roles", roles);
	    	model.addAttribute("niveles", niveles);
	    	model.addAttribute("entidades", entidades);
			return "admin/users/edit";
		}
		else{
			return "403";
		}
	}
    
    
    @RequestMapping( value="editUser", method=RequestMethod.POST)
	public @ResponseBody String processUpdateUserForm( @RequestParam(value="username", required=true ) String userName
	        , @RequestParam( value="completeName", required=true ) String completeName
	        , @RequestParam( value="email", required=true ) String email
	        , @RequestParam( value="nivel", required=true ) String nivel
	        , @RequestParam( value="entidad", required=false ) Long entidad
	        , @RequestParam( value="municipio", required=false ) String municipio
	        , @RequestParam( value="unidad", required=false ) Long unidad
	        , @RequestParam( value="authorities", required=false ) List<String> authorities
	        )
	{
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = usuarioService.getUser(userName);
		user.setModifiedBy(usuario.getUsername());
		user.setCompleteName(completeName);
		user.setEmail(email);
		user.setNivel(catalogoService.getNivel(nivel));
		if (nivel.equals("HSF_NIVELES|SILAIS")){
			user.setEntidad(entidadAdtvaService.getEntidadesAdtvas(entidad));
			user.setUnidad(null);
		}
		else if (nivel.equals("HSF_NIVELES|UNIDAD")){
			user.setEntidad(null);
			user.setUnidad(unidadesService.getUnidades(unidad));
		}
		else{
			user.setEntidad(null);
			user.setUnidad(null);
		}
		user.setModified(new Date());
		if(hsfService.verificarPermisoUsuarios(user,usuario)){
			try{
				this.usuarioService.updateUser(user);
				usuarioService.deleteRoles(userName);
				if (authorities!=null){
					for(String auth:authorities){
						AuthorityId authId = new AuthorityId();
						authId.setUsername(user.getUsername());
						authId.setAuthority(auth);
						Authority authority = new Authority();
						authority.setAuthId(authId);
						authority.setUser(user);
						this.usuarioService.addAuthority(authority);
					}
				}
			}
			catch(Exception e){
				return e.getMessage();
			}
		}
		else{
			return "No autorizado";
		}
		return user.getUsername();
	}
    
    /**
     * Custom handler for changing an user password.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "chgpass/{username}", method = RequestMethod.GET)
	public String initChangePassForm(@PathVariable("username") String username, Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema usertoChange = this.usuarioService.getUser(username,usuario);
		if(usertoChange!=null){
			model.addAttribute("user",usertoChange);
			return "admin/users/chgpass";
		}
		else{
			return "403";
		}
	}
    
    
    @RequestMapping( value="chgPass", method=RequestMethod.POST)
	public @ResponseBody String processChangePassForm( @RequestParam(value="username", required=true ) String userName
			, @RequestParam( value="password", required=true ) String password
	        )
	{
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = usuarioService.getUser(userName);
    	if(hsfService.verificarPermisoUsuarios(user,usuario)){
			user.setModifiedBy(usuario.getUsername());
			user.setModified(new Date());
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encodedPass = encoder.encode(password);
			user.setPassword(encodedPass);
			user.setLastCredentialChange(new Date());
			this.usuarioService.updateUser(user);
			return user.getUsername();
    	}
		else{
			return "403";
		}
	}
	
	
	@RequestMapping(value="availability", method=RequestMethod.GET)
	public @ResponseBody boolean getAvailability(@RequestParam String userName) {
	    return this.usuarioService.checkUser(userName);
	}
	
	
	/**
     * Custom handler for enabling/disabling an user.
     *
     * @param username the ID of the user to enable
     * @return a String
     */
    @RequestMapping("/habdes/{accion}/{username}")
    public String enableUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean hab;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/users/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/users/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = this.usuarioService.getUser(username,usuario);
    	if(user!=null){
    		user.setModified(new Date());
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		user.setModifiedBy(authentication.getName());
    		user.setEnabled(hab);
    		this.usuarioService.updateUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for locking an user.
     *
     * @param username the ID of the user to lock
     * @return a String
     */
    @RequestMapping("/lock/{username}")
    public String lockUser(@PathVariable("username") String username, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = this.usuarioService.getUser(username,usuario);
    	if(user==null){
			return "403";
		}
		else{
	    	user.setModified(new Date());
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        user.setModifiedBy(authentication.getName());
	        user.setAccountNonLocked(false);
	    	this.usuarioService.updateUser(user);
	    	redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
	    	redirectAttributes.addFlashAttribute("nombreUsuario", username);
	    	return "redirect:/admin/users/{username}";
		}
    }
    
    /**
     * Custom handler for unlocking an user.
     *
     * @param username the ID of the user to lock
     * @return a String
     */
    @RequestMapping("/unlock/{username}")
    public String unlockUser(@PathVariable("username") String username, 
    		RedirectAttributes redirectAttributes) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = this.usuarioService.getUser(username,usuario);
    	if(user==null){
			return "403";
		}
		else{
	    	user.setModified(new Date());
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        user.setModifiedBy(authentication.getName());
	        user.setAccountNonLocked(true);
	    	this.usuarioService.updateUser(user);
	    	redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
	    	redirectAttributes.addFlashAttribute("nombreUsuario", username);
	    	return "redirect:/admin/users/{username}";
		}
    }
}
