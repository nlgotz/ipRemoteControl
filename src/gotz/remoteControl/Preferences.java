package gotz.remoteControl;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
	}
	
    @Override 
    protected void onResume(){
        super.onResume();
        // Set up a listener whenever a key changes             
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override 
    protected void onPause() { 
        super.onPause();
        // Unregister the listener whenever a key changes             
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);     
    } 
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { 
        updatePrefSummary(findPreference(key));
        String address = sharedPreferences.getString("address", "0");
        IpRemoteControlActivity.createX(address, Integer.valueOf(sharedPreferences.getString("port", "0")));
        
    }

    private void updatePrefSummary(Preference p){
        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p; 
            p.setSummary(listPref.getEntry()); 
        }
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p; 
            p.setSummary(editTextPref.getText()); 
        }

    }


}