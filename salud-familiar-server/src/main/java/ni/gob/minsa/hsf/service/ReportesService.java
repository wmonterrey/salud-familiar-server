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

@Service("reportesService")
@Transactional
public class ReportesService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Object> visitasDia(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad, long date1, long date2){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.fechaVisita between :fechaInicio and :fechaFinal group by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createSQLQuery("SELECT hsf_Visitas.FECHA_VISITA as fecha, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, count(id_visita) as total " +
					"FROM ((((hsf_Visitas INNER JOIN hsf_familias ON hsf_Visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA) " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO) " +
					"INNER JOIN divisionpolitica ON sectores.MUNICIPIO = divisionpolitica.CODIGO_NACIONAL) " +
					"INNER JOIN entidades_adtvas ON divisionpolitica.DEPENDENCIA_SILAIS = entidades_adtvas.CODIGO " +
					"WHERE (((hsf_Visitas.FECHA_VISITA) between :fechaInicio and :fechaFinal) and entidades_adtvas.CODIGO=:silais) " +
					"GROUP BY hsf_Visitas.FECHA_VISITA;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", codSilais);
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createSQLQuery("SELECT hsf_Visitas.FECHA_VISITA as fecha, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, " +
					"Count(hsf_Visitas.id_visita) AS total " +
					"FROM ((hsf_Visitas INNER JOIN hsf_familias ON hsf_Visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA) " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO " +
					"WHERE (((hsf_Visitas.FECHA_VISITA)  between :fechaInicio and :fechaFinal) " +
					"and ((sectores.unidad) In (select codigo from unidades where codigo =:unidad or UNIDAD_ADTVA =:unidad))) " +
					"GROUP BY hsf_Visitas.FECHA_VISITA;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("unidad", codUnidad);
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createSQLQuery("SELECT hsf_Visitas.FECHA_VISITA as fecha, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, count(id_visita) as total " +
					"FROM (((hsf_Visitas INNER JOIN hsf_familias ON hsf_Visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA) " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO) " +
					"WHERE (((hsf_Visitas.FECHA_VISITA) between :fechaInicio and :fechaFinal) and sectores.CODIGO=:sector) " +
					"GROUP BY hsf_Visitas.FECHA_VISITA;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("sector", codSector);
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
			query = session.createSQLQuery("SELECT entidades_adtvas.NOMBRE, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, count(id_visita) as total " +
					"FROM hsf_visitas " +
					"INNER JOIN hsf_familias ON hsf_visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO " +
					"INNER JOIN divisionpolitica ON sectores.MUNICIPIO = divisionpolitica.CODIGO_NACIONAL " +
					"INNER JOIN entidades_adtvas ON divisionpolitica.DEPENDENCIA_SILAIS = entidades_adtvas.ENTIDAD_ADTVA_ID " +
					"WHERE hsf_visitas.FECHA_VISITA between :fechaInicio and :fechaFinal " +
					"GROUP BY entidades_adtvas.NOMBRE;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|SILAIS")){
			query = session.createSQLQuery("SELECT divisionpolitica.NOMBRE, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, count(id_visita) as total " +
					"FROM hsf_visitas " +
					"INNER JOIN hsf_familias ON hsf_visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO " +
					"INNER JOIN divisionpolitica ON sectores.MUNICIPIO = divisionpolitica.CODIGO_NACIONAL " +
					"INNER JOIN entidades_adtvas ON divisionpolitica.DEPENDENCIA_SILAIS = entidades_adtvas.ENTIDAD_ADTVA_ID " +
					"WHERE hsf_visitas.FECHA_VISITA between :fechaInicio and :fechaFinal and entidades_adtvas.codigo =:silais " +
					"GROUP BY divisionpolitica.NOMBRE;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", usuario.getEntidad().getCodigo());
		}
		else if (usuario.getNivel().getCodigo().equals("HSF_NIVELES|UNIDAD")){
			query = session.createSQLQuery("SELECT sectores.NOMBRE, " +
					"sum(case hsf_visitas.TIPO_VISITA when '1' then 1 else 0 end) as inicial, " +
					"sum(case hsf_visitas.TIPO_VISITA when '0' then 1 else 0 end) as seguimiento, count(id_visita) as total " +
					"FROM hsf_visitas " +
					"INNER JOIN hsf_familias ON hsf_visitas.ID_FAMILIA = hsf_familias.ID_FAMILIA " +
					"INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO " +
					"WHERE hsf_visitas.FECHA_VISITA between :fechaInicio and :fechaFinal and sectores.unidad In (select codigo from unidades where codigo =:unidad or UNIDAD_ADTVA =:unidad) " +
					"GROUP BY sectores.NOMBRE;");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("unidad", usuario.getUnidad().getCodigo());
		}
        // Retrieve all
		return  query.list();
	}
	
}
