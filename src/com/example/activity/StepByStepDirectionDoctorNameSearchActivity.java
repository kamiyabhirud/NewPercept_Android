package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.newpercept.R;

public class StepByStepDirectionDoctorNameSearchActivity  extends ListActivity  {

	public ArrayList<HashMap<String, String>> selectedroutedetails=  ListOfRoutesSearchByDoctorNameActivity.tempselectedroutedetails ;
	ArrayList<HashMap<String, String>> jsonarray;
	ArrayList<HashMap<String, String>> routestepslist;
	
	// JSON Node names
	String TAG_DURATION = "duration";
	String TAG_DISTANCE = "distance";
	String TAG_HTML_INSTRUCTIONS = "html_instructions";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stepbystepdirectiondoctornamesearchlayout);
		
		routestepslist = new  ArrayList<HashMap<String, String>>();
	
		  ListView routedetailslistview = getListView();
		  new Getroutedetails().execute();
		  
//		  Button buttonok = (Button) findViewById(R.id.button_backroutelistofc);
//			buttonok.setOnClickListener(new View.OnClickListener() {
//				public void onClick(View v) {
//					// goto register page after clicking
//					Intent gotoregisterpage = new Intent(StepByStepDirectionDoctorNameSearchActivity.this, ResultSearchByDoctorNameActivity.class);
//					startActivity(gotoregisterpage);
//				}
//			});
//		
	}//oncreate ends here
	
	private class Getroutedetails extends AsyncTask<Void, Void, Void> {


		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
		

			if (selectedroutedetails != null) {
				try {
					
					jsonarray = new ArrayList(selectedroutedetails);
			
					// loop each route
					for (int r = 0; r < jsonarray.size(); r++) {
						 HashMap<String, String> obj = jsonarray.get(r);
						
					     String tempdistance =  obj.get(TAG_DISTANCE);
					     String tempduration = obj.get(TAG_DURATION);
					     String tempinstr = obj.get(TAG_HTML_INSTRUCTIONS);
					  
							// tmp hashmap for single route
							HashMap<String, String> hmroutedetails = new HashMap<String, String>();
							hmroutedetails.put(TAG_HTML_INSTRUCTIONS, tempinstr);
							hmroutedetails.put(TAG_DISTANCE, tempdistance);
							hmroutedetails.put(TAG_DURATION, tempduration);

							// adding contact to contact list
							routestepslist.add(hmroutedetails);
			
//							System.out.println("routesteps ------>>"+routestepslist);
					
					}  //for loop ends here
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		} //on background ends here
		
		 @Override
	        protected void onPostExecute(Void result) {
	  
	            try{
	            	  System.out.println("before listadapter---------------->");
	            	             	   
	            	  ListAdapter stepsadapter = new SimpleAdapter(
	            			  StepByStepDirectionDoctorNameSearchActivity.this, routestepslist,
	                          R.layout.eachdetailforselectedrouteforstepbystepsearchbyoffice,
	                          new String[] { TAG_HTML_INSTRUCTIONS, TAG_DISTANCE,TAG_DURATION }, 
	                          new int[] { R.id.tvofcinstruction,R.id.tvofcdistance, R.id.tvofcduration });
	            	  
	           System.out.println("After listadapter----------------->");
	            setListAdapter(stepsadapter);
	            System.out.println("After set listadapter----------------->");
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} //async class ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.step_by_step_direction_doctor_name_search, menu);
		return true;
	}

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
}
