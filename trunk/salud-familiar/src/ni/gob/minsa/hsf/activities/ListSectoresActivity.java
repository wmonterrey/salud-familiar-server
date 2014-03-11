package ni.gob.minsa.hsf.activities;
/**
 * Actividad que presenta la lista de Sectores
 * 
 * @author William Aviles
 **/

import java.util.ArrayList;

import ni.gob.minsa.hsf.R;
import ni.gob.minsa.hsf.adapters.SectorAdapter;
import ni.gob.minsa.hsf.database.FamiliarAdapter;
import ni.gob.minsa.hsf.domain.Sector;
import ni.gob.minsa.hsf.preferences.PreferencesActivity;
import ni.gob.minsa.hsf.utils.Constants;
import ni.gob.minsa.hsf.utils.ConstantsDB;
import ni.gob.minsa.hsf.utils.FileUtils;




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
public class ListSectoresActivity extends ListActivity{

	private ImageButton mBarcodeButton;
	private EditText mSearchText;
	private TextWatcher mFilterTextWatcher;

	private ArrayAdapter<Sector> mSectorAdapter;
	private ArrayList<Sector> mSector = new ArrayList<Sector>();

	public static final int BARCODE_CAPTURE = 2;
	public static final int DOWNLOAD_COMU = 66;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.find_item);
		setTitle(getString(R.string.app_name) + " > "
				+ getString(R.string.find_sector));

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
				if (mSectorAdapter != null) {
					mSectorAdapter.getFilter().filter(s);
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
		
		Sector s = (Sector) getListAdapter().getItem(position);
		String code_sector = s.getCodigo();
		//Presenta la actividad de descarga de comunidades
		Intent myIntent = new Intent(this, DownloadActivity.class);
		myIntent.putExtra(Constants.OPCION_DESCARGA, Constants.DESCARGA_COMUNIDADES);
		myIntent.putExtra("code_sector", code_sector);
		startActivityForResult(myIntent,DOWNLOAD_COMU);
		
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
		
		if (requestCode == DOWNLOAD_COMU) {
			if (resultCode == RESULT_OK){
				Toast.makeText(getApplicationContext(),R.string.success_download_comu,Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), ListComunidadesActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
			else{
				Toast.makeText(getApplicationContext(),R.string.error_download_comu ,Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);

	}



	private void getSector() {

		FamiliarAdapter ca = new FamiliarAdapter();
		ca.open();
		Cursor c = null;
		c = ca.fetchAllSectores();

		if (c != null && c.getCount() > 0) {

			mSector.clear();

			int idIndex = c
					.getColumnIndex(ConstantsDB.KEY_SECTOR_COD);
			int nombreIndex = c
					.getColumnIndex(ConstantsDB.KEY_SECTOR_NAME);


			if (c.getCount() > 0) {

				Sector sector;
				do {
					sector = new Sector();
					sector.setCodigo(c.getString(idIndex));
					sector.setNombre(c.getString(nombreIndex));

					mSector.add(sector);
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

		mSectorAdapter = new SectorAdapter(this, R.layout.list_item,
				mSector);
		setListAdapter(mSectorAdapter);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mSearchText.removeTextChangedListener(mFilterTextWatcher);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getSector();
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
