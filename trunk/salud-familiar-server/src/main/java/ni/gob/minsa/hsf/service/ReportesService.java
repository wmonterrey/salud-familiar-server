package ni.gob.minsa.hsf.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.report.Consolidado;
import ni.gob.minsa.hsf.domain.report.FamEstComunidad;
import ni.gob.minsa.hsf.domain.report.FamEstDivPol;
import ni.gob.minsa.hsf.domain.report.FamEstEntidad;
import ni.gob.minsa.hsf.domain.report.FamEstSector;

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
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", codSilais);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					" and v.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
									"lu.unidadAdtva = "+ codUnidad +") " +
											"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.codigo =:sector " +
					"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("sector", codSector);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("select v.fechaVisita as fecha, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.codigo =:comunidad " +
					"group by v.fechaVisita order by v.fechaVisita");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("comunidad", codComunidad);
			query.setParameter("pasivo", '0');
		}
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> visitasArea(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad, long date1, long date2){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStampInicio = new Timestamp(date1);
		Timestamp timeStampFinal = new Timestamp(date2);
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
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
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("select v.familia.comunidad.sector.municipio.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by v.familia.comunidad.sector.municipio.nombre order by v.familia.comunidad.sector.municipio.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("silais", codSilais);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("select v.familia.comunidad.sector.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
									"lu.unidadAdtva = "+ codUnidad +") " +
					"group by v.familia.comunidad.sector.nombre order by v.familia.comunidad.sector.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("select v.familia.comunidad.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.sector.codigo =:sector " +
					"group by v.familia.comunidad.nombre order by v.familia.comunidad.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("sector", codSector);
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("select v.familia.comunidad.nombre as area, " +
					"sum(case v.tipoVisita when '1' then 1 else 0 end) as inicial, " +
					"sum(case v.tipoVisita when '0' then 1 else 0 end) as seguimiento, " +
					"count(v.idVisita) as total " +
					"from Visita v where v.pasive =:pasivo and v.fechaVisita between :fechaInicio and :fechaFinal " +
					"and v.familia.comunidad.codigo =:comunidad " +
					"group by v.familia.comunidad.nombre order by v.familia.comunidad.nombre");
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
			query.setParameter("comunidad", codComunidad);
			query.setParameter("pasivo", '0');
		}
        // Retrieve all
		return  query.list();
	}
		
	@SuppressWarnings("unchecked")
	public List<Consolidado> getConsolidado(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad){
		List<Consolidado> consFamilias = new ArrayList<Consolidado>();
		List<Consolidado> consViviendas = new ArrayList<Consolidado>();
		List<Consolidado> consFamiliasEst = new ArrayList<Consolidado>();
		List<Consolidado> consPersonas = new ArrayList<Consolidado>();
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
			query = session.createQuery("Select fam.comunidad.sector.municipio.dependenciaSilais.codigo as codigo, " +
					"fam.comunidad.sector.municipio.dependenciaSilais.nombre as nombre, Count(fam.idFamilia) as familias, " +
					"sum(case fam.dispensarizada when '1' then 1 else 0 end) as familiasDisp " +
					"From Familia fam where fam.pasive =:pasivo group by fam.comunidad.sector.municipio.dependenciaSilais.codigo, fam.comunidad.sector.municipio.dependenciaSilais.nombre " +
					"order by fam.comunidad.sector.municipio.dependenciaSilais.codigo");
			query.setParameter("pasivo", '0');
			List<Object[]> consFamiliasResult = query.list();
			for(Object[] objeto:consFamiliasResult){
				consFamilias.add(new Consolidado(objeto[0].toString(),objeto[1].toString(),(Long)objeto[2],null,null,null,(Long)objeto[3],null));
			}
			query = session.createSQLQuery("SELECT vivs.silais as codigo, Count(vivs.silais) AS viviendas " +
					"FROM (SELECT divisionpolitica.DEPENDENCIA_SILAIS AS silais " +
					"FROM (((hsf_familias INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO) " +
					"INNER JOIN divisionpolitica ON sectores.MUNICIPIO = divisionpolitica.CODIGO_NACIONAL) " +
					"WHERE hsf_familias.pasivo = '0' GROUP BY divisionpolitica.DEPENDENCIA_SILAIS, hsf_familias.COMUNIDAD, hsf_familias.NUM_VIVIENDA) vivs " +
					"GROUP BY vivs.silais ORDER BY vivs.silais");
			List<Object[]> consViviendasResult = query.list();
			for(Object[] objeto:consViviendasResult){
				consViviendas.add(new Consolidado(objeto[0].toString(),null,null,(BigDecimal)objeto[1],null,null,null,null));
			}
			query = session.createQuery("From FamEstEntidad famEst order by famEst.entidad.codigo");
			List<FamEstEntidad> consFamiliasEstResult = query.list();
			for(FamEstEntidad famEst:consFamiliasEstResult){
				consFamiliasEst.add(new Consolidado(String.valueOf(famEst.getEntidad().getCodigo()), null, null, null, null, famEst.getFamEstimadas(), null, null));
			}
			query = session.createQuery("Select per.familia.comunidad.sector.municipio.dependenciaSilais.codigo as codigo, " +
					"Count(per.idPersona) as personas, " +
					"sum(case when per.grupoDisp.codigo <> 'HSF_GD|NING' then 1 else 0 end) as peronasDisp " +
					"From Persona per where per.pasive =:pasivo group by per.familia.comunidad.sector.municipio.dependenciaSilais.codigo " +
					"order by per.familia.comunidad.sector.municipio.dependenciaSilais.codigo");
			query.setParameter("pasivo", '0');
			List<Object[]> consPersonasResult = query.list();
			for(Object[] objeto:consPersonasResult){
				consPersonas.add(new Consolidado(objeto[0].toString(),null,null,null,(Long)objeto[1],null,null,(Long)objeto[2]));
			}
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("Select fam.comunidad.sector.municipio.codigoNacional as codigo, " +
					"fam.comunidad.sector.municipio.nombre as nombre, Count(fam.idFamilia) as familias, " +
					"sum(case fam.dispensarizada when '1' then 1 else 0 end) as familiasDisp " +
					"From Familia fam where fam.pasive =:pasivo and fam.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by fam.comunidad.sector.municipio.codigoNacional, fam.comunidad.sector.municipio.nombre " +
					"order by fam.comunidad.sector.municipio.codigoNacional");
			query.setParameter("silais", codSilais);
			query.setParameter("pasivo", '0');
			List<Object[]> consFamiliasResult = query.list();
			for(Object[] objeto:consFamiliasResult){
				consFamilias.add(new Consolidado(objeto[0].toString(),objeto[1].toString(),(Long)objeto[2],null,null,null,(Long)objeto[3],null));
			}
			query = session.createSQLQuery("SELECT vivs.municipio as codigo, Count(vivs.municipio) AS viviendas " +
					"FROM (SELECT divisionpolitica.codigo_nacional AS municipio FROM (((hsf_familias INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO) " +
					"INNER JOIN divisionpolitica ON sectores.MUNICIPIO = divisionpolitica.CODIGO_NACIONAL) " +
					"WHERE hsf_familias.pasivo = '0' and divisionpolitica.dependencia_silais = " + codSilais + " GROUP BY divisionpolitica.codigo_nacional, hsf_familias.COMUNIDAD, hsf_familias.NUM_VIVIENDA) vivs " +
					"GROUP BY vivs.municipio ORDER BY vivs.municipio");
			List<Object[]> consViviendasResult = query.list();
			for(Object[] objeto:consViviendasResult){
				consViviendas.add(new Consolidado(objeto[0].toString(),null,null,(BigDecimal)objeto[1],null,null,null,null));
			}
			query = session.createQuery("From FamEstDivPol famEst where famEst.municipio.dependenciaSilais.codigo =:silais order by famEst.municipio.codigoNacional");
			query.setParameter("silais", codSilais);
			List<FamEstDivPol> consFamiliasEstResult = query.list();
			for(FamEstDivPol famEst:consFamiliasEstResult){
				consFamiliasEst.add(new Consolidado(String.valueOf(famEst.getMunicipio().getCodigoNacional()), null, null, null, null, famEst.getFamEstimadas(), null, null));
			}
			query = session.createQuery("Select per.familia.comunidad.sector.municipio.codigoNacional as codigo, " +
					"Count(per.idPersona) as personas, " +
					"sum(case when per.grupoDisp.codigo <> 'HSF_GD|NING' then 1 else 0 end) as peronasDisp " +
					"From Persona per where per.pasive =:pasivo and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by per.familia.comunidad.sector.municipio.codigoNacional " +
					"order by per.familia.comunidad.sector.municipio.codigoNacional");
			query.setParameter("silais", codSilais);
			query.setParameter("pasivo", '0');
			List<Object[]> consPersonasResult = query.list();
			for(Object[] objeto:consPersonasResult){
				consPersonas.add(new Consolidado(objeto[0].toString(),null,null,null,(Long)objeto[1],null,null,(Long)objeto[2]));
			}
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("Select fam.comunidad.sector.codigo as codigo, " +
					"fam.comunidad.sector.nombre as nombre, Count(fam.idFamilia) as familias, " +
					"sum(case fam.dispensarizada when '1' then 1 else 0 end) as familiasDisp " +
					"From Familia fam where fam.pasive =:pasivo and fam.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
									"lu.unidadAdtva = "+ codUnidad +") " +
					"group by fam.comunidad.sector.codigo, fam.comunidad.sector.nombre " +
					"order by fam.comunidad.sector.codigo");
			query.setParameter("pasivo", '0');
			List<Object[]> consFamiliasResult = query.list();
			for(Object[] objeto:consFamiliasResult){
				consFamilias.add(new Consolidado(objeto[0].toString(),objeto[1].toString(),(Long)objeto[2],null,null,null,(Long)objeto[3],null));
			}
			query = session.createSQLQuery("SELECT vivs.sector as codigo, Count(vivs.sector) AS viviendas " +
					"FROM (SELECT sectores.codigo AS sector FROM ((hsf_familias INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO) " +
					"INNER JOIN sectores ON comunidades.SECTOR = sectores.CODIGO) " +
					"WHERE hsf_familias.pasivo = '0' and sectores.unidad in (select codigo from Unidades where codigo = " + codUnidad + " or unidad_Adtva = " + codUnidad + ") " +
					"GROUP BY sectores.codigo, hsf_familias.COMUNIDAD, hsf_familias.NUM_VIVIENDA) vivs " +
					"GROUP BY vivs.sector ORDER BY vivs.sector");
			List<Object[]> consViviendasResult = query.list();
			for(Object[] objeto:consViviendasResult){
				consViviendas.add(new Consolidado(objeto[0].toString(),null,null,(BigDecimal)objeto[1],null,null,null,null));
			}
			query = session.createQuery("From FamEstSector famEst where famEst.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
									"lu.unidadAdtva = "+ codUnidad +") " + " order by famEst.sector.codigo");
			List<FamEstSector> consFamiliasEstResult = query.list();
			for(FamEstSector famEst:consFamiliasEstResult){
				consFamiliasEst.add(new Consolidado(String.valueOf(famEst.getSector().getCodigo()), null, null, null, null, famEst.getFamEstimadas(), null, null));
			}
			query = session.createQuery("Select per.familia.comunidad.sector.codigo as codigo, " +
					"Count(per.idPersona) as personas, " +
					"sum(case when per.grupoDisp.codigo <> 'HSF_GD|NING' then 1 else 0 end) as peronasDisp " +
					"From Persona per where per.pasive =:pasivo and per.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
									"lu.unidadAdtva = "+ codUnidad +") " +
					"group by per.familia.comunidad.sector.codigo " +
					"order by per.familia.comunidad.sector.codigo");
			query.setParameter("pasivo", '0');
			List<Object[]> consPersonasResult = query.list();
			for(Object[] objeto:consPersonasResult){
				consPersonas.add(new Consolidado(objeto[0].toString(),null,null,null,(Long)objeto[1],null,null,(Long)objeto[2]));
			}
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("Select fam.comunidad.codigo as codigo, " +
					"fam.comunidad.nombre as nombre, Count(fam.idFamilia) as familias, " +
					"sum(case fam.dispensarizada when '1' then 1 else 0 end) as familiasDisp " +
					"From Familia fam where fam.pasive =:pasivo and fam.comunidad.sector.codigo =:sector " +
					"group by fam.comunidad.codigo, fam.comunidad.nombre " +
					"order by fam.comunidad.codigo");
			query.setParameter("sector", codSector);
			query.setParameter("pasivo", '0');
			List<Object[]> consFamiliasResult = query.list();
			for(Object[] objeto:consFamiliasResult){
				consFamilias.add(new Consolidado(objeto[0].toString(),objeto[1].toString(),(Long)objeto[2],null,null,null,(Long)objeto[3],null));
			}
			query = session.createSQLQuery("SELECT vivs.comunidad as codigo, Count(vivs.comunidad) AS viviendas " +
					"FROM (SELECT comunidades.codigo AS comunidad FROM hsf_familias INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO " +
					"WHERE hsf_familias.pasivo = '0' and comunidades.sector = '" + codSector + "' GROUP BY comunidades.codigo, hsf_familias.COMUNIDAD, hsf_familias.NUM_VIVIENDA) vivs " +
					"GROUP BY vivs.comunidad ORDER BY vivs.comunidad");
			List<Object[]> consViviendasResult = query.list();
			for(Object[] objeto:consViviendasResult){
				consViviendas.add(new Consolidado(objeto[0].toString(),null,null,(BigDecimal)objeto[1],null,null,null,null));
			}
			query = session.createQuery("From FamEstComunidad famEst where famEst.comunidad.sector.codigo =:sector order by famEst.comunidad.sector.codigo");
			query.setParameter("sector", codSector);
			List<FamEstComunidad> consFamiliasEstResult = query.list();
			for(FamEstComunidad famEst:consFamiliasEstResult){
				consFamiliasEst.add(new Consolidado(String.valueOf(famEst.getComunidad().getCodigo()), null, null, null, null, famEst.getFamEstimadas(), null, null));
			}
			query = session.createQuery("Select per.familia.comunidad.codigo as codigo, " +
					"Count(per.idPersona) as personas, " +
					"sum(case when per.grupoDisp.codigo <> 'HSF_GD|NING' then 1 else 0 end) as peronasDisp " +
					"From Persona per where per.pasive =:pasivo and per.familia.comunidad.sector.codigo =:sector " +
					"group by per.familia.comunidad.codigo " +
					"order by per.familia.comunidad.codigo");
			query.setParameter("sector", codSector);
			query.setParameter("pasivo", '0');
			List<Object[]> consPersonasResult = query.list();
			for(Object[] objeto:consPersonasResult){
				consPersonas.add(new Consolidado(objeto[0].toString(),null,null,null,(Long)objeto[1],null,null,(Long)objeto[2]));
			}
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("Select fam.comunidad.codigo as codigo, " +
					"fam.comunidad.nombre as nombre, Count(fam.idFamilia) as familias, " +
					"sum(case fam.dispensarizada when '1' then 1 else 0 end) as familiasDisp " +
					"From Familia fam where fam.pasive =:pasivo and fam.comunidad.codigo =:comunidad " +
					"group by fam.comunidad.codigo, fam.comunidad.nombre " +
					"order by fam.comunidad.codigo");
			query.setParameter("comunidad", codComunidad);
			query.setParameter("pasivo", '0');
			List<Object[]> consFamiliasResult = query.list();
			for(Object[] objeto:consFamiliasResult){
				consFamilias.add(new Consolidado(objeto[0].toString(),objeto[1].toString(),(Long)objeto[2],null,null,null,(Long)objeto[3],null));
			}
			query = session.createSQLQuery("SELECT vivs.comunidad as codigo, Count(vivs.comunidad) AS viviendas " +
					"FROM (SELECT comunidades.codigo AS comunidad FROM hsf_familias INNER JOIN comunidades ON hsf_familias.COMUNIDAD = comunidades.CODIGO " +
					"WHERE hsf_familias.pasivo = '0' and comunidades.codigo = '" + codComunidad + "' GROUP BY comunidades.codigo, hsf_familias.COMUNIDAD, hsf_familias.NUM_VIVIENDA) vivs " +
					"GROUP BY vivs.comunidad ORDER BY vivs.comunidad");
			List<Object[]> consViviendasResult = query.list();
			for(Object[] objeto:consViviendasResult){
				consViviendas.add(new Consolidado(objeto[0].toString(),null,null,(BigDecimal)objeto[1],null,null,null,null));
			}
			query = session.createQuery("From FamEstComunidad famEst where famEst.comunidad.codigo =:comunidad order by famEst.comunidad.codigo");
			query.setParameter("comunidad", codComunidad);
			List<FamEstComunidad> consFamiliasEstResult = query.list();
			for(FamEstComunidad famEst:consFamiliasEstResult){
				consFamiliasEst.add(new Consolidado(String.valueOf(famEst.getComunidad().getCodigo()), null, null, null, null, famEst.getFamEstimadas(), null, null));
			}
			query = session.createQuery("Select per.familia.comunidad.codigo as codigo, " +
					"Count(per.idPersona) as personas, " +
					"sum(case when per.grupoDisp.codigo <> 'HSF_GD|NING' then 1 else 0 end) as peronasDisp " +
					"From Persona per where per.pasive =:pasivo and per.familia.comunidad.codigo =:comunidad " +
					"group by per.familia.comunidad.codigo " +
					"order by per.familia.comunidad.codigo");
			query.setParameter("comunidad", codComunidad);
			query.setParameter("pasivo", '0');
			List<Object[]> consPersonasResult = query.list();
			for(Object[] objeto:consPersonasResult){
				consPersonas.add(new Consolidado(objeto[0].toString(),null,null,null,(Long)objeto[1],null,null,(Long)objeto[2]));
			}
		}
		final Map<String, Consolidado> map =
		    new TreeMap<String, Consolidado>();
		for(final Consolidado noFamilias : consFamilias){
		    map.put(noFamilias.getCodigo(), noFamilias);
		}
		for(final Consolidado noViviendas : consViviendas){
		    final Consolidado retrieved =
		        map.get(noViviendas.getCodigo());
		    if(retrieved != null){
		        retrieved.setViviendas(noViviendas.getViviendas());
		    }
		}
		for(final Consolidado noFamEstimadas : consFamiliasEst){
		    final Consolidado retrieved =
		        map.get(noFamEstimadas.getCodigo());
		    if(retrieved != null){
		        retrieved.setFamiliasEst(noFamEstimadas.getFamiliasEst());
		    }
		}
		for(final Consolidado noPersonas : consPersonas){
		    final Consolidado retrieved =
		        map.get(noPersonas.getCodigo());
		    if(retrieved != null){
		        retrieved.setPersonas(noPersonas.getPersonas());
		        retrieved.setPersonasDisp(noPersonas.getPersonasDisp());
		    }
		}
		List<Consolidado> consolidado = new ArrayList<Consolidado>(map.values());
		return consolidado;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> embarazosArea(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
			query = session.createQuery("select per.familia.comunidad.sector.municipio.dependenciaSilais.nombre as area, " +
					"count(per.idPersona) as embarazadas, " +
					"sum(case per.cpnActualizado when '1' then 1 else 0 end) as cpnSi, " +
					"sum(case per.cpnActualizado when '0' then 1 else 0 end) as cpnNo " +
					"from Persona per where per.pasive =:pasivo and per.embarazada =:emb " +
					"group by per.familia.comunidad.sector.municipio.dependenciaSilais.nombre order by per.familia.comunidad.sector.municipio.dependenciaSilais.nombre");
			query.setParameter("emb", "1");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("select per.familia.comunidad.sector.municipio.nombre as area, " +
					"count(per.idPersona) as embarazadas, " +
					"sum(case per.cpnActualizado when '1' then 1 else 0 end) as cpnSi, " +
					"sum(case per.cpnActualizado when '0' then 1 else 0 end) as cpnNo " +
					"from Persona per where per.pasive =:pasivo and per.embarazada =:emb and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by per.familia.comunidad.sector.municipio.nombre order by per.familia.comunidad.sector.municipio.nombre");
			query.setParameter("emb", "1");
			query.setParameter("pasivo", '0');
			query.setParameter("silais", codSilais);
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("select per.familia.comunidad.sector.nombre as area, " +
					"count(per.idPersona) as embarazadas, " +
					"sum(case per.cpnActualizado when '1' then 1 else 0 end) as cpnSi, " +
					"sum(case per.cpnActualizado when '0' then 1 else 0 end) as cpnNo " +
					"from Persona per where per.pasive =:pasivo and per.embarazada =:emb and per.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +") " +
					"group by per.familia.comunidad.sector.nombre order by per.familia.comunidad.sector.nombre");
			query.setParameter("emb", "1");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("select per.familia.comunidad.nombre as area, " +
					"count(per.idPersona) as embarazadas, " +
					"sum(case per.cpnActualizado when '1' then 1 else 0 end) as cpnSi, " +
					"sum(case per.cpnActualizado when '0' then 1 else 0 end) as cpnNo " +
					"from Persona per where per.pasive =:pasivo and per.embarazada =:emb and per.familia.comunidad.sector.codigo =:sector " +
					"group by per.familia.comunidad.nombre order by per.familia.comunidad.nombre");
			query.setParameter("emb", "1");
			query.setParameter("pasivo", '0');
			query.setParameter("sector", codSector);
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("select per.familia.comunidad.nombre as area, " +
					"count(per.idPersona) as embarazadas, " +
					"sum(case per.cpnActualizado when '1' then 1 else 0 end) as cpnSi, " +
					"sum(case per.cpnActualizado when '0' then 1 else 0 end) as cpnNo " +
					"from Persona per where per.pasive =:pasivo and per.embarazada =:emb and per.familia.comunidad.codigo =:comunidad " +
					"group by per.familia.comunidad.nombre order by per.familia.comunidad.nombre");
			query.setParameter("emb", "1");
			query.setParameter("pasivo", '0');
			query.setParameter("comunidad", codComunidad);
		}
        // Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> cronicosArea(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
			query = session.createQuery("select enfermedad.persona.familia.comunidad.sector.municipio.dependenciaSilais.nombre as area, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E10' then 1 else 0 end) as E10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E11' then 1 else 0 end) as E11, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'J45' then 1 else 0 end) as J45, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'I10' then 1 else 0 end) as I10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'G40' then 1 else 0 end) as G40, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'F79' then 1 else 0 end) as F79, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'M139' then 1 else 0 end) as M139, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'A16' then 1 else 0 end) as A16 " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo " +
					"group by enfermedad.persona.familia.comunidad.sector.municipio.dependenciaSilais.nombre order by enfermedad.persona.familia.comunidad.sector.municipio.dependenciaSilais.nombre");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("select enfermedad.persona.familia.comunidad.sector.municipio.nombre as area, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E10' then 1 else 0 end) as E10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E11' then 1 else 0 end) as E11, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'J45' then 1 else 0 end) as J45, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'I10' then 1 else 0 end) as I10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'G40' then 1 else 0 end) as G40, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'F79' then 1 else 0 end) as F79, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'M139' then 1 else 0 end) as M139, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'A16' then 1 else 0 end) as A16 " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by enfermedad.persona.familia.comunidad.sector.municipio.nombre order by enfermedad.persona.familia.comunidad.sector.municipio.nombre");
			query.setParameter("pasivo", '0');
			query.setParameter("silais", codSilais);
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("select enfermedad.persona.familia.comunidad.sector.nombre as area, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E10' then 1 else 0 end) as E10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E11' then 1 else 0 end) as E11, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'J45' then 1 else 0 end) as J45, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'I10' then 1 else 0 end) as I10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'G40' then 1 else 0 end) as G40, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'F79' then 1 else 0 end) as F79, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'M139' then 1 else 0 end) as M139, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'A16' then 1 else 0 end) as A16 " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
					"lu.unidadAdtva = "+ codUnidad +") " +
					"group by enfermedad.persona.familia.comunidad.sector.nombre order by enfermedad.persona.familia.comunidad.sector.nombre");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("select enfermedad.persona.familia.comunidad.nombre as area, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E10' then 1 else 0 end) as E10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E11' then 1 else 0 end) as E11, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'J45' then 1 else 0 end) as J45, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'I10' then 1 else 0 end) as I10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'G40' then 1 else 0 end) as G40, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'F79' then 1 else 0 end) as F79, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'M139' then 1 else 0 end) as M139, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'A16' then 1 else 0 end) as A16 " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.codigo =:sector " +
					"group by enfermedad.persona.familia.comunidad.nombre order by enfermedad.persona.familia.comunidad.nombre");
			query.setParameter("pasivo", '0');
			query.setParameter("sector", codSector);
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("select enfermedad.persona.familia.comunidad.nombre as area, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E10' then 1 else 0 end) as E10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'E11' then 1 else 0 end) as E11, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'J45' then 1 else 0 end) as J45, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'I10' then 1 else 0 end) as I10, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'G40' then 1 else 0 end) as G40, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'F79' then 1 else 0 end) as F79, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'M139' then 1 else 0 end) as M139, " +
					"sum(case enfermedad.enfermedad.codigoCie10 when 'A16' then 1 else 0 end) as A16 " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.codigo =:comunidad " +
					"group by enfermedad.persona.familia.comunidad.nombre order by enfermedad.persona.familia.comunidad.nombre");
			query.setParameter("pasivo", '0');
			query.setParameter("comunidad", codComunidad);
		}
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getEnfermedades(String codArea, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		if (codArea.equals("HSF_AREAS|CENTRAL")){
			query = session.createQuery("select enfermedad.enfermedad.nombreCie10 as nomEnfermedad, enfermedad.enfermedad.codigoCie10 as codEnfermedad," +
					"count(enfermedad.idEnfermedad) as total " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo " +
					"group by enfermedad.enfermedad.nombreCie10, enfermedad.enfermedad.codigoCie10 order by enfermedad.enfermedad.nombreCie10");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SILAIS")){
			query = session.createQuery("select enfermedad.enfermedad.nombreCie10 as nomEnfermedad, enfermedad.enfermedad.codigoCie10 as codEnfermedad," +
					"count(enfermedad.idEnfermedad) as total " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais " +
					"group by enfermedad.enfermedad.nombreCie10, enfermedad.enfermedad.codigoCie10 order by enfermedad.enfermedad.nombreCie10");
			query.setParameter("pasivo", '0');
			query.setParameter("silais", codSilais);
		}
		else if (codArea.equals("HSF_AREAS|UNI")){
			query = session.createQuery("select enfermedad.enfermedad.nombreCie10 as nomEnfermedad, enfermedad.enfermedad.codigoCie10 as codEnfermedad," +
					"count(enfermedad.idEnfermedad) as total " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.unidad in " +
					"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
					"lu.unidadAdtva = "+ codUnidad +") " +
					"group by enfermedad.enfermedad.nombreCie10, enfermedad.enfermedad.codigoCie10 order by enfermedad.enfermedad.nombreCie10");
			query.setParameter("pasivo", '0');
		}
		else if (codArea.equals("HSF_AREAS|SECTOR")){
			query = session.createQuery("select enfermedad.enfermedad.nombreCie10 as nomEnfermedad, enfermedad.enfermedad.codigoCie10 as codEnfermedad," +
					"count(enfermedad.idEnfermedad) as total " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.sector.codigo =:sector " +
					"group by enfermedad.enfermedad.nombreCie10, enfermedad.enfermedad.codigoCie10 order by enfermedad.enfermedad.nombreCie10");
			query.setParameter("pasivo", '0');
			query.setParameter("sector", codSector);
		}
		else if (codArea.equals("HSF_AREAS|COMU")){
			query = session.createQuery("select enfermedad.enfermedad.nombreCie10 as nomEnfermedad, enfermedad.enfermedad.codigoCie10 as codEnfermedad," +
					"count(enfermedad.idEnfermedad) as total " +
					"from Enfermedades enfermedad where enfermedad.pasive =:pasivo and enfermedad.persona.familia.comunidad.codigo =:comunidad " +
					"group by enfermedad.enfermedad.nombreCie10, enfermedad.enfermedad.codigoCie10 order by enfermedad.enfermedad.nombreCie10");
			query.setParameter("pasivo", '0');
			query.setParameter("comunidad", codComunidad);
		}
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Familia> getFamilias(String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		query = session.createQuery("From Familia familia where familia.pasive =:pasivo " +
					"and familia.comunidad.codigo =:comunidad " +
					"order by familia.comunidad.nombre, familia.numVivienda, familia.numFamilia");
		query.setParameter("pasivo", '0');
		query.setParameter("comunidad", codComunidad);
        // Retrieve all
		return  query.list();
	}
	
}
