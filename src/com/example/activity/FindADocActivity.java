package com.example.activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.newpercept.R;

public class FindADocActivity extends ActionBarActivity {

	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2, Tab3;
	Fragment fragmentTab1 = new SearchByLocationActivity();
	Fragment fragmentTab2 = new SearchByDoctorNameActivity();
	Fragment fragmentTab3 = new SearchbyOfficeNameActivity();  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findadoclayout);

		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Location/Services");
		Tab2 = actionBar.newTab().setText("Doctor Name");
		Tab3 = actionBar.newTab().setText("Office Name");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.findadoclayout);
	//
	// ListView listfinddochome;
	// String listfinddochome_array[]={"Search by Location/Services"
	// ,"Search by Doctor Name"
	// ,"Search by Office Name" };
	//
	// listfinddochome=(ListView)findViewById(R.id.findadoctorlist);
	// listfinddochome.setAdapter(new
	// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
	// ,listfinddochome_array));
	//
	// listfinddochome.setOnItemClickListener(new OnItemClickListener() {
	// public void onItemClick(AdapterView<?> parent, View view,int position,
	// long id) {
	// // final TextView mTextView = (TextView)view;
	// switch (position) {
	// case 0:
	// Intent howtousebenefitsact = new
	// Intent(FindADocActivity.this,SearchByLocationActivity.class);
	// startActivity(howtousebenefitsact);
	// break;
	// case 1:
	// Intent benefitswithinnetworkact = new
	// Intent(FindADocActivity.this,SearchByDoctorNameActivity.class);
	// startActivity(benefitswithinnetworkact);
	// break;
	// case 2:
	// Intent benefitswithoutnetworkact = new
	// Intent(FindADocActivity.this,SearchbyOfficeNameActivity.class);
	// startActivity(benefitswithoutnetworkact);
	// break;
	//
	// default:
	// // Nothing do!
	// }
	//
	// }
	// });
	//
	// //Back button navigates to home page
	// Button buttonbacktohomefromfindadoc = (Button)
	// findViewById(R.id.button_back);
	// buttonbacktohomefromfindadoc.setOnClickListener(new
	// View.OnClickListener() {
	// public void onClick(View v) {
	// // goto home page after clicking
	// Intent gotohomefromfindadoc = new Intent(FindADocActivity.this,
	// HomeActivity.class);
	// startActivity(gotohomefromfindadoc);
	// }
	// });
	// } // on create ends here
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.find_adoc, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
