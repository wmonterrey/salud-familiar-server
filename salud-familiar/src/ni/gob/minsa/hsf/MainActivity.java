package ni.gob.minsa.hsf;


import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import ni.gob.minsa.hsf.R;
import ni.gob.minsa.hsf.adapters.MenuPrincipalAdapter;
import ni.gob.minsa.hsf.preferences.PreferencesActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {
	
	private static final String EXIT_SHOWING = "exitshowing";
	private boolean mExitShowing;
	private AlertDialog exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] menu_main = getResources().getStringArray(R.array.menu_main);
		setListAdapter(new MenuPrincipalAdapter(this, R.layout.menu_item, menu_main));
		
		if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXIT_SHOWING)) {
                mExitShowing = savedInstanceState.getBoolean(EXIT_SHOWING, false);
            }
        }
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	
		
		switch (item.getItemId()) {
		case R.id.MENU_EXIT:
			createExitDialog();
			return true;
		case R.id.MENU_PREFERENCES:
			Intent ig = new Intent(this, PreferencesActivity.class);
            startActivity(ig);
            return true;
		default:
		    return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXIT_SHOWING, mExitShowing);
    }
    
    
    @Override
    protected void onResume() {
        if (mExitShowing) {
        	createExitDialog();
        }
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (exit != null && exit.isShowing()) {
        	exit.dismiss();
        }
    }

	@Override
	public void onBackPressed (){
		createExitDialog();
	}
	
	private void createExitDialog() {
		// Pregunta si desea salir o no
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String s = getMyPhoneNumber();
		int mes = getSemanasPE();
	    builder.setTitle(this.getString(R.string.confirm));
	    builder.setMessage(this.getString(R.string.exiting)+ s + "- mes"+mes);

	    builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {
	            // Finish app

	            dialog.dismiss();
	            mExitShowing=false;
	            finish();
	        }

	    });

	    builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            // Do nothing
	            dialog.dismiss();
	            mExitShowing=false;
	        }
	    });

	    exit = builder.create();
	    exit.show();
	    mExitShowing=true;
	}
	
	private String getMyPhoneNumber(){
	    TelephonyManager mTelephonyMgr;
	    WifiManager wifi;
	    mTelephonyMgr = (TelephonyManager)
	        getSystemService(Context.TELEPHONY_SERVICE); 
	    
	    wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    
	    String deviceId;

	    deviceId = mTelephonyMgr.getDeviceId();
	    
	    if (deviceId != null){
	    	deviceId = wifi.getConnectionInfo().getMacAddress();
	    }
	    Date fecha = new Date();
	    UUID deviceUuid = new UUID(deviceId.hashCode(),fecha.hashCode());
	    return deviceUuid.toString();
	}
	
	public int getSemanasPE() {
        int semanas = 0;
        Calendar fur = Calendar.getInstance();
        fur.set(2013, Calendar.DECEMBER, 31);
        Calendar now = Calendar.getInstance();
        long diff = now.getTimeInMillis() - fur.getTimeInMillis();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        semanas = (int) (dayCount / 7) ;
        return semanas;
    }
	
	
}
