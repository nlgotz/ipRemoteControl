package gotz.remoteControl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IpRemoteControlActivity extends Activity {
	SharedPreferences preferences;
	public static Execute x;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(
        		  new SharedPreferences.OnSharedPreferenceChangeListener() {
        		  public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        		    // Implementation
        			  x=null;
        			  x = new Execute(preferences.getString("address", "0"), Integer.valueOf(preferences.getString("port", "0")));
        			  Toast toast =  Toast.makeText(getApplicationContext(), preferences.getString("address", "0"), Toast.LENGTH_SHORT);
        			  toast.show();
        		  }
        		});

        

        String address = preferences.getString("address", "0");
        int port = Integer.valueOf(preferences.getString("port", "10002"));
        //int port = 10002;
        createX(address,port);
        
        //Allow Power Control
        x.allowPowerControl();

        //Channel Up
        final Button channelUpButton = (Button) findViewById(R.id.channelUp);
        channelUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	System.out.println("blah");
            	
            	x.channelUp();
            }
        });

        
        
        //Channel Down
        final Button channelDownButton = (Button) findViewById(R.id.channelDw);
        channelDownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	System.out.println("blah");
            	
            	x.channelDown();
            	
            }
        });
      
        //Enter a channel
        final EditText channelChangerEditText = (EditText) findViewById(R.id.channelChanger);
        channelChangerEditText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                	x.adjustChannel(channelChangerEditText.getText());
                	channelChangerEditText.setText("");
                  return true;
                }
                return false;
            }
        });
        
      //Flashback
        final Button flashback = (Button) findViewById(R.id.flashback);
        flashback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	x.flashback();
            	
            }
        });
        
        //Volume Up
        final Button volumeUpButton = (Button) findViewById(R.id.volumeUp);
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	x.volumeUp();
            	
            }
        });
        
        //Volume Down
        final Button volumeDownButton = (Button) findViewById(R.id.volumeDw);
        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	x.volumeDown();
            	
            }
        });
        
        //Toggle Mute
        final Button muteButton = (Button) findViewById(R.id.mute);
        muteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	x.mute();
            	
            }
        });
        
        //Toggle Power
        final Button powerButton = (Button) findViewById(R.id.Power);
        powerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	x.power();
            	
            }
        });
        
        Spinner spinner = (Spinner) findViewById(R.id.inputSelector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.inputOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new inputSelector(x));


    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        
        
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
            	Intent i = new Intent(IpRemoteControlActivity.this, Preferences.class);
        		startActivity(i);
            break;
        }
        return true;
    }
    
    public static void createX(String address, int port) {
    	x = null;
    	x = new Execute(address, port);
    }
    
    
}