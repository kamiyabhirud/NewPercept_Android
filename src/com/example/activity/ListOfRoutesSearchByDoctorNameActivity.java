package com.example.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.newpercept.R;
import com.google.android.gms.maps.model.LatLng;

public class ListOfRoutesSearchByDoctorNameActivity extends ListActivity {

private static String API_KEY ="AIzaSyCauDrsTQ-6j6cAEothtNZD7P1kBUUaH6Y";
	/********** result from search by office name*******/
	public String toencodeorigin = ResultSearchByDoctorNameActivity.tempgetsource ;
	private String origin = "origin=" + 	encodeforurl(toencodeorigin);
	
	private String toencodedestination = ResultSearchByDoctorNameActivity.tempgetdestination;
	private String destination = "destination="+encodeforurl(toencodedestination);
	
	private String alternatives = "alternatives=true";
	private String sensor = "sensor=true";
	
	private String getdirectionsurl = "http://maps.googleapis.com/maps/api/directions/json?"+ origin	+ "&"	+ destination	+ "&"	+ sensor+ "&API_KEY="+ API_KEY+ "&" + alternatives;
	//http://maps.googleapis.com/maps/api/directions/json?origin=folsom,california,USA&destination=Sacramento,CA,USA&sensor=false&API_KEY=AIzaSyCauDrsTQ-6j6cAEothtNZD7P1kBUUaH6Y&alternatives=true
	// JSON Node names
		String TAG_ROUTES = "routes";
		String TAG_DURATION = "duration";
		String TAG_DISTANCE = "distance";
		String TAG_LEGS = "legs";
		String TAG_STEPS = "steps";
		String TAG_SUMMARY = "summary";
		String TAG_TEXT = "text";
		String TAG_HTML_INSTRUCTIONS = "html_instructions";
		String TAG_END_LOCATION = "end_location";
		String TAG_START_LOCATION = "start_location";
		String TAG_POLYLINE = "polyline";
		String TAG_POINTS = "points";
		String TAG_LAT = "lat"; 
		String TAG_LNG = "lng";

		// routes JSONArray
		JSONArray jarrayroutes = null;
		JSONArray jarraylegs = null;
		JSONArray jarraysteps = null;

		/********* 2 arrays for route list and another with route details *******/
		
		//ArrayList<String> temproutelist = new ArrayList<String>(); 
		ArrayList<HashMap<String, String>> routeslist ;
		ArrayList<ArrayList<HashMap<String, String>>> allroutessteps ;
		ArrayList<HashMap<String, String>> stepslist ; 
		public static ArrayList<HashMap<String, String>> tempselectedroutedetails ;
		public static LatLng sourcelatlng ;
		public static LatLng destinationlatlng;
		
//		 public int selectedrouteposition;
		/******** handle origin and destination input***********/
		private String encodeforurl(String data)
		{  String encodeddata = "data not encoded";
			try {
				 encodeddata =  URLEncoder.encode(data, "utf-8");
				return encodeddata;
		                 	} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			                          }
			return data;
			}	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listofroutessearchbydoctornamelayout);
		
		routeslist =  new ArrayList<HashMap<String, String>>();
		stepslist = new ArrayList<HashMap<String, String>>();
		allroutessteps = new ArrayList<ArrayList<HashMap<String, String>>>();	
		tempselectedroutedetails = new ArrayList<HashMap<String, String>>();
		
		  final ListView routelistview = getListView();
		  new Getroutelist().execute();
		  
		  /***** when user selectes one of the route from the list ****/
		  routelistview.setOnItemClickListener(new OnItemClickListener() {
			   public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//				   selectedrouteposition = position;
//					System.out.println("positiom=================>"+selectedrouteposition); //seletcing items 
					
					tempselectedroutedetails = allroutessteps.get(position);
					System.out.println("route selected*****************************"+tempselectedroutedetails);
//					tempsourcedestination_latlng = sourcedestination.get(position);
					System.out.println("latlng selected*****************************"+sourcelatlng + "=============="+destinationlatlng);
					
				   // When clicked, goto directions page
//				   Intent gotodirectionspage = new Intent(ListOfRoutesSearchByDoctorNameActivity.this,StepByStepDirectionDoctorNameSearchActivity.class);     
					Intent gotodirectionspage = new Intent(ListOfRoutesSearchByDoctorNameActivity.this,MapsRouteActivity.class);
		             startActivity(gotodirectionspage);
				   
			   }
			  });
		  
	} //oncreate ends here
	
	private class Getroutelist extends AsyncTask<Void, Void, Void> {


		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(getdirectionsurl,
					ServiceHandler.GET);

//			Log.d("Response: logs ----> ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					jarrayroutes = jsonObj.getJSONArray(TAG_ROUTES);
				
					// loop each route
					for (int r = 0; r < jarrayroutes.length(); r++) {
						JSONObject jobjroutes = jarrayroutes.getJSONObject(r);
						
						String rsummary = jobjroutes.optString(TAG_SUMMARY);

						/**** create node for parsing legs ****/
						jarraylegs = jobjroutes.optJSONArray(TAG_LEGS);
						// loop each legs
						for (int l = 0; l < jarraylegs.length(); l++) {
							
							JSONObject jobjlegs = jarraylegs.optJSONObject(l); // get lth element inlegs array
							
							JSONObject jobjlegsdistance = jobjlegs.getJSONObject(TAG_DISTANCE);
							String rdistance = jobjlegsdistance.getString(TAG_TEXT);

							JSONObject jobjlegsduration = jobjlegs.getJSONObject(TAG_DURATION);
							String rduration = jobjlegsduration.getString(TAG_TEXT);
							
							JSONObject jobjstartloc = jobjlegs.getJSONObject(TAG_START_LOCATION);
							double sourcestartlat = jobjstartloc.getDouble(TAG_LAT);
							double sourcestartlng = jobjstartloc.getDouble(TAG_LNG);
							 sourcelatlng = new LatLng(sourcestartlat,sourcestartlng);
							
							JSONObject jobjendloc = jobjlegs.getJSONObject(TAG_END_LOCATION);
							double destendlat = jobjendloc.getDouble(TAG_LAT);
							double destendlng = jobjendloc.getDouble(TAG_LNG);
							 destinationlatlng = new LatLng(destendlat,destendlng);
	
							/**** parse for each route details***/
							JSONArray jobjlegsstepsarray = jobjlegs.getJSONArray(TAG_STEPS);
							for (int s = 0; s < jobjlegsstepsarray.length(); s++) {
								JSONObject jobjsteps = jobjlegsstepsarray.optJSONObject(s); 
								
								JSONObject jobjstepsdistance = jobjsteps.getJSONObject(TAG_DISTANCE);
								String stepdistance = jobjstepsdistance.getString(TAG_TEXT);

								JSONObject jobjstepsduration = jobjsteps.getJSONObject(TAG_DURATION);
								String stepduration = jobjstepsduration.getString(TAG_TEXT);
								
								String tempinstr = jobjsteps.getString(TAG_HTML_INSTRUCTIONS);
								String stepinstruction = android.text.Html.fromHtml(tempinstr).toString();
								
								HashMap<String, String> hmstepslist = new HashMap<String, String>();
					
								hmstepslist.put(TAG_DISTANCE, stepdistance);
								hmstepslist.put(TAG_DURATION, stepduration);
								hmstepslist.put(TAG_HTML_INSTRUCTIONS, stepinstruction);
//								hmstepslist.put("START_LOCATION_LAT", startlat);
//								hmstepslist.put("START_LOCATION_LAT", startlng);
//								hmstepslist.put("END_LOCATION_LAT", endlat);
//								hmstepslist.put("END_LOCATION_LAT", endlng);
//								hmstepslist.put("TAG_POLYLINE", polylinepoints);
								
								stepslist.add(hmstepslist);
//								System.out.println("steplist================> " + stepslist);
							} //allroutessteps
							
							
							allroutessteps.add(stepslist);   //add the steps for that route

							// tmp hashmap for single route
							HashMap<String, String> hmroutelist = new HashMap<String, String>();

						
							hmroutelist.put(TAG_SUMMARY, rsummary);
							hmroutelist.put(TAG_DISTANCE, rdistance);
							hmroutelist.put(TAG_DURATION, rduration);

							// adding contact to contact list
							routeslist.add(hmroutelist);
					}

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		} //on background ends here
		
		 @Override
	        protected void onPostExecute(Void result) {
	      
	            /**
	             * Updating parsed JSON data into ListView
	             * */
	   
	            try{
	            	            	   
	            	  ListAdapter adapter = new SimpleAdapter(
	            			  ListOfRoutesSearchByDoctorNameActivity.this, routeslist,
	                          R.layout.eachiteminroutelistlayout, new String[] { TAG_SUMMARY, TAG_DISTANCE,
	            					  TAG_DURATION }, new int[] { R.id.summary,
	                                  R.id.distance, R.id.duration });
	            	  
	            setListAdapter(adapter);
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} //async class ends here


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_of_routes_search_by_doctor_name,
				menu);
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
