package com.example.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.newpercept.R;

public class SearchbyOfficeNameActivity extends Fragment implements OnClickListener {

	static String officename = "";
	static String cityname = "";
	static String statename = "";
	EditText etofficename;
	EditText etcity;
	EditText etstate;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.searchbyofficenamelayout, container, false);
	 
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.searchbyofficenamelayout);

	    	etofficename   = (EditText) rootView.findViewById(R.id.et_byofficename);
			etcity   = (EditText) rootView.findViewById(R.id.et_citybyofficename);
			etstate   = (EditText) rootView.findViewById(R.id.et_statebyofficename);
			
		// after pressing GOTO SEARCH PAGE button
		Button buttonsearchbyofficename = (Button) rootView.findViewById(R.id.button_searchbyofficename);
		buttonsearchbyofficename.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// goto result page after clicking
						
						officename  = etofficename.getText().toString();
						cityname  = etcity.getText().toString();
						statename  = etstate.getText().toString();
						Intent resultsearchbyofficename = new Intent(getActivity(), ResultSearchByOfficeNameActivity.class);
						startActivity(resultsearchbyofficename);
					}
				});
		  return rootView;
	 }
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.searchby_office_name, menu);
//		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
