package ni.gob.minsa.hsf.service;

import java.util.List;
import javax.annotation.Resource;


import ni.gob.minsa.hsf.auth.config.Authority;
import ni.gob.minsa.hsf.auth.config.User;

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
	public List<User> getUsers() {
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

	public User getUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User u where " +
				"u.username = '" + username + "'");
		User user = (User) query.uniqueResult();
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
		User user = (User) query.uniqueResult();
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
	public void addUser(User user) {
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
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}
}
