
package ni.gob.minsa.comunitaria.familiar.activities;


import ni.gob.minsa.comunitaria.familiar.R;
import ni.gob.minsa.comunitaria.familiar.listener.DownloadListener;
import ni.gob.minsa.comunitaria.familiar.preferences.PreferencesActivity;
import ni.gob.minsa.comunitaria.familiar.tasks.DownloadCatalogosTask;
import ni.gob.minsa.comunitaria.familiar.tasks.DownloadComunidadesTask;
import ni.gob.minsa.comunitaria.familiar.tasks.DownloadDivisionPoliticaTask;
import ni.gob.minsa.comunitaria.familiar.tasks.DownloadSectoresTask;
import ni.gob.minsa.comunitaria.familiar.utils.Constants;
import ni.gob.minsa.comunitaria.familiar.utils.FileUtils;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class DownloadActivity extends Activity implements DownloadListener{

	protected static final String TAG = DownloadActivity.class.getSimpleName();

	private String username;
	private String password;
	private String url;
	
	private SharedPreferences settings;
	private DownloadDivisionPoliticaTask downloadDivisionPoliticaTask;
	private DownloadSectoresTask downloadSectoresTask;
	private DownloadComunidadesTask downloadComunidadesTask;
	private DownloadCatalogosTask downloadCatalogosTask;
	
	private final static int PROGRESS_DIALOG = 1;
	private ProgressDialog mProgressDialog;
	private int opcion;

	// ***************************************
	// Metodos de la actividad
	// ***************************************
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(getString(R.string.app_name) + " > "
				+ getString(R.string.download));

		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			setResult(RESULT_CANCELED);
			finish();
		}
		settings =
				PreferenceManager.getDefaultSharedPreferences(this);
		url =
				settings.getString(PreferencesActivity.KEY_SERVER_URL, this.getString(R.string.default_server_url));
		username =
				settings.getString(PreferencesActivity.KEY_USERNAME,
						null);
		password =
				settings.getString(PreferencesActivity.KEY_PASSWORD,
						null);

		opcion = getIntent().getIntExtra(Constants.OPCION_DESCARGA,-1);

		switch(opcion){
		case Constants.DESCARGA_DIVPOL:
			downloadDivisionPoliticaTask = (DownloadDivisionPoliticaTask) getLastNonConfigurationInstance();
			if (downloadDivisionPoliticaTask == null) {
				downloadDivPol();
			}
			break;
		case Constants.DESCARGA_SECTORES:
			downloadSectoresTask = (DownloadSectoresTask) getLastNonConfigurationInstance();
			if (downloadSectoresTask == null) {
				downloadSectores();
			}
			break;
		case Constants.DESCARGA_COMUNIDADES:
			downloadComunidadesTask = (DownloadComunidadesTask) getLastNonConfigurationInstance();
			if (downloadComunidadesTask == null) {
				downloadComunidades();
			}
			break;
		case Constants.DESCARGA_CATALOGOS:
			downloadCatalogosTask = (DownloadCatalogosTask) getLastNonConfigurationInstance();
			if (downloadCatalogosTask == null) {
				downloadCat();
			}
			break;
		default:
			finish();
		}
	}

	private void downloadDivPol() {   
		if (downloadDivisionPoliticaTask != null)
			return;
		showDialog(PROGRESS_DIALOG);
		String mensaje = getString(R.string.update_divpol);
		downloadDivisionPoliticaTask = new DownloadDivisionPoliticaTask();
		downloadDivisionPoliticaTask.setDownloadListener(DownloadActivity.this);
		downloadDivisionPoliticaTask.execute(url, username, password, mensaje);
	}
	
	private void downloadCat() {   
		if (downloadCatalogosTask != null)
			return;
		showDialog(PROGRESS_DIALOG);
		String mensaje = getString(R.string.update_cat);
		downloadCatalogosTask = new DownloadCatalogosTask();
		downloadCatalogosTask.setDownloadListener(DownloadActivity.this);
		downloadCatalogosTask.execute(url, username, password, mensaje);
	}
	
	private void downloadSectores(){   
		if (downloadSectoresTask != null)
			return;
		showDialog(PROGRESS_DIALOG);
		String mensaje = getString(R.string.update_sector);
		String municipio = getIntent().getStringExtra("cod_muni");
		downloadSectoresTask = new DownloadSectoresTask();
		downloadSectoresTask.setDownloadListener(DownloadActivity.this);
		downloadSectoresTask.execute(url, username, password, municipio, mensaje);
	}

	private void downloadComunidades(){   
		if (downloadComunidadesTask != null)
			return;
		showDialog(PROGRESS_DIALOG);
		String mensaje = getString(R.string.update_comu);
		String sector = getIntent().getStringExtra("code_sector");
		downloadComunidadesTask = new DownloadComunidadesTask();
		downloadComunidadesTask.setDownloadListener(DownloadActivity.this);
		downloadComunidadesTask.execute(url, username, password, sector, mensaje);
	}
	
	@Override
	public void downloadComplete(String result) {

		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}

		if (result != null) {
			Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
			toast.show();
			setResult(RESULT_CANCELED);
		} else {
			setResult(RESULT_OK);
		}

		downloadDivisionPoliticaTask = null;
		downloadSectoresTask=null;
		downloadComunidadesTask = null;
		downloadCatalogosTask=null;
		finish();
	}

	@Override
	public void progressUpdate(String message, int progress, int max) {

		mProgressDialog.setMax(max);
		mProgressDialog.setProgress(progress);
		mProgressDialog.setTitle(message);

	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		switch(opcion){
		case Constants.DESCARGA_DIVPOL:
			return downloadDivisionPoliticaTask;
		case Constants.DESCARGA_SECTORES:
			return downloadSectoresTask;
		case Constants.DESCARGA_COMUNIDADES:
			return downloadComunidadesTask;
		case Constants.DESCARGA_CATALOGOS:
			return downloadCatalogosTask;
		default:
			return null;
		}

	}

	@Override
	protected void onDestroy() {
		if (downloadDivisionPoliticaTask != null) {
			downloadDivisionPoliticaTask.setDownloadListener(null);
		}
		if (downloadSectoresTask != null) {
			downloadSectoresTask.setDownloadListener(null);
		}
		if (downloadComunidadesTask != null) {
			downloadComunidadesTask.setDownloadListener(null);
		}
		if (downloadCatalogosTask != null) {
			downloadCatalogosTask.setDownloadListener(null);
		}
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (downloadDivisionPoliticaTask != null) {
			downloadDivisionPoliticaTask.setDownloadListener(this);
		}
		
		if (downloadSectoresTask != null) {
			downloadSectoresTask.setDownloadListener(this);
		}

		if (downloadComunidadesTask != null) {
			downloadComunidadesTask.setDownloadListener(this);
		}
		
		if (downloadCatalogosTask != null) {
			downloadCatalogosTask.setDownloadListener(this);
		}

		if (mProgressDialog != null && !mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == PROGRESS_DIALOG) {
			mProgressDialog = createDownloadDialog();
			return mProgressDialog;
		}
		return null;
	}

	private ProgressDialog createDownloadDialog() {

		ProgressDialog dialog = new ProgressDialog(this);
		DialogInterface.OnClickListener loadingButtonListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				downloadDivisionPoliticaTask.setDownloadListener(null);
				downloadSectoresTask.setDownloadListener(null);
				downloadComunidadesTask.setDownloadListener(null);
				downloadCatalogosTask.setDownloadListener(null);
				setResult(RESULT_CANCELED);
				finish();
			}
		};
		dialog.setTitle(getString(R.string.loading));
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setIndeterminate(false);
		dialog.setCancelable(false);
		dialog.setButton(getString(R.string.cancel),
				loadingButtonListener);
		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		if (id == PROGRESS_DIALOG) {
			ProgressDialog progress = (ProgressDialog) dialog;
			progress.setTitle(getString(R.string.loading));
			progress.setProgress(0);
		}
	}

}
