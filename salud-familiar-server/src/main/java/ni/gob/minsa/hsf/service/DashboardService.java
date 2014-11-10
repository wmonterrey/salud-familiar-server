package ni.gob.minsa.hsf.service;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dashboardService")
@Transactional
public class DashboardService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public boolean visitasDia(UserSistema usuario){
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			
		}
		return false;
	}
	
}
