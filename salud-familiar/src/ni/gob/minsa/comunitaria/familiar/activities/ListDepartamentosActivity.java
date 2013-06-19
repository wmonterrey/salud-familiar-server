package ni.gob.minsa.comunitaria.familiar.activities;
/**
 * Actividad que presenta la lista de Departamentos
 * 
 * @author William Aviles
 **/

import java.util.ArrayList;

import ni.gob.minsa.comunitaria.familiar.R;
import ni.gob.minsa.comunitaria.familiar.adapters.DivPoliticaAdapter;
import ni.gob.minsa.comunitaria.familiar.database.FamiliarAdapter;
import ni.gob.minsa.comunitaria.familiar.domain.Divisionpolitica;
import ni.gob.minsa.comunitaria.familiar.preferences.PreferencesActivity;
import ni.gob.minsa.comunitaria.familiar.utils.Constants;
import ni.gob.minsa.comunitaria.familiar.utils.ConstantsDB;
import ni.gob.minsa.comunitaria.familiar.utils.FileUtils;




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


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListDepartamentosActivity extends ListActivity{

	private ImageButton mBarcodeButton;
	private EditText mSearchText;
	private TextWatcher mFilterTextWatcher;

	private ArrayAdapter<Divisionpolitica> mSilaisAdapter;
	private ArrayList<Divisionpolitica> mSilais = new ArrayList<Divisionpolitica>();

	public static final int BARCODE_CAPTURE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.find_item);
		setTitle(getString(R.string.app_name) + " > "
				+ getString(R.string.find_dpto));

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
				if (mSilaisAdapter != null) {
					mSilaisAdapter.getFilter().filter(s);
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
		// Get selected silais
		
		Divisionpolitica s = (Divisionpolitica) getListAdapter().getItem(position);
		long silais_id = s.getDivisionpoliticaId();
		String silais_name = s.getNombre();
		//Presenta la actividad de municipios almacenando el silais seleccionado
		Intent i = new Intent(getApplicationContext(),
				ListMunicipiosActivity.class);
		i.putExtra(Constants.KEY_SILAIS_ID, silais_id);
		i.putExtra(Constants.KEY_NAME, silais_name);
		startActivity(i);
	}
    
	@Override
    public void onBackPressed (){
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



	private void getSilais() {

		FamiliarAdapter ca = new FamiliarAdapter();
		ca.open();
		Cursor c = null;
		c = ca.fetchAllSilais();

		if (c != null && c.getCount() > 0) {

			mSilais.clear();

			int idIndex = c
					.getColumnIndex(ConstantsDB.KEY_DIV_ID);
			int nombreIndex = c
					.getColumnIndex(ConstantsDB.KEY_DIV_NAME);


			if (c.getCount() > 0) {

				Divisionpolitica s;
				do {
					s = new Divisionpolitica();
					s.setDivisionpoliticaId(c.getLong(idIndex));
					s.setNombre(c.getString(nombreIndex));

					mSilais.add(s);
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

		mSilaisAdapter = new DivPoliticaAdapter(this, R.layout.list_item,
				mSilais);
		setListAdapter(mSilaisAdapter);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mSearchText.removeTextChangedListener(mFilterTextWatcher);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getSilais();
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
}
