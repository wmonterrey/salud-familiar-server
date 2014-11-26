package ni.gob.minsa.hsf.service;

import java.sql.Timestamp;
import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Object> visitasDia(UserSistema usuario, long date1, long date2){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setParameter("pasivo", '0');
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					" and v.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ usuario.getUnidad().getCodigo() +" or " +
									"lu.unidadAdtva = "+ usuario.getUnidad().getCodigo() +") " +
											"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> visitasArea(UserSistema usuario, long date1, long date2){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			query = session.createQuery("select v.familia.comunidad.sector.municipio.dependenciaSilais.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"group by v.familia.comunidad.sector.municipio.dependenciaSilais.nombre order by v.familia.comunidad.sector.municipio.dependenciaSilais.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createQuery("select v.familia.comunidad.sector.municipio.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by v.familia.comunidad.sector.municipio.nombre order by v.familia.comunidad.sector.municipio.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", usuario.getEntidad().getCodigo());
			query.setParameter("pasivo", '0');
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createQuery("select v.familia.comunidad.sector.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ usuario.getUnidad().getCodigo() +" or " +
									"lu.unidadAdtva = "+ usuario.getUnidad().getCodigo() +") " +
					"group by v.familia.comunidad.sector.nombre order by v.familia.comunidad.sector.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
        // Retrieve all
		return  query.list();
	}
	
}
