
package ni.gob.minsa.comunitaria.hsf.utils;

/**
 * Constantes usadas en la base de datos de la aplicacion
 * 
 * @author William Aviles
 * 
 */
public class ConstantsDB {
	
	//Base de datos y tablas
	public static final String DATABASE_NAME = "familiar.sqlite3";
	public static final String DIV_TABLE = "divisionpolitica";
	public static final String SECTOR_TABLE = "sectores";
	public static final String COMU_TABLE = "comunidades";
	public static final String CATGEN_TABLE = "tblcatgen";
	public static final int DATABASE_VERSION = 1;

	//Campos division politica
	public static final String KEY_DIV_ID = "divisionpolitica_id";
	public static final String KEY_DIV_NAME = "nombre";
	public static final String KEY_DIV_DEP = "dependencia";
	public static final String KEY_DIV_ADM = "administracion";
	public static final String KEY_DIV_LONG = "longitud";
	public static final String KEY_DIV_LAT = "latitud";
	public static final String KEY_DIV_ISO = "codigo_iso";
	public static final String KEY_DIV_NAC = "codigo_nacional";
	public static final String KEY_DIV_PASIVO = "pasivo";
	public static final String KEY_DIV_FECREG = "fecha_registro";
	public static final String KEY_DIV_USUREG = "usuario_registro";
	public static final String KEY_DIV_CSE = "codigo_cse";
	
	//Campos sectores
	public static final String KEY_SECTOR_ID = "sector_id";
	public static final String KEY_SECTOR_NAME = "nombre";
	public static final String KEY_SECTOR_REF = "referencias";
	public static final String KEY_SECTOR_UND = "unidad";
	public static final String KEY_SECTOR_MUN = "municipio";
	public static final String KEY_SECTOR_COD = "codigo";
	public static final String KEY_SECTOR_SEDE = "sede";
	public static final String KEY_SECTOR_PASIVO = "pasivo";
	public static final String KEY_SECTOR_FECREG = "fecha_registro";
	public static final String KEY_SECTOR_USUREG = "usuario_registro";
	
	//Campos comunidades
	public static final String KEY_COMU_ID = "comunidad_id";
	public static final String KEY_COMU_NAME = "nombre";
	public static final String KEY_COMU_SECTOR = "sector";
	public static final String KEY_COMU_REF = "referencias";
	public static final String KEY_COMU_TIPOAREA = "tipo_area";
	public static final String KEY_COMU_COD = "codigo";
	public static final String KEY_COMU_CARACT = "caracteristicas";
	public static final String KEY_COMU_PASIVO = "pasivo";
	public static final String KEY_COMU_FECREG = "fecha_registro";
	public static final String KEY_COMU_USUREG = "usuario_registro";
	public static final String KEY_COMU_LONG = "longitud";
	public static final String KEY_COMU_LAT = "latitud";
	public static final String KEY_COMU_ZONA = "zona";
	
	//Campos catalogo general
	public static final String KEY_CG_ID = "idTiposvar";
	public static final String KEY_CG_TC = "idTipocat";
	public static final String KEY_CG_NAME = "nombre";


	public static final String CREATE_DIV_TABLE = "create table "
			+ DIV_TABLE + " ("
			+ KEY_DIV_ID + " integer not null, " 
			+ KEY_DIV_NAME + " text, "
			+ KEY_DIV_DEP + " integer, "
			+ KEY_DIV_ADM + " integer, "
			+ KEY_DIV_LONG + " real, "
			+ KEY_DIV_LAT + " real, "
			+ KEY_DIV_ISO + " text, "
			+ KEY_DIV_NAC + " text, "
			+ KEY_DIV_PASIVO + " text, "
			+ KEY_DIV_FECREG + " date, "
			+ KEY_DIV_USUREG + " text, "
			+ KEY_DIV_CSE + " integer, "
			+ "primary key (" + KEY_DIV_ID + "));";
	
	public static final String CREATE_SECTOR_TABLE = "create table "
			+ SECTOR_TABLE + " ("
			+ KEY_SECTOR_ID + " integer not null, " 
			+ KEY_SECTOR_NAME + " text, "
			+ KEY_SECTOR_UND + " integer, "
			+ KEY_SECTOR_MUN + " text, "
			+ KEY_SECTOR_REF + " text, "
			+ KEY_SECTOR_COD + " text, "
			+ KEY_SECTOR_PASIVO + " text, "
			+ KEY_SECTOR_SEDE + " text, "
			+ KEY_SECTOR_FECREG + " date, "
			+ KEY_SECTOR_USUREG + " text, "
			+ "primary key (" + KEY_SECTOR_ID + "));";


	public static final String CREATE_COMU_TABLE = "create table "
			+ COMU_TABLE + " ("
			+ KEY_COMU_ID + " integer not null, " 
			+ KEY_COMU_NAME + " text, "
			+ KEY_COMU_SECTOR + " text, "
			+ KEY_COMU_REF + " text, "
			+ KEY_COMU_TIPOAREA + " char, "
			+ KEY_COMU_COD + " text, "
			+ KEY_COMU_CARACT + " text, "
			+ KEY_COMU_PASIVO + " text, "
			+ KEY_COMU_FECREG + " date, "
			+ KEY_COMU_USUREG + " text, "
			+ KEY_COMU_LONG + " real, "
			+ KEY_COMU_LAT + " real, "
			+ KEY_COMU_ZONA + " text, "
			+ "primary key (" + KEY_COMU_ID + "));";
	
	public static final String CREATE_CATGEN_TABLE = "create table "
			+ CATGEN_TABLE + " ("
			+ KEY_CG_ID + " integer not null, " 
			+ KEY_CG_NAME + " text, "
			+ KEY_CG_TC + " integer, "
			+ KEY_COMU_REF + " text, "
			+ "primary key (" + KEY_CG_ID + "));";

	
}
