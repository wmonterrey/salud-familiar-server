package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;


import ni.gob.minsa.hsf.users.model.Authority;
import ni.gob.minsa.hsf.users.model.Rol;
import ni.gob.minsa.hsf.users.model.UserAccess;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto UserSistema
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
		Query query = session.createQuery("FROM UserSistema");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los usuarios para un usuario
	 * 
	 * @return una lista de <code>User</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getUsers(UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		SQLQuery query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema where " +
					"codigo_entidad = "+ usuario.getEntidad().getCodigo() +" or (codigo_unidad in (Select codigo from unidades where ENTIDAD_ADTVA = "+ usuario.getEntidad().getCodigo() +"))");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema where " +
					"codigo_unidad = "+ usuario.getUnidad().getCodigo() +" or (codigo_unidad in (Select codigo from unidades where UNIDAD_ADTVA = "+ usuario.getUnidad().getCodigo() +"));");
		}
		query.addEntity(UserSistema.class);
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
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username = '" + username + "'");
		UserSistema user = (UserSistema) query.uniqueResult();
		return user;
	}
	
	/**
	 * Regresa un User
	 * 
	 * @return un <code>User</code>
	 */

	public UserSistema getUser(String username, UserSistema usuario) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		SQLQuery query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema where " +
				"NOMBRE_USUARIO = '" + username + "'");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema where " +
					"NOMBRE_USUARIO = '" + username + "' and (codigo_entidad = "+ usuario.getEntidad().getCodigo() +" or " +
							"(codigo_unidad in (Select codigo from unidades where ENTIDAD_ADTVA = "+ usuario.getEntidad().getCodigo() +")))");
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createSQLQuery("select * from hsf_usuarios_sistema where " +
					"NOMBRE_USUARIO = '" + username + "' and (codigo_unidad = "+ usuario.getUnidad().getCodigo() +" or " +
							"(codigo_unidad in (Select codigo from unidades where UNIDAD_ADTVA = "+ usuario.getUnidad().getCodigo() +")));");
		}
		query.addEntity(UserSistema.class);
		// Retrieve all
		return  (UserSistema) query.uniqueResult();
	}
	
	
	/**
	 * Regresa los UserAccess
	 * 
	 * @return una lista de <code>UserAccess</code>
	 */

	@SuppressWarnings("unchecked")
	public List<UserAccess> getUserAccess(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserAccess u where " +
				"u.username = '" + username + "' order by u.loginDate DESC");
		return query.list();
	}
	
	/**
	 * Verifica un User
	 * 
	 * @return boolean
	 */

	public Boolean checkUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
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
	 * Verifica Credenciales
	 * 
	 * @return boolean
	 */

	public Boolean checkCredential(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username = '" + username + "'");
		UserSistema user = (UserSistema) query.uniqueResult();
		return user.getCredentialsNonExpired();
	}
	
	/**
	 * Bloquea un User
	 * 
	 * @return boolean
	 */

	public void blockUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username = '" + username + "'");
		UserSistema user = (UserSistema) query.uniqueResult();
		if (user!=null){
			user.setAccountNonLocked(false);
			session.update(user);
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
	 * Regresa todos los roles de usuarios
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRoles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol");
		// Retrieve all
		return  query.list();
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
	
	/**
	 * Borra todos los roles
	 * 
	 * 
	 */

	public Integer deleteRoles(String userName) {
		// Retrieve session from Hibernate
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		String hqlDelete = "delete Authority auth where auth.authId.username = :userName";
		int deletedEntities = s.createQuery( hqlDelete )
		        .setString( "userName", userName )
		        .executeUpdate();
		tx.commit();
		s.close();
		return deletedEntities;
	}
	
}
