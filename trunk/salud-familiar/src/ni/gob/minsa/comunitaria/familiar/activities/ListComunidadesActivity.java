package ni.gob.minsa.comunitaria.familiar.activities;
/**
 * Actividad que presenta la lista de Comunidades
 * 
 * @author William Aviles
 **/

import java.util.ArrayList;

import ni.gob.minsa.comunitaria.familiar.R;
import ni.gob.minsa.comunitaria.familiar.adapters.ComunidadAdapter;
import ni.gob.minsa.comunitaria.familiar.database.FamiliarAdapter;
import ni.gob.minsa.comunitaria.familiar.domain.Comunidad;
import ni.gob.minsa.comunitaria.familiar.preferences.PreferencesActivity;
import ni.gob.minsa.comunitaria.familiar.utils.ConstantsDB;
import ni.gob.minsa.comunitaria.familiar.utils.FileUtils;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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


public class ListComunidadesActivity extends ListActivity{

	private ImageButton mBarcodeButton;
	private EditText mSearchText;
	private TextWatcher mFilterTextWatcher;

	private ArrayAdapter<Comunidad> mComunidadAdapter;
	private ArrayList<Comunidad> mComunidad = new ArrayList<Comunidad>();

	public static final int BARCODE_CAPTURE = 2;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.find_item);
		setTitle(getString(R.string.app_name));
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	ActionBar actionBar = getActionBar();
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }

		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			finish();
		}

		//Configuracion para la busqueda
		mFilterTextWatcher = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (mComunidadAdapter != null) {
					mComunidadAdapter.getFilter().filter(s);
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
		Comunidad com = (Comunidad) getListAdapter().getItem(position);
		String nombreComunidad = com.getNombre();
		Integer idComunidad = com.getComunidadId();
		//Presenta la comunidad
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences.edit();
        editor.putString(PreferencesActivity.KEY_COMUNIDAD, nombreComunidad);
        editor.commit();
        editor.putInt(PreferencesActivity.KEY_ID_COMUNIDAD, idComunidad);
        editor.commit();
        Toast.makeText(getApplicationContext(), this.getString(R.string.change_comu) + " " +com.getNombre(), Toast.LENGTH_LONG).show();
        finish();
		Intent i = new Intent(getApplicationContext(), PreferencesActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
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
		super.onActivityResult(requestCode, resultCode, intent);

	}

	private void getComunidades() {

		FamiliarAdapter ca = new FamiliarAdapter();
		ca.open();
		Cursor c = null;
		c = ca.fetchAllComunidades();

		if (c != null && c.getCount() > 0) {

			mComunidad.clear();

			int idIndex = c
					.getColumnIndex(ConstantsDB.KEY_COMU_ID);
			int nombreIndex = c
					.getColumnIndex(ConstantsDB.KEY_COMU_NAME);


			if (c.getCount() > 0) {

				Comunidad com;
				do {
					com = new Comunidad();
					com.setComunidadId(c.getInt(idIndex));
					com.setNombre(c.getString(nombreIndex));
					mComunidad.add(com);
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

		mComunidadAdapter = new ComunidadAdapter(this, R.layout.list_item,
				mComunidad);
		setListAdapter(mComunidadAdapter);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mSearchText.removeTextChangedListener(mFilterTextWatcher);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getComunidades();
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
          Intent i = new Intent(getApplicationContext(), PreferencesActivity.class);
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
          return true;
    	}
      return (super.onOptionsItemSelected(menuItem));
    }
    
    @Override
    public void onBackPressed (){
    	finish();
        Intent i = new Intent(getApplicationContext(), PreferencesActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
