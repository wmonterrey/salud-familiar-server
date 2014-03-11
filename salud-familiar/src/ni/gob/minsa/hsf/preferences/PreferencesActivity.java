/*
 * Copyright (C) 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package ni.gob.minsa.hsf.preferences;

import ni.gob.minsa.hsf.R;
import ni.gob.minsa.hsf.activities.DownloadActivity;
import ni.gob.minsa.hsf.activities.ListComunidadesActivity;
import ni.gob.minsa.hsf.activities.ListDepartamentosActivity;
import ni.gob.minsa.hsf.activities.ListSectoresActivity;
import ni.gob.minsa.hsf.utils.Constants;
import ni.gob.minsa.hsf.utils.UrlUtils;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author william aviles
 */

@SuppressWarnings("deprecation")
public class PreferencesActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    public static String KEY_SERVER_URL = "server_url";
    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";
    public static String KEY_REM_PASSWORD = "remember_password";
    public static String KEY_COMUNIDAD = "comunidad";
    public static String KEY_ID_COMUNIDAD = "id_comu";
    public static String KEY_UPDATE_DIVPOL = "update_divpol";
    public static String KEY_UPDATE_SECTOR = "update_sector";
    public static String KEY_UPDATE_COMU = "update_comu";
    public static String KEY_UPDATE_CAT = "update_cat";
    public static String KEY_COMPLETE_FORM = "complete_form";


    private EditTextPreference mServerUrlPreference;
    private EditTextPreference mUsernamePreference;
    private EditTextPreference mPasswordPreference;
    private PreferenceScreen mComunidadPreference;
    private PreferenceScreen mUpdateDivPolPreference;
    private PreferenceScreen mUpdateSectorPreference;
    private PreferenceScreen mUpdateComuPreference;
    private PreferenceScreen mUpdateCatPreference;
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	ActionBar actionBar = getActionBar();
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
        buscarComunidad();
        actualizarDivPol();
        actualizarCat();
        actualizarSectores();
        actualizarComunidades();
        updateServerUrl();
        updateUsername();
        updatePassword();
        updateComunidad();
        updateCompleteForm();
    }


	@Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
            this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        updateServerUrl();
        updateUsername();
        updatePassword();
        updateComunidad();
        updateCompleteForm();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_CANCELED) {
        	if (requestCode ==  Constants.DESCARGA_DIVPOL) {
		    	Toast.makeText(getApplicationContext(),R.string.error_download_divpol ,Toast.LENGTH_LONG).show();
		    }
        	if (requestCode ==  Constants.DESCARGA_SECTORES) {
		    	Toast.makeText(getApplicationContext(),R.string.error_download_sector ,Toast.LENGTH_LONG).show();
		    }
        	if (requestCode ==  Constants.DESCARGA_COMUNIDADES) {
		    	Toast.makeText(getApplicationContext(),R.string.error_download_comu ,Toast.LENGTH_LONG).show();
		    }
        	if (requestCode ==  Constants.DESCARGA_CATALOGOS) {
		    	Toast.makeText(getApplicationContext(),R.string.error_download_cat ,Toast.LENGTH_LONG).show();
		    }
            return;
        }
        else{
        	if (requestCode == Constants.DESCARGA_DIVPOL){
    			Toast.makeText(getApplicationContext(),R.string.success_download_divpol,Toast.LENGTH_SHORT).show();
    		}
        	if (requestCode == Constants.DESCARGA_SECTORES){
    			Toast.makeText(getApplicationContext(),R.string.success_download_sector,Toast.LENGTH_SHORT).show();
    		}
        	if (requestCode == Constants.DESCARGA_COMUNIDADES){
    			Toast.makeText(getApplicationContext(),R.string.success_download_comu,Toast.LENGTH_SHORT).show();
    		}
        	if (requestCode == Constants.DESCARGA_CATALOGOS){
    			Toast.makeText(getApplicationContext(),R.string.success_download_cat,Toast.LENGTH_SHORT).show();
    		}
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	if (key.equals(KEY_SERVER_URL)) {
            updateServerUrl();
        } else if (key.equals(KEY_USERNAME)) {
            updateUsername();
        } else if (key.equals(KEY_PASSWORD)) {
            updatePassword();
        } else if (key.equals(KEY_COMUNIDAD)) {
            updateComunidad();
        } else if (key.equals(KEY_COMPLETE_FORM)) {
            updateCompleteForm();
        }
    }

    private void validateUrl(EditTextPreference preference) {
        if (preference != null) {
            String url = preference.getText();
            if (UrlUtils.isValidUrl(url)) {
                preference.setText(url);
                preference.setSummary(url);
            } else {
                // preference.setText((String) preference.getSummary());
                Toast.makeText(getApplicationContext(), getString(R.string.url_error),
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateCompleteForm() {
        ListPreference lp = (ListPreference) findPreference(KEY_COMPLETE_FORM);
        lp.setSummary(lp.getEntry());
    }

    private void updateServerUrl() {
        mServerUrlPreference = (EditTextPreference) findPreference(KEY_SERVER_URL);

        // remove all trailing "/"s
        while (mServerUrlPreference.getText().endsWith("/")) {
            mServerUrlPreference.setText(mServerUrlPreference.getText().substring(0,
                mServerUrlPreference.getText().length() - 1));
        }
        validateUrl(mServerUrlPreference);
        mServerUrlPreference.setSummary(mServerUrlPreference.getText());

        mServerUrlPreference.getEditText().setFilters(new InputFilter[] {
            getReturnFilter()
        });
    }


    private void updateUsername() {
        mUsernamePreference = (EditTextPreference) findPreference(KEY_USERNAME);
        mUsernamePreference.setSummary(mUsernamePreference.getText());

        mUsernamePreference.getEditText().setFilters(new InputFilter[] {
            getWhitespaceFilter()
        });

    }


    private void updatePassword() {
        mPasswordPreference = (EditTextPreference) findPreference(KEY_PASSWORD);
        if (mPasswordPreference.getText() != null && mPasswordPreference.getText().length() > 0) {
            mPasswordPreference.setSummary("********");
        } else {
            mPasswordPreference.setSummary("");

        }
        mPasswordPreference.getEditText().setFilters(new InputFilter[] {
            getWhitespaceFilter()
        });
    }

    private void updateComunidad() {
        mComunidadPreference = (PreferenceScreen) findPreference(KEY_COMUNIDAD);
        mComunidadPreference.setSummary(mComunidadPreference.getSharedPreferences().getString(KEY_COMUNIDAD, null));
    }
    
    private void buscarComunidad() {
    	mComunidadPreference = (PreferenceScreen) findPreference(KEY_COMUNIDAD);
    	mComunidadPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// Presenta Departamento
				Intent myIntent = new Intent(PreferencesActivity.this, ListComunidadesActivity.class);
				PreferencesActivity.this.startActivity(myIntent);
			    return true;
			}
    		
    	}); 	
    }
    
    
    private void actualizarDivPol() {
    	mUpdateDivPolPreference = (PreferenceScreen) findPreference(KEY_UPDATE_DIVPOL);
    	mUpdateDivPolPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent myIntent = new Intent(PreferencesActivity.this, DownloadActivity.class);
				myIntent.putExtra(Constants.OPCION_DESCARGA, Constants.DESCARGA_DIVPOL);
				PreferencesActivity.this.startActivityForResult(myIntent,Constants.DESCARGA_DIVPOL);
			    return true;
			}
    		
    	}); 	
    }
    
    private void actualizarCat() {
    	mUpdateCatPreference = (PreferenceScreen) findPreference(KEY_UPDATE_CAT);
    	mUpdateCatPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent myIntent = new Intent(PreferencesActivity.this, DownloadActivity.class);
				myIntent.putExtra(Constants.OPCION_DESCARGA, Constants.DESCARGA_CATALOGOS);
				PreferencesActivity.this.startActivityForResult(myIntent, Constants.DESCARGA_CATALOGOS);
			    return true;
			}
    		
    	}); 	
    }
    
    private void actualizarSectores() {
    	mUpdateSectorPreference = (PreferenceScreen) findPreference(KEY_UPDATE_SECTOR);
    	mUpdateSectorPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// Presenta sectores
				Intent myIntent = new Intent(PreferencesActivity.this, ListDepartamentosActivity.class);
				PreferencesActivity.this.startActivity(myIntent);
			    return true;
			}
    	}); 	
    }
    
    private void actualizarComunidades() {
    	mUpdateComuPreference = (PreferenceScreen) findPreference(KEY_UPDATE_COMU);
    	mUpdateComuPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// Presenta comunidades
				Intent myIntent = new Intent(PreferencesActivity.this, ListSectoresActivity.class);
				PreferencesActivity.this.startActivity(myIntent);
			    return true;
			}
    	}); 	
    }
    
    private InputFilter getWhitespaceFilter() {
        InputFilter whitespaceFilter = new InputFilter() {
            @Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                    int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        return whitespaceFilter;
    }


    private InputFilter getReturnFilter() {
        InputFilter returnFilter = new InputFilter() {
            @Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                    int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.getType((source.charAt(i))) == Character.CONTROL) {
                        return "";
                    }
                }
                return null;
            }
        };
        return returnFilter;
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
          return true;
    	}
      return (super.onOptionsItemSelected(menuItem));
    }
}
