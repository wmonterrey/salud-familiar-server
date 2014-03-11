package ni.gob.minsa.comunitaria.hsf.activities;
/**
 * Actividad que presenta la lista de Municipios
 * 
 * @author William Aviles
 **/

import java.util.ArrayList;

import ni.gob.minsa.comunitaria.familiar.R;
import ni.gob.minsa.comunitaria.hsf.adapters.DivPoliticaAdapter;
import ni.gob.minsa.comunitaria.hsf.database.FamiliarAdapter;
import ni.gob.minsa.comunitaria.hsf.domain.Divisionpolitica;
import ni.gob.minsa.comunitaria.hsf.utils.Constants;
import ni.gob.minsa.comunitaria.hsf.utils.ConstantsDB;
import ni.gob.minsa.comunitaria.hsf.utils.FileUtils;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class ListMunicipiosActivity extends ListActivity{

	private ImageButton mBarcodeButton;
	private EditText mSearchText;
	private TextWatcher mFilterTextWatcher;

	private ArrayAdapter<Divisionpolitica> mMunicipioAdapter;
	private ArrayList<Divisionpolitica> mMunicipio = new ArrayList<Divisionpolitica>();

	public static final int BARCODE_CAPTURE = 2;
	public static final int DOWNLOAD_MUNI = 66;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.find_item);
		String dptoStr = getIntent().getStringExtra(Constants.KEY_NAME);
		setTitle(getString(R.string.app_name) + " > "
				+ getString(R.string.dpto) + " " + dptoStr);

		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			finish();
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	ActionBar actionBar = getActionBar();
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
		
		//Configuracion para la busqueda
		mFilterTextWatcher = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (mMunicipioAdapter != null) {
					mMunicipioAdapter.getFilter().filter(s);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}
		};

		mSearchText = (EditText) findViewById(R.id.search_text);
		mSearchText.addTextChangedListener(mFilterTextWatcher);

		mBarcodeButton = (ImageButton) findViewById(R.id.barcode_button);
		mBarcodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("com.google.zxing.client.android.SCAN");
				try {
					startActivityForResult(i, BARCODE_CAPTURE);
				} catch (ActivityNotFoundException e) {
					Toast t = Toast.makeText(getApplicationContext(),
							getString(R.string.error, R.string.barcode_error),
							Toast.LENGTH_LONG);
					t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t.show();
				}
				
			}
		});

	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position,
			long id) {
		// Obtener el municipio seleccionado
		Divisionpolitica m = (Divisionpolitica) getListAdapter().getItem(position);
		String cod_muni = m.getCodigoNacional();
		//Presenta la actividad de descarga de comunidades almacenando el municipio seleccionado
		Intent myIntent = new Intent(this, DownloadActivity.class);
		myIntent.putExtra(Constants.OPCION_DESCARGA, Constants.DESCARGA_SECTORES);
		myIntent.putExtra("cod_muni", cod_muni);
		startActivityForResult(myIntent,DOWNLOAD_MUNI);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {

		if (requestCode == BARCODE_CAPTURE && intent != null) {
			String sb = intent.getStringExtra("SCAN_RESULT");
			if (sb != null && sb.length() > 0) {
				mSearchText.setText(sb);
			}
		}
		
		if (requestCode == DOWNLOAD_MUNI) {
			if (resultCode == RESULT_OK){
				Toast.makeText(getApplicationContext(),R.string.success_download_sector,Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), ListSectoresActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
			else{
				Toast.makeText(getApplicationContext(),R.string.error_download_sector ,Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);

	}

	private void getMunicipios() {

		FamiliarAdapter ca = new FamiliarAdapter();
		ca.open();
		Cursor c = null;
		long silaisId = getIntent().getLongExtra(Constants.KEY_SILAIS_ID,-1);
		c = ca.fetchMunicipios(silaisId);

		if (c != null && c.getCount() > 0) {

			mMunicipio.clear();

			int idIndex = c
					.getColumnIndex(ConstantsDB.KEY_DIV_ID);
			int nombreIndex = c
					.getColumnIndex(ConstantsDB.KEY_DIV_NAME);
			int codeIndex = c
					.getColumnIndex(ConstantsDB.KEY_DIV_NAC);

			if (c.getCount() > 0) {

				Divisionpolitica m;
				do {
					m = new Divisionpolitica();
					m.setDivisionpoliticaId(c.getLong(idIndex));
					m.setNombre(c.getString(nombreIndex));
					m.setCodigoNacional(c.getString(codeIndex));
					mMunicipio.add(m);
				} while (c.moveToNext());
			}

		}

		refreshView();

		if (c != null) {
			c.close();
		}
		ca.close();

	}

	private void refreshView() {

		mMunicipioAdapter = new DivPoliticaAdapter(this, R.layout.list_item,
				mMunicipio);
		setListAdapter(mMunicipioAdapter);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mSearchText.removeTextChangedListener(mFilterTextWatcher);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getMunicipios();
		// filtrar nuevamente
		mSearchText.setText(mSearchText.getText().toString());
	}

	/**
     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
    	switch (menuItem.getItemId()) {
        case android.R.id.home:
          finish();
          Intent i = new Intent(getApplicationContext(), ListDepartamentosActivity.class);
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
          return true;
    	}
      return (super.onOptionsItemSelected(menuItem));
    }
    
    @Override
    public void onBackPressed (){
    	finish();
        Intent i = new Intent(getApplicationContext(), ListDepartamentosActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
