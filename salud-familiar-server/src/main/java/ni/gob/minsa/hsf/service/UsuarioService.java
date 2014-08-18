package ni.gob.minsa.hsf.service;

import java.util.List;
import javax.annotation.Resource;


import ni.gob.minsa.hsf.users.model.Authority;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto Vivienda
 * 
 * @author William Aviles
 * 
 **/

@Service("usuarioService")
@Transactional
public class UsuarioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los usuarios
	 * 
	 * @return una lista de <code>User</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM User");
		// Retrieve all
		return  query.list();
	}
	

	/**
	 * Regresa un User
	 * 
	 * @return un <code>User</code>
	 */

	public UserSistema getUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User u where " +
				"u.username = '" + username + "'");
		UserSistema user = (UserSistema) query.uniqueResult();
		return user;
	}
	
	/**
	 * Verifica un User
	 * 
	 * @return boolean
	 */

	public Boolean checkUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User u where " +
				"u.username = '" + username + "'");
		UserSistema user = (UserSistema) query.uniqueResult();
		if (user!=null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Agrega un user
	 * 
	 * 
	 */
	public void addUser(UserSistema user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	/**
	 * Agrega un rol
	 * 
	 * 
	 */
	public void addAuthority(Authority auth) {
		Session session = sessionFactory.getCurrentSession();
		session.save(auth);
	}
	
	/**
	 * Actualiza un user
	 * 
	 * 
	 */
	public void updateUser(UserSistema user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}
}
