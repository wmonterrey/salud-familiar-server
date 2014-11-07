package ni.gob.minsa.hsf.service;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.estructura.EntidadesAdtvas;
import ni.gob.minsa.hsf.domain.estructura.Unidades;
import ni.gob.minsa.hsf.domain.poblacion.Comunidades;
import ni.gob.minsa.hsf.users.model.UserSistema;

import org.hibernate.SQLQuery;
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
		SQLQuery query = null;
		
		if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|CENTRAL")){
			return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createSQLQuery("Select * FROM ((entidades_adtvas ea INNER JOIN divisionpolitica dp ON ea.codigo = dp.DEPENDENCIA_SILAIS) " +
					"INNER JOIN sectores secs ON dp.CODIGO_NACIONAL = secs.municipio) INNER JOIN comunidades coms ON secs.codigo = coms.sector" +
					" WHERE (((ea.codigo)= " + usuario.getEntidad().getCodigo() + " and coms.codigo = '"+comunidad.getCodigo() +"'))");
			query.addEntity(EntidadesAdtvas.class);
			EntidadesAdtvas entidad = (EntidadesAdtvas) query.uniqueResult();
			if (entidad!=null) return true;
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createSQLQuery("SELECT * FROM (unidades INNER JOIN sectores ON unidades.CODIGO = sectores.UNIDAD) " +
					"INNER JOIN comunidades ON sectores.CODIGO = comunidades.SECTOR " +
					"WHERE (unidades.CODIGO=" + usuario.getUnidad().getCodigo() + " OR unidades.UNIDAD_ADTVA=" + usuario.getUnidad().getCodigo() + ") and comunidades.CODIGO = '"+comunidad.getCodigo() +"';");
			query.addEntity(Unidades.class);
			Unidades unidad = (Unidades) query.uniqueResult();
			if (unidad!=null) return true;
		}
		return false;
	}
}
