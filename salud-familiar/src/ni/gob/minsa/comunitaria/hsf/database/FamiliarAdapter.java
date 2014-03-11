package ni.gob.minsa.comunitaria.hsf.database;

/**
 * Adaptador de la base de datos Salud Familiar
 * 
 * @author William Aviles
 */



import ni.gob.minsa.comunitaria.hsf.domain.CatGen;
import ni.gob.minsa.comunitaria.hsf.domain.Comunidad;
import ni.gob.minsa.comunitaria.hsf.domain.Divisionpolitica;
import ni.gob.minsa.comunitaria.hsf.domain.Sector;
import ni.gob.minsa.comunitaria.hsf.utils.ConstantsDB;
import ni.gob.minsa.comunitaria.hsf.utils.FileUtils;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FamiliarAdapter {
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static class DatabaseHelper extends FamiliarSQLiteOpenHelper {

		DatabaseHelper() {
			super(FileUtils.DATABASE_PATH, ConstantsDB.DATABASE_NAME, null, ConstantsDB.DATABASE_VERSION);
			createStorage();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(ConstantsDB.CREATE_DIV_TABLE);
			db.execSQL(ConstantsDB.CREATE_SECTOR_TABLE);
			db.execSQL(ConstantsDB.CREATE_COMU_TABLE);
			db.execSQL(ConstantsDB.CREATE_CATGEN_TABLE);
		}

		@Override
		// upgrading will destroy all old data
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + ConstantsDB.DIV_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + ConstantsDB.SECTOR_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + ConstantsDB.COMU_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + ConstantsDB.CATGEN_TABLE);
			onCreate(db);
		}
	}

	public FamiliarAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper();
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	/**
	 * Inserta un silais o municipio en la base de datos
	 * 
	 * @param divpolitica
	 *            Objeto Divpolitica que contiene la informacion
	 *
	 */
	public void createDivPolitica(Divisionpolitica divpolitica) {
		ContentValues cv = new ContentValues();
		cv.put(ConstantsDB.KEY_DIV_ID, divpolitica.getDivisionpoliticaId());
		cv.put(ConstantsDB.KEY_DIV_NAME, divpolitica.getNombre());
		cv.put(ConstantsDB.KEY_DIV_DEP, divpolitica.getDependencia());
		cv.put(ConstantsDB.KEY_DIV_ADM, divpolitica.getAdministracion());
		cv.put(ConstantsDB.KEY_DIV_PASIVO, String.valueOf(divpolitica.getPasivo()));
		cv.put(ConstantsDB.KEY_DIV_FECREG, divpolitica.getFechaRegistro().getMillis());
		cv.put(ConstantsDB.KEY_DIV_USUREG, divpolitica.getUsuarioRegistro());
		if (divpolitica.getLongitud()!=null)
		cv.put(ConstantsDB.KEY_DIV_LONG, divpolitica.getLongitud().doubleValue());
		if (divpolitica.getLatitud()!=null)
		cv.put(ConstantsDB.KEY_DIV_LAT, divpolitica.getLatitud().doubleValue());
		cv.put(ConstantsDB.KEY_DIV_ISO, divpolitica.getCodigoIso());
		cv.put(ConstantsDB.KEY_DIV_NAC, divpolitica.getCodigoNacional());
		cv.put(ConstantsDB.KEY_DIV_CSE, divpolitica.getCodigoCse());
		mDb.insert(ConstantsDB.DIV_TABLE, null, cv);
	}
	
	
	/**
	 * Borra todos los silais y municipios de la base de datos
	 * 
	 * @return verdadero o falso
	 */
	public boolean deleteAllDiv() {
		return mDb.delete(ConstantsDB.DIV_TABLE, null, null) > 0;
	}
	
	/**
	 * Busca una division de la base de datos
	 * 
	 * @return la division
	 */	
	
	public Cursor fetchDivisionPolitica(long divisionpolitica_id) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.DIV_TABLE, null,
				ConstantsDB.KEY_DIV_ID + "=" + divisionpolitica_id, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor fetchMunicipio(String munId) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.DIV_TABLE, null,
				ConstantsDB.KEY_DIV_NAC + "=" + munId, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	/**
	 * Lista todos los silais de la base de datos
	 * 
	 * @return dataset con divisionpolitica
	 */
	public Cursor fetchAllSilais() throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.DIV_TABLE, null,
				ConstantsDB.KEY_DIV_DEP + " is null", null, null, null, ConstantsDB.KEY_DIV_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	/**
	 * Lista todos los municipios de la base de datos
	 * 
	 * @return dataset con divisionpolitica
	 */
	public Cursor fetchAllMunicipios() throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.DIV_TABLE, null,
				ConstantsDB.KEY_DIV_DEP + "<>" + null, null, null, null, ConstantsDB.KEY_DIV_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	/**
	 * Lista todos los municipios de la base de datos para un silais
	 * 
	 * @return dataset con divisionpolitica
	 */
	public Cursor fetchMunicipios(long depId) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.DIV_TABLE, null,
				ConstantsDB.KEY_DIV_DEP + "=" + depId, null, null, null, ConstantsDB.KEY_DIV_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	/**
	 * Inserta un sector en la base de datos
	 * 
	 * @param sector
	 *            Objeto Sector que contiene la informacion
	 *
	 */
	public void createSector(Sector sector) {
		ContentValues cv = new ContentValues();
		cv.put(ConstantsDB.KEY_SECTOR_ID, sector.getSectorId());
		cv.put(ConstantsDB.KEY_SECTOR_NAME, sector.getNombre());
		cv.put(ConstantsDB.KEY_SECTOR_MUN, sector.getMunicipio());
		cv.put(ConstantsDB.KEY_SECTOR_UND, sector.getUnidad());
		cv.put(ConstantsDB.KEY_SECTOR_REF, sector.getReferencias());
		cv.put(ConstantsDB.KEY_SECTOR_COD, sector.getCodigo());
		cv.put(ConstantsDB.KEY_SECTOR_SEDE, String.valueOf(sector.getSede()));
		cv.put(ConstantsDB.KEY_SECTOR_PASIVO, String.valueOf(sector.getPasivo()));
		cv.put(ConstantsDB.KEY_SECTOR_FECREG, sector.getFechaRegistro().getMillis());
		cv.put(ConstantsDB.KEY_SECTOR_USUREG, sector.getUsuarioRegistro());
		mDb.insert(ConstantsDB.SECTOR_TABLE, null, cv);
	}

	/**
	 * Borra todos los sectores de la base de datos
	 * 
	 * @return verdadero o falso
	 */
	public boolean deleteAllSectores() {
		return mDb.delete(ConstantsDB.SECTOR_TABLE, null, null) > 0;
	}
		
	
	/**
	 * Busca un sector de la base de datos
	 * 
	 * @return el sector
	 */	
	
	public Cursor fetchSector(Integer sector_id) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.SECTOR_TABLE, null,
				ConstantsDB.KEY_SECTOR_ID + "=" + sector_id, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	/**
	 * Lista todos los sectores de la base de datos
	 * 
	 * @return dataset con sectores
	 */
	public Cursor fetchAllSectores() throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.SECTOR_TABLE, null,
				null, null, null, null, ConstantsDB.KEY_SECTOR_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	/**
	 * Inserta una comunidad en la base de datos
	 * 
	 * @param comunidad
	 *            Objeto Comunidad que contiene la informacion
	 *
	 */
	public void createComunidad(Comunidad comunidad) {
		ContentValues cv = new ContentValues();
		cv.put(ConstantsDB.KEY_COMU_ID, comunidad.getComunidadId());
		cv.put(ConstantsDB.KEY_COMU_NAME, comunidad.getNombre());
		cv.put(ConstantsDB.KEY_COMU_SECTOR, comunidad.getSector());
		cv.put(ConstantsDB.KEY_COMU_REF, comunidad.getReferencias());
		if(comunidad.getTipoArea()!=null)
		cv.put(ConstantsDB.KEY_COMU_TIPOAREA, comunidad.getTipoArea().toString());
		cv.put(ConstantsDB.KEY_COMU_COD, comunidad.getCodigo());
		cv.put(ConstantsDB.KEY_COMU_CARACT, comunidad.getCaracteristicas());
		cv.put(ConstantsDB.KEY_COMU_PASIVO, String.valueOf(comunidad.getPasivo()));
		cv.put(ConstantsDB.KEY_COMU_FECREG, comunidad.getFechaRegistro().getMillis());
		cv.put(ConstantsDB.KEY_COMU_USUREG, comunidad.getUsuarioRegistro());
		if(comunidad.getLongitud()!=null)
		cv.put(ConstantsDB.KEY_COMU_LONG, comunidad.getLongitud().doubleValue());
		if(comunidad.getLatitud()!=null)
		cv.put(ConstantsDB.KEY_COMU_LAT, comunidad.getLatitud().doubleValue());
		cv.put(ConstantsDB.KEY_COMU_ZONA, comunidad.getZona());
		mDb.insert(ConstantsDB.COMU_TABLE, null, cv);
	}

	/**
	 * Borra todas las comunidades de la base de datos
	 * 
	 * @return verdadero o falso
	 */
	public boolean deleteAllComunidades() {
		return mDb.delete(ConstantsDB.COMU_TABLE, null, null) > 0;
	}
		
	
	/**
	 * Busca una comunidad de la base de datos
	 * 
	 * @return la comunidad
	 */	
	
	public Cursor fetchComunidad(Integer comunidad_id) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.COMU_TABLE, null,
				ConstantsDB.KEY_COMU_ID + "=" + comunidad_id, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	/**
	 * Lista todas las comunidades de la base de datos
	 * 
	 * @return dataset con comunidades
	 */
	public Cursor fetchAllComunidades() throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.COMU_TABLE, null,
				null, null, null, null, ConstantsDB.KEY_COMU_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	/**
	 * Inserta un catgen en la base de datos
	 * 
	 * @param catgen
	 *            Objeto CatGen que contiene la informacion
	 *
	 */
	public void createCatGen(CatGen catgen) {
		ContentValues cv = new ContentValues();
		cv.put(ConstantsDB.KEY_CG_ID, catgen.getIdTiposvar());
		cv.put(ConstantsDB.KEY_CG_NAME, catgen.getNombre());
		cv.put(ConstantsDB.KEY_CG_TC, catgen.getIdTipocat());
		mDb.insert(ConstantsDB.CATGEN_TABLE, null, cv);
	}

	/**
	 * Borra todos los CatGen de la base de datos
	 * 
	 * @return verdadero o falso
	 */
	public boolean deleteAllCatGens() {
		return mDb.delete(ConstantsDB.CATGEN_TABLE, null, null) > 0;
	}
		
	
	/**
	 * Busca un CatGen de la base de datos
	 * 
	 * @return el CatGen
	 */	
	
	public Cursor fetchCatGen(Integer cg_id) throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.CATGEN_TABLE, null,
				ConstantsDB.KEY_CG_ID + "=" + cg_id, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	/**
	 * Lista todos los CatGen de la base de datos
	 * 
	 * @return dataset con CatGen
	 */
	public Cursor fetchAllCatGens() throws SQLException {
		Cursor c = null;
		c = mDb.query(true, ConstantsDB.CATGEN_TABLE, null,
				null, null, null, null, ConstantsDB.KEY_CG_NAME, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public static boolean createStorage() {
		return FileUtils.createFolder(FileUtils.DATABASE_PATH);
	}

}
