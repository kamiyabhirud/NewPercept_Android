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

public class SearchByDoctorNameActivity extends Fragment implements OnClickListener {
	
	static String doctorfirstname = "";
	static String doctorlastname = "";
	static String state = "";
	EditText etdocfirstname;
	EditText etdoclastname;
	EditText etstate;


	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.searchbydoctornamelayout, container, false);
	        
	        etdocfirstname   = (EditText) rootView.findViewById(R.id.et_docfirstname);
//			doctorfirstname  = etdocfirstname.getText().toString();
			etdoclastname   = (EditText) rootView.findViewById(R.id.et_doclastname);
//			doctorlastname  = etdoclastname.getText().toString();
			etstate   = (EditText) rootView.findViewById(R.id.et_state);
//			state  = etstate.getText().toString();
			
		
			
//	        after pressing GOTO SEARCH PAGE button
			Button buttonsearchbydoctorname = (Button) rootView.findViewById(R.id.button_search);
			buttonsearchbydoctorname.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							// goto result page after clickingrootView
//							etdocfirstname   = (EditText) rootView.findViewById(R.id.et_docfirstname);
							doctorfirstname  = etdocfirstname.getText().toString();
//							etdoclastname   = (EditText) rootView.findViewById(R.id.et_doclastname);
							doctorlastname  = etdoclastname.getText().toString();
//							etstate   = (EditText) rootView.findViewById(R.id.et_state);
							state  = etstate.getText().toString();
							Intent nologingotohomepage = new Intent(getActivity(), ResultSearchByDoctorNameActivity.class);
							startActivity(nologingotohomepage);
						}
					});
	        return rootView;
	    }
	 
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.search_by_doctor_name, menu);
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
