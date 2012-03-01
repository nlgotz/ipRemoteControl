package gotz.remoteControl;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class inputSelector implements OnItemSelectedListener {
	
	private Execute x;
	
	public inputSelector(Execute x) {
		this.x = x;
	}


	public void onItemSelected(AdapterView<?> parent,
        View view, int pos, long id) {
      Toast.makeText(parent.getContext(), "The planet is " +
          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
      if(pos==0) {
    	  x.selectTV();
      } else {
    	  x.selectInput(pos);
      }
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
}