package ni.gob.minsa.hsf.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Enfermedades;
import ni.gob.minsa.hsf.domain.Familia;
import ni.gob.minsa.hsf.domain.Persona;
import ni.gob.minsa.hsf.domain.Visita;
import ni.gob.minsa.hsf.domain.report.Consolidado;
import ni.gob.minsa.hsf.domain.report.FamEstComunidad;
import ni.gob.minsa.hsf.domain.report.FamEstDivPol;
import ni.gob.minsa.hsf.domain.report.FamEstEntidad;
import ni.gob.minsa.hsf.domain.report.FamEstSector;
import ni.gob.minsa.hsf.domain.report.ReporteCaract;

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
					"FROM (SELECT general.divisionpolitica.DEPENDENCIA_SILAIS AS silais " +
					"FROM (((hsf.hsf_familias INNER JOIN general.comunidades ON hsf.hsf_familias.COMUNIDAD = general.comunidades.CODIGO) " +
					"INNER JOIN general.sectores ON general.comunidades.SECTOR = general.sectores.CODIGO) " +
					"INNER JOIN general.divisionpolitica ON general.sectores.MUNICIPIO = general.divisionpolitica.CODIGO_NACIONAL) " +
					"WHERE hsf.hsf_familias.pasivo = '0' GROUP BY general.divisionpolitica.DEPENDENCIA_SILAIS, hsf.hsf_familias.COMUNIDAD, hsf.hsf_familias.NUM_VIVIENDA) vivs " +
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
					"FROM (SELECT general.divisionpolitica.codigo_nacional AS municipio FROM (((hsf.hsf_familias INNER JOIN general.comunidades ON hsf.hsf_familias.COMUNIDAD = general.comunidades.CODIGO) " +
					"INNER JOIN general.sectores ON general.comunidades.SECTOR = general.sectores.CODIGO) " +
					"INNER JOIN general.divisionpolitica ON general.sectores.MUNICIPIO = general.divisionpolitica.CODIGO_NACIONAL) " +
					"WHERE hsf.hsf_familias.pasivo = '0' and general.divisionpolitica.dependencia_silais = " + codSilais + " GROUP BY general.divisionpolitica.codigo_nacional, hsf.hsf_familias.COMUNIDAD, hsf.hsf_familias.NUM_VIVIENDA) vivs " +
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
					"FROM (SELECT general.sectores.codigo AS sector FROM ((hsf.hsf_familias INNER JOIN general.comunidades ON hsf.hsf_familias.COMUNIDAD = general.comunidades.CODIGO) " +
					"INNER JOIN general.sectores ON general.comunidades.SECTOR = general.sectores.CODIGO) " +
					"WHERE hsf.hsf_familias.pasivo = '0' and general.sectores.unidad in (select codigo from general.Unidades where codigo = " + codUnidad + " or unidad_Adtva = " + codUnidad + ") " +
					"GROUP BY general.sectores.codigo, hsf.hsf_familias.COMUNIDAD, hsf.hsf_familias.NUM_VIVIENDA) vivs " +
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
					"FROM (SELECT general.comunidades.codigo AS comunidad FROM hsf.hsf_familias INNER JOIN general.comunidades ON hsf.hsf_familias.COMUNIDAD = general.comunidades.CODIGO " +
					"WHERE hsf.hsf_familias.pasivo = '0' and general.comunidades.sector = '" + codSector + "' GROUP BY general.comunidades.codigo, hsf.hsf_familias.COMUNIDAD, hsf.hsf_familias.NUM_VIVIENDA) vivs " +
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
					"FROM (SELECT general.comunidades.codigo AS comunidad FROM hsf.hsf_familias INNER JOIN general.comunidades ON hsf.hsf_familias.COMUNIDAD = general.comunidades.CODIGO " +
					"WHERE hsf.hsf_familias.pasivo = '0' and general.comunidades.codigo = '" + codComunidad + "' GROUP BY general.comunidades.codigo, hsf.hsf_familias.COMUNIDAD, hsf.hsf_familias.NUM_VIVIENDA) vivs " +
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
	
	@SuppressWarnings("unchecked")
	public List<Visita> getVisitas(String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		query = session.createQuery("From Visita visita where visita.pasive =:pasivo " +
					"and visita.familia.comunidad.codigo =:comunidad " +
					"order by visita.fechaVisita");
		query.setParameter("pasivo", '0');
		query.setParameter("comunidad", codComunidad);
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas(String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		query = session.createQuery("From Persona persona where persona.pasive =:pasivo " +
					"and persona.familia.comunidad.codigo =:comunidad " +
					"order by persona.familia.comunidad.nombre, persona.familia.numVivienda, persona.familia.numFamilia, persona.numPersona");
		query.setParameter("pasivo", '0');
		query.setParameter("comunidad", codComunidad);
        // Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Enfermedades> getEnfermedades(String codComunidad, String codEnfermedad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		query = session.createQuery("From Enfermedades enf where enf.pasive =:pasivo " +
					"and enf.persona.familia.comunidad.codigo =:comunidad and enf.enfermedad.codigoCie10 =:enfermedad " +
					"order by enf.persona.familia.comunidad.nombre, enf.persona.familia.numVivienda, enf.persona.familia.numFamilia, enf.persona.numPersona, enf.enfermedad.nombreCie10");
		query.setParameter("pasivo", '0');
		query.setParameter("comunidad", codComunidad);
		query.setParameter("enfermedad", codEnfermedad);
        // Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Persona> getEmbarazos(String codComunidad){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		query = session.createQuery("From Persona persona where persona.pasive =:pasivo " + 
					"and persona.familia.comunidad.codigo =:comunidad and persona.embarazada =:embarazo " +
					"order by persona.familia.comunidad.nombre, persona.familia.numVivienda, persona.familia.numFamilia, persona.numPersona");
		query.setParameter("pasivo", '0');
		query.setParameter("comunidad", codComunidad);
		query.setParameter("embarazo", "1");
        // Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ReporteCaract> caratPersonas(String codArea, String codVariable, Long codSilais, String codMunicipio, Long codUnidad, String codSector, String codComunidad){
		List<ReporteCaract> reporte = new ArrayList<ReporteCaract>();
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = null;
		//GET DATA FROM DATABASE
		if(codVariable.equals("ocupacion")){
			String sqlQuery = "select per."+codVariable+".nombre, count(per."+codVariable+".nombre) " +
						"from Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery +" group by per."+codVariable+".nombre");
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais group by per."+codVariable+".nombre");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +") group by per."+codVariable+".nombre");
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.sector.codigo =:sector group by per."+codVariable+".nombre");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.codigo =:comunidad group by per."+codVariable+".nombre");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		else{
		else if(codVariable.equals("discapacidades")){
			String sqlQuery = "Select Count(per.idPersona) as total, " +
					"sum(case per.discapacidades when 'HSF_DISCAP|NING' then 1 else 0 end) as Ninguna "+ 
					", sum(case when per.discapacidades like '%HSF_DISCAP|VISUAL%' then 1 else 0 end) as Visual"+
					", sum(case when per.discapacidades like '%HSF_DISCAP|AUDIO%' then 1 else 0 end) as Auditiva"+
					", sum(case when per.discapacidades like '%HSF_DISCAP|MOTORA%' then 1 else 0 end) as Motora"+
					", sum(case when per.discapacidades like '%HSF_DISCAP|MENTAL%' then 1 else 0 end) as Mental"+
					", sum(case when per.discapacidades like '%HSF_DISCAP|CONG%' then 1 else 0 end) as Cong�nita"+
					" From Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery);
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +")");				
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.codigo =:sector");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.codigo =:comunidad");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		else if(codVariable.equals("factRiesgoMod")){
			String sqlQuery = "Select Count(per.idPersona) as total, " +
					"sum(case per.factRiesgoMod when 'HSF_FRMOD|NING' then 1 else 0 end) as Ninguno "+ 
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|HTOX%' then 1 else 0 end) as HabitosTox"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|SED%' then 1 else 0 end) as Sedentarismo"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|OBE%' then 1 else 0 end) as Obesidad"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|DES%' then 1 else 0 end) as Desnutrici�n"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|ACC%' then 1 else 0 end) as Accidentes"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|ITS%' then 1 else 0 end) as ITS"+
					", sum(case when per.factRiesgoMod like '%HSF_FRMOD|PREC%' then 1 else 0 end) as Riesgo_Preconcepcional"+
					" From Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery);
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +")");				
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.codigo =:sector");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.codigo =:comunidad");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		else if(codVariable.equals("factRiesgoNoMod")){
			String sqlQuery = "Select Count(per.idPersona) as total, " +
					"sum(case per.factRiesgoNoMod when 'HSF_FRNOMOD|NING' then 1 else 0 end) as Ninguno "+ 
					", sum(case when per.factRiesgoNoMod like '%HSF_FRNOMOD|EDAD%' then 1 else 0 end) as Edad"+
					", sum(case when per.factRiesgoNoMod like '%HSF_FRNOMOD|SEXO%' then 1 else 0 end) as Sexo"+
					", sum(case when per.factRiesgoNoMod like '%HSF_FRNOMOD|RAZA%' then 1 else 0 end) as Raza"+
					", sum(case when per.factRiesgoNoMod like '%HSF_FRNOMOD|GSANG%' then 1 else 0 end) as Grupo_Sangu�neo"+
					" From Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery);
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +")");				
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.codigo =:sector");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.codigo =:comunidad");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		else if(codVariable.equals("factRiesgoSocial")){
			String sqlQuery = "Select Count(per.idPersona) as total, " +
					"sum(case per.factRiesgoSocial when 'HSF_FRSOC|NING' then 1 else 0 end) as Ninguno "+ 
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|AAUT%' then 1 else 0 end) as Abuso_Autoridad"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|TDES%' then 1 else 0 end) as Total_Desamparo"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|NTRA%' then 1 else 0 end) as Ni�os_Trabajadores"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|EXSC%' then 1 else 0 end) as Explotaci�n_Sexual_Comercial"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|MFIS%' then 1 else 0 end) as Maltrato_F�sico_Psicol�gico"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|DISC%' then 1 else 0 end) as Discapacidad"+
					", sum(case when per.factRiesgoSocial like '%HSF_FRSOC|TRATA%' then 1 else 0 end) as Trata_Personas"+
					" From Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery);
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +")");				
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.codigo =:sector");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.codigo =:comunidad");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		else{
			String sqlQuery = "select per."+codVariable+".valor, count(per."+codVariable+".valor) " +
						"from Persona per where per.pasive =:pasivo";
			if (codArea.equals("HSF_AREAS|CENTRAL")){
				query = session.createQuery(sqlQuery +" group by per."+codVariable+".valor");
			}
			else if (codArea.equals("HSF_AREAS|SILAIS")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.sector.municipio.dependenciaSilais.codigo =:silais group by per."+codVariable+".valor");
				query.setParameter("silais", codSilais);
			}
			else if (codArea.equals("HSF_AREAS|UNI")){
				query = session.createQuery(sqlQuery + " and per.familia.comunidad.sector.unidad in " +
						"(select lu.codigo from Unidades lu where lu.codigo = "+ codUnidad +" or " +
						"lu.unidadAdtva = "+ codUnidad +") group by per."+codVariable+".valor");
			}
			else if (codArea.equals("HSF_AREAS|SECTOR")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.sector.codigo =:sector group by per."+codVariable+".valor");
				query.setParameter("sector", codSector);
			}
			else if (codArea.equals("HSF_AREAS|COMU")){
				query = session.createQuery(sqlQuery +" and per.familia.comunidad.codigo =:comunidad group by per."+codVariable+".valor");
				query.setParameter("comunidad", codComunidad);
			}
			query.setParameter("pasivo", '0');
		}
		
		
		//PROCESS DATA IN REPORTECARACT FORMAT
		if(codVariable.equals("discapacidades")){
			String[] campos = query.getReturnAliases();
			Object[] reporteResult = (Object[]) query.uniqueResult();
			for (int i=1;i<7;i++){
				if((Long) reporteResult[i]!=null){
					ReporteCaract rep = new ReporteCaract();
					rep.setValor(campos[i]);
					rep.setCuenta((Long) reporteResult[i]);
					float num = (float) rep.getCuenta();
					float tot = (float) (Long) reporteResult[0];
					float porc = num / tot * 100;
					rep.setPorcentaje(porc);
					reporte.add(rep);
				}
			}
		}
		else if(codVariable.equals("factRiesgoMod")){
			String[] campos = query.getReturnAliases();
			Object[] reporteResult = (Object[]) query.uniqueResult();
			for (int i=1;i<9;i++){
				if((Long) reporteResult[i]!=null){
					ReporteCaract rep = new ReporteCaract();
					rep.setValor(campos[i]);
					rep.setCuenta((Long) reporteResult[i]);
					float num = (float) rep.getCuenta();
					float tot = (float) (Long) reporteResult[0];
					float porc = num / tot * 100;
					rep.setPorcentaje(porc);
					reporte.add(rep);
				}
			}
		}
		else if(codVariable.equals("factRiesgoNoMod")){
			String[] campos = query.getReturnAliases();
			Object[] reporteResult = (Object[]) query.uniqueResult();
			for (int i=1;i<6;i++){
				if((Long) reporteResult[i]!=null){
					ReporteCaract rep = new ReporteCaract();
					rep.setValor(campos[i]);
					rep.setCuenta((Long) reporteResult[i]);
					float num = (float) rep.getCuenta();
					float tot = (float) (Long) reporteResult[0];
					float porc = num / tot * 100;
					rep.setPorcentaje(porc);
					reporte.add(rep);
				}
			}
		}
		else if(codVariable.equals("factRiesgoSocial")){
			String[] campos = query.getReturnAliases();
			Object[] reporteResult = (Object[]) query.uniqueResult();
			for (int i=1;i<9;i++){
				if((Long) reporteResult[i]!=null){
					ReporteCaract rep = new ReporteCaract();
					rep.setValor(campos[i]);
					rep.setCuenta((Long) reporteResult[i]);
					float num = (float) rep.getCuenta();
					float tot = (float) (Long) reporteResult[0];
					float porc = num / tot * 100;
					rep.setPorcentaje(porc);
					reporte.add(rep);
				}
			}
		}
		else{
			List<Object[]> reporteResult = query.list();
			Long total = 0L;
			for(Object[] objeto:reporteResult){
				reporte.add(new ReporteCaract(objeto[0].toString(),(Long)objeto[1], 0F));
				total = total + (Long)objeto[1];
			}
			for (ReporteCaract rep:reporte){
				float num = (float) rep.getCuenta();
				float tot = (float) total;
				float porc = num / tot * 100;
				rep.setPorcentaje(porc);
			}
		}
		return reporte;
	}
	
}
