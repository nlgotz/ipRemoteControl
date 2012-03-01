package gotz.remoteControl;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(arg0.getContext(), "Input is: " +
		          arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
