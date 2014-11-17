package ni.gob.minsa.hsf.service;

import java.util.List;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hsfService")
@Transactional
public class HsfService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public boolean verificarPermisoDatos(Comunidades comunidad, UserSistema usuario){
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("From Comunidades coms where coms.codigo =:comunidad and coms.sector.municipio.dependenciaSilais.codigo =:silais");
			query.setParameter("comunidad", comunidad.getCodigo());
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			Comunidades comRes = (Comunidades) query.uniqueResult();
			if (comRes!=null) return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("From Comunidades coms where coms.codigo =:comunidad and coms.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ usuario.getUnidad().getCodigo() +" or " +
									"lu.unidadAdtva = "+ usuario.getUnidad().getCodigo() +")");
			query.setParameter("comunidad", comunidad.getCodigo());
			Comunidades comRes = (Comunidades) query.uniqueResult();
			if (comRes!=null) return true;
		}
		return false;
	}
	
	public boolean verificarPermisoUsuarios(UserSistema usuarioModif,UserSistema usuario){
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			if(usuarioModif.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
				if (usuarioModif.getEntidad().getCodigo()==usuario.getEntidad().getCodigo()) return true;
			}
			else if(usuarioModif.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
				query = session.createQuery("From UserSistema u where " +
					"u.username = '" + usuario.getUsername() + "' and u.entidad.codigo = "+ usuarioModif.getUnidad().getEntidadAdtva());
				UserSistema resultado = (UserSistema) query.uniqueResult();
				if (resultado!=null) return true;
			}
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			if(usuarioModif.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
				if (usuarioModif.getUnidad().getCodigo()==usuario.getUnidad().getCodigo()) {
					return true;
				}
				else{
					query = session.createQuery("From Unidades u where " +
							"u.codigo in (select lu.codigo from Unidades lu where lu.codigo = "+ usuario.getUnidad().getCodigo() +" or " +
									"lu.unidadAdtva = "+ usuario.getUnidad().getCodigo() +")");
					@SuppressWarnings("unchecked")
					List<Unidades> resultado = query.list();
					for(Unidades unidad:resultado){
						if (unidad.getCodigo()==usuarioModif.getUnidad().getCodigo()) return true;
					}
				}
			}
		}
		return false;
	}
	
}
