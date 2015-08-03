package com.example.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newpercept.R;

public class ResultSearchByLocationActivity  extends ListActivity  implements LocationListener {

	private static final String API_KEY = "AIzaSyAwC0MUWe8X39v8FhDNQ-ginkUcT76IRx8";
	
	public String TAG_FIRSTNAME = "docfirstname";
	public String TAG_LASTNAME = "doclastname";
	public String TAG_OFFICENAME = "officename";
	public String TAG_WORKINGHOURS = "workinghours";
	public String TAG_WORKINGDAYS = "workingdays";
	public String TAG_STREETADDRESS = "streetaddress";
	public String TAG_STATE = "state";
	public String TAG_CITY = "city";
	public String TAG_ZIPCODE = "zipcode";
	public String TAG_DOCTORCONTACT = "doctorcontact";
	public String TAG_NAME = "Name";
	
	public String tempdoctorfirstname = "";
	public String doctorname = "";
	public String officename = "";
	public String workinghrs = "";
	public String workingdays = "";
	public String streetaddress = "";
	public String state = "";
	public String city = "";
	public String zipcode = "";
	public String doctorcontact = "";
	
	public String getzipcode = encodeforurl(SearchByLocationActivity.zipcode);
	public String getstate = encodeforurl(SearchByLocationActivity.statename);
	public String getframebrand = encodeforurl(SearchByLocationActivity.framebrand);
	public String gets_eyeexam = encodeforurl(SearchByLocationActivity.s_eyeexam);
	public String gets_extendedhrs = encodeforurl(SearchByLocationActivity.s_extendedhrs);
	public String gets_visiontherapy = encodeforurl(SearchByLocationActivity.s_visiontherapy);
	public String gets_smallchildren = encodeforurl(SearchByLocationActivity.s_smallchildren);
	public String gets_advance = encodeforurl(SearchByLocationActivity.s_advance);
	public String gets_expresseyewear = encodeforurl(SearchByLocationActivity.s_expresseyewear);
	public String gets_laservisioncare = encodeforurl(SearchByLocationActivity.s_laservisioncare);
	public String gets_bigchildren = encodeforurl(SearchByLocationActivity.s_bigchildren);
	public String gets_preventativeeyecare = encodeforurl(SearchByLocationActivity.s_preventativeeyecare);
	public String gets_specialoffers = encodeforurl(SearchByLocationActivity.s_specialoffers);
	
	public String getp_glasses = encodeforurl(SearchByLocationActivity.p_glasses);
	public String getp_featuredframebrand = encodeforurl(SearchByLocationActivity.p_featuredframebrand);
	public String getp_contactlens = encodeforurl(SearchByLocationActivity.p_contactlens);
	public String getp_sportseyewear = encodeforurl(SearchByLocationActivity.p_sportseyewear);
	public String getp_unitylenses = encodeforurl(SearchByLocationActivity.p_unitylenses);
	public String getp_hardtofit = encodeforurl(SearchByLocationActivity.p_hardtofit);
	public String getp_otisandpiper = encodeforurl(SearchByLocationActivity.p_otisandpiper);
	public String getp_safetyprotec = encodeforurl(SearchByLocationActivity.p_safetyprotec);
	public String getp_lowvision = encodeforurl(SearchByLocationActivity.p_lowvision);
	public String getp_googleglasslenses = encodeforurl(SearchByLocationActivity.p_googleglasslenses);
	
	final Context context = this;
	
	AutoCompleteTextView atvPlaces_source;
	AutoCompleteTextView atvPlaces_destination;
	PlacesTask placesTask;
	ParserTask parserTask;
	public static String tempgetsource ;
	public static String tempgetdestination ;
	
	JSONArray jsonarray;
	String[] serviceslist ;
	String[] productslist ;
	ArrayList<HashMap<String, String>> doctorlist ;
	
	 int numservices ;
	 int numproducts;
	 private LocationManager locationManager;
	 private String provider;
	 Location location;
	 Geocoder gc;
	 String fulladdress;
	 
	ArrayList<HashMap<String, String>> doctorlistbyofficename ;
	
	//checkbox variables

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultsearchbylocationlayout);
		getActionBar().setHomeButtonEnabled(true);
		
		// Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
//	    Toast.makeText(this, (CharSequence) location, Toast.LENGTH_LONG).show();
	   
	    System.out.println("location-------------------------------------> "+location);
	   
	    //geocoding stuff
	    gc = new Geocoder(this);
//	    try {
//			List<Address> list = gc.getFromLocation(38.5995 , -121.284, 1);
//			Address add = list.get(0);
//			String locality = add.getLocality();
//			String street = add.getAddressLine(0);
//			String city = add.getLocality();
//			String state = add.getAdminArea();
//			String code = add.getPostalCode();
//			fulladdress = street + ", " + city + ", " + state + ", " + code;
//			Toast.makeText(this, fulladdress, Toast.LENGTH_LONG).show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
		doctorlistbyofficename =  new ArrayList<HashMap<String, String>>();
		new GetResultsSearchByOfficeName().execute();
	
		final ListView doctorslist = getListView();
		new GetServices().execute();
		new GetProducts().execute();
		   
//		doctorslist.setOnItemClickListener(new OnItemClickListener() {
//		   public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//			 
//			   // When clicked, goto directions page
//			   HashMap<String, String> data=(HashMap<String, String>) parent.getItemAtPosition(position);
//			   System.out.println("========================"+data);
//			   String docname=(String)data.get("Name");
//			   System.out.println("========================>>>>>>>"+docname);
			  
			   
//			   LayoutInflater li = LayoutInflater.from(context);
//				View promptsView = li.inflate(R.layout.dialogservicesproductslayout, null);
//				
//				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//				
//				// set prompts.xml to alertdialog builder
//				alertDialogBuilder.setView(promptsView);
//				
//				LinearLayout lm = (LinearLayout) promptsView.findViewById(R.id.linearservices);
////					 getservices( doctorname, lm);
//					// Create TextView
//					 for(int i=0; i<numservices;i++){
//					TextView tv = new TextView(context);
//					tv.setId(i);
//					tv.setText("• "+ serviceslist[i]);
//					lm.addView(tv);
//					 }
//					 
//					 LinearLayout ll = (LinearLayout) promptsView.findViewById(R.id.linearproducts);
////					 getservices( doctorname, lm);
//					// Create TextView
//					 for(int i=0; i<numproducts;i++){
//					TextView tv = new TextView(context);
//					tv.setId(i);
//					tv.setText("• "+ productslist[i]);
//					ll.addView(tv);
//					 }
//					
//					
//				// set dialog message
//				alertDialogBuilder.setCancelable(false)
//			.setNegativeButton("OK",new DialogInterface.OnClickListener() {
//					    public void onClick(DialogInterface dialog,int id) {
//						dialog.cancel();
//					    }
//					  });
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
// 
//				// show it
//				alertDialog.show();
//				
//		   }
//		  });
		
		// open dialog box button
//				Button buttongetdialogbox = (Button) findViewById(R.id.button_searchbylocmap);
//				buttongetdialogbox.setOnClickListener(new View.OnClickListener() {
//							public void onClick(View v) {
//								LayoutInflater li = LayoutInflater.from(context);
//								View promptsView = li.inflate(R.layout.routelistdialogsearchbylocationlayout, null);
//								
//								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//								
//								// set prompts.xml to alertdialog builder
//								alertDialogBuilder.setView(promptsView);
//								
//								// set dialog message
//								alertDialogBuilder.setCancelable(false).setPositiveButton("Go",
//									  new DialogInterface.OnClickListener() {
//									    public void onClick(DialogInterface dialog,int id) {
//									    	//goto page with list of routes
//									    	 Intent findroutelist = new Intent(ResultSearchByLocationActivity.this,ListOfRoutesSearchByLocationActivity.class);     
//								             startActivity(findroutelist);
//									    }
//									  })
//									.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//									    public void onClick(DialogInterface dialog,int id) {
//										dialog.cancel();
//									    }
//									  });
//								// create alert dialog
//								AlertDialog alertDialog = alertDialogBuilder.create();
//				 
//								// show it
//								alertDialog.show();
//								
//								//get source and destination using autocomplete
//								atvPlaces_source = (AutoCompleteTextView) promptsView.findViewById(R.id.etsourcesearchbyloc);
//								atvPlaces_source.setThreshold(1);
////								atvPlaces_destination = (EditText) promptsView.findViewById(R.id.etdestination);
//								atvPlaces_destination = (AutoCompleteTextView) promptsView.findViewById(R.id.etdestinationsearchbyloc);
//								atvPlaces_source.setThreshold(1);
//								
//								// on text change listner
//								atvPlaces_source.addTextChangedListener(new TextWatcher() {
//
//									@Override
//									public void onTextChanged(CharSequence s, int start, int before,int count) {
//										placesTask = new PlacesTask();
//										placesTask.execute(s.toString());
//									}
//
//									@Override
//									public void beforeTextChanged(CharSequence s, int start, int count,
//											int after) {
//										// TODO Auto-generated method stub
//									}
//
//									@Override
//									public void afterTextChanged(Editable s) {
//										// TODO Auto-generated method stub
//									}
//								});
//								
//								// ontext listner for destination
//								atvPlaces_destination.addTextChangedListener(new TextWatcher() {
//
//									@Override
//									public void onTextChanged(CharSequence s, int start, int before,
//											int count) {
//										placesTask = new PlacesTask();
//										placesTask.execute(s.toString());
//									}
//
//									@Override
//									public void beforeTextChanged(CharSequence s, int start, int count,
//											int after) {
//										// TODO Auto-generated method stub
//									}
//
//									@Override
//									public void afterTextChanged(Editable s) {
//										// TODO Auto-generated method stub
//									}
//
//								});
//								atvPlaces_source.setOnItemClickListener(new OnItemClickListener() {
//									 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//									    			      tempgetsource= atvPlaces_source.getText().toString();
//									    }
//									});
//											
//								atvPlaces_destination.setOnItemClickListener(new OnItemClickListener() {
//									 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//									         tempgetdestination= atvPlaces_destination.getText().toString();
//									    }
//									});
//						
//							} //view map button ends here
//						});
	
	} //oncreate ends here
	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url in method downliad url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	} // downloadurl ends here


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_search_by_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) 
		   {        
		      case android.R.id.home:            
		         Intent intent = new Intent(this, HomeActivity.class);            
		         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		         startActivity(intent);            
		         return true;        
		      default:            
		         return super.onOptionsItemSelected(item);    
		   }
	}
	private String encodeforurl(String data)
	{  String encodeddata = "data not encoded";
		try {
			 encodeddata =  URLEncoder.encode(data, "utf-8").replace("+", "%20");
			return encodeddata;
	                 	} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		                          }
		return data;
		}
	
	private class GetResultsSearchByOfficeName extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			   String GETSEARCHBYLOCATIONNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbylocation/zipcode/95670/state/CA/brand/Brand1/eyeexam/Eye%20Exam/extendedhrs/Extended%20Hours/visiontherapy/Vision%20Therapy/smallchild/Childern%20ages%20between%203%20and%205/advance/Advanced%20Eye%20Exam/expresseyewear/Express%20Eyewear/laservision/Laser%20Vision%20Care/bigchild/Childern%20ages%20between%203%20and%205/preventativeeyecare/Preventative%20Eye%20Care/specialoffer/Special%20Offers%20and%20Savings/glasses/Glasses/featuredframe/Featured%20Frame%20Brands/contacts/Contact%20Lenses/sportseye/Sports%20Eyewear/unitylens/Unity%20Lenses/hardtfit/Hard-to-fit%20contacts/otispiper/null/safetypro/Safety%20Pro%20Tec%20Eyewear/lowvision/null/googleglass/Google%20Glass%20Lenses";
//			String GETSEARCHBYLOCATIONNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbylocation/zipcode/"+getzipcode
//					+ "/state/"+getstate+"/brand/"+getframebrand+"/eyeexam/"+gets_eyeexam+"/extendedhrs/"
//					+gets_extendedhrs+"/visiontherapy/"+gets_visiontherapy+"/smallchild/"+gets_smallchildren
//					+"/advance/"+gets_advance+"/expresseyewear/"+gets_expresseyewear+"/laservision/"+gets_laservisioncare
//					+"/bigchild/"+gets_bigchildren+"/preventativeeyecare/"+gets_preventativeeyecare
//					+"/specialoffer/"+gets_specialoffers+"/glasses/"+getp_glasses+"/featuredframe/"+getp_featuredframebrand
//					+"/contacts/"+getp_contactlens+"/sportseye/"+getp_sportseyewear+"/unitylens/"+getp_unitylenses
//					+"/hardtfit/"+getp_hardtofit+"/otispiper/"+getp_otisandpiper+"/safetypro/"+getp_safetyprotec
//					+"/lowvision/"+getp_lowvision+"/googleglass/"+getp_googleglasslenses;
			
			Log.d("RURL------------------------>>>>>>>>>>>>","...."+GETSEARCHBYLOCATIONNAMEURL);
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrsearchbyofficename = sh.makeServiceCall(GETSEARCHBYLOCATIONNAMEURL,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by office----> ", "> " + jsonStrsearchbyofficename);

			if (jsonStrsearchbyofficename != null) {
				try {
					 jsonarray = new JSONArray(jsonStrsearchbyofficename);
						for(int r = 0; r < jsonarray.length(); r++){
							JSONObject jsonObj = jsonarray.getJSONObject(r);
//					JSONObject jsonObj = new JSONObject(jsonStrsearchbyofficename);
					
					     tempdoctorfirstname = jsonObj.getString(TAG_FIRSTNAME);
					     System.out.println("firstname : "+ tempdoctorfirstname );
					     doctorname = tempdoctorfirstname + " "+ jsonObj.getString(TAG_LASTNAME);
						 System.out.println("lastname : "+ doctorname );				
						 officename = jsonObj.getString(TAG_OFFICENAME);
						 System.out.println("office : "+ officename );
						 workinghrs = jsonObj.getString(TAG_WORKINGHOURS);
						 System.out.println("hrs : "+ workinghrs );
						 workingdays = jsonObj.getString(TAG_WORKINGDAYS);
						 System.out.println("coverage : "+ workingdays );
						 streetaddress = jsonObj.getString(TAG_STREETADDRESS);
						 System.out.println("description : "+ streetaddress );
						 city = streetaddress + ", "+jsonObj.getString(TAG_CITY);
					     System.out.println("username : "+ city );
						 state = city + ", "+jsonObj.getString(TAG_STATE);
					     System.out.println("username : "+ state );
					     zipcode = state+", "+ jsonObj.getString(TAG_ZIPCODE);
					     System.out.println("username : "+ zipcode );
					     doctorcontact = jsonObj.getString(TAG_DOCTORCONTACT);
					     System.out.println("username : "+ doctorcontact );
					     
					     HashMap<String, String> hmdoctorlist = new HashMap<String, String>();

//							hmdoctorlist.put(TAG_FIRSTNAME, doctorfirstname);
							hmdoctorlist.put(TAG_NAME,doctorname);
							hmdoctorlist.put(TAG_OFFICENAME,officename);
							hmdoctorlist.put(TAG_WORKINGHOURS,workinghrs);
							hmdoctorlist.put(TAG_WORKINGDAYS,workingdays);
//							hmdoctorlist.put(TAG_STREETADDRESS,streetaddress);
//							hmdoctorlist.put(TAG_STATE,state);
//							hmdoctorlist.put(TAG_CITY,city);
							hmdoctorlist.put(TAG_ZIPCODE,zipcode);
							hmdoctorlist.put(TAG_DOCTORCONTACT,doctorcontact);

							// adding contact to contact list
							doctorlistbyofficename.add(hmdoctorlist);
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
	            try{
	            	  System.out.println("before listadapter---------------->");

	            	  ListAdapter adapter = new SimpleAdapter(
	            			  ResultSearchByLocationActivity.this, doctorlistbyofficename,
	                          R.layout.eachdoctordetailssearchbylocationlayout, new String[] { 
	            					  TAG_NAME,TAG_OFFICENAME,TAG_WORKINGHOURS,TAG_WORKINGDAYS, TAG_ZIPCODE,TAG_DOCTORCONTACT  }, 
	            					  new int[] { R.id.tvbylocdocname,  R.id.tvbylocofficename, R.id.tvbylocworkinghrs , R.id.tvbylocworkingdays
	            					   , R.id.tvbyloczipcode,R.id.tvbylocdrcontact})
	            	  {
	            		  @Override
	            	        public View getView (int position, View convertView, ViewGroup parent)
	            	        {
	            	            View v = super.getView(position, convertView, parent);
	            	           
	            	             Button b=(Button)v.findViewById(R.id.button_listviewonmapbyloc);
	            	             b.setOnClickListener(new OnClickListener() {

	            	                @Override
	            	                public void onClick(View arg0) {
//	            	                	   HashMap<String, String> data=(HashMap<String, String>) getItemAtPosition(position);
//	            	    				   System.out.println("========================"+data);
//	            	    				   String docname=(String)data.get("Name");
	            	    				   
	            	                	/****map stuff***/
	            	                	LayoutInflater li = LayoutInflater.from(context);
	    								View promptsView = li.inflate(R.layout.routelistdialogsearchbylocationlayout, null);
	    								
	    								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	    								
	    								// set prompts.xml to alertdialog builder
	    								alertDialogBuilder.setView(promptsView);
	    								
	    								// set dialog message
	    								alertDialogBuilder.setCancelable(false).setPositiveButton("Go",
	    									  new DialogInterface.OnClickListener() {
	    									    public void onClick(DialogInterface dialog,int id) {
	    									    	//goto page with list of routes
	    									    	 Intent findroutelist = new Intent(ResultSearchByLocationActivity.this,ListOfRoutesSearchByLocationActivity.class);     
	    								             startActivity(findroutelist);
	    									    }
	    									  })
	    									.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
	    									    public void onClick(DialogInterface dialog,int id) {
	    										dialog.cancel();
	    									    }
	    									  });
	    								// create alert dialog
	    								AlertDialog alertDialog = alertDialogBuilder.create();
	    				 
	    								// show it
	    								alertDialog.show();
	    								
//	    								 gc = new Geocoder(this);
	    								    try {
	    										List<Address> list = gc.getFromLocation(38.5995 , -121.284, 1);
	    										Address add = list.get(0);
	    										String locality = add.getLocality();
	    										String street = add.getAddressLine(0);
	    										String city = add.getLocality();
	    										String state = add.getAdminArea();
	    										String code = add.getPostalCode();
	    										fulladdress = street + ", " + city + ", " + state + ", " + code;
	    										
	    									} catch (IOException e) {
	    										// TODO Auto-generated catch block
	    										e.printStackTrace();
	    									}
	    								    
	    								//get source and destination using autocomplete
	    								atvPlaces_source = (AutoCompleteTextView) promptsView.findViewById(R.id.etsourcesearchbyloc);
	    								atvPlaces_source.append(fulladdress);
	    								atvPlaces_source.setThreshold(1);
//	    								atvPlaces_destination = (EditText) promptsView.findViewById(R.id.etdestination);
	    								atvPlaces_destination = (AutoCompleteTextView) promptsView.findViewById(R.id.etdestinationsearchbyloc);
//	    								atvPlaces_destination.setText("3333 Quality Drive, Rancho Cordova, CA, United States");
	    								atvPlaces_destination.append("3333 Quality Drive, Rancho Cordova, CA, United States");
	    								atvPlaces_source.setThreshold(1);
	    								
	    								// on text change listner
	    								atvPlaces_source.addTextChangedListener(new TextWatcher() {

	    									@Override
	    									public void onTextChanged(CharSequence s, int start, int before,int count) {
	    										placesTask = new PlacesTask();
	    										placesTask.execute(s.toString());
	    									}

	    									@Override
	    									public void beforeTextChanged(CharSequence s, int start, int count,
	    											int after) {
	    										// TODO Auto-generated method stub
	    									}

	    									@Override
	    									public void afterTextChanged(Editable s) {
	    										// TODO Auto-generated method stub
	    									}
	    								});
	    								
	    								// ontext listner for destination
	    								atvPlaces_destination.addTextChangedListener(new TextWatcher() {

	    									@Override
	    									public void onTextChanged(CharSequence s, int start, int before,
	    											int count) {
	    										placesTask = new PlacesTask();
	    										placesTask.execute(s.toString());
	    									}

	    									@Override
	    									public void beforeTextChanged(CharSequence s, int start, int count,
	    											int after) {
	    										// TODO Auto-generated method stub
	    									}

	    									@Override
	    									public void afterTextChanged(Editable s) {
	    										// TODO Auto-generated method stub
	    									}

	    								});
	    								atvPlaces_source.setOnItemClickListener(new OnItemClickListener() {
	    									 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    									    			      tempgetsource= atvPlaces_source.getText().toString();
	    									    }
	    									});
	    											
	    								atvPlaces_destination.setOnItemClickListener(new OnItemClickListener() {
	    									 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    									         tempgetdestination= atvPlaces_destination.getText().toString();
	    									    }
	    									});
	    						
	    							} //view map button ends here
	    						});
	            	            
	            	             /**********producst and services view****/
	            	             Button bprod=(Button)v.findViewById(R.id.button_productsbyloc);
	            	             bprod.setOnClickListener(new OnClickListener() {

		            	                @Override
		            	                public void onClick(View arg0) {
		            	                	 LayoutInflater li = LayoutInflater.from(context);
		            	     				View promptsView = li.inflate(R.layout.dialogservicesproductslayout, null);
		            	     				
		            	     				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            	     				
		            	     				// set prompts.xml to alertdialog builder
		            	     				alertDialogBuilder.setView(promptsView);
		            	     				
		            	     				LinearLayout lm = (LinearLayout) promptsView.findViewById(R.id.linearservices);
//		            	     					 getservices( doctorname, lm);
		            	     					// Create TextView
		            	     					 for(int i=0; i<numservices;i++){
		            	     					TextView tv = new TextView(context);
		            	     					tv.setId(i);
		            	     					tv.setText("• "+ serviceslist[i]);
		            	     					lm.addView(tv);
		            	     					 }
		            	     					 
		            	     					 LinearLayout ll = (LinearLayout) promptsView.findViewById(R.id.linearproducts);
//		            	     					 getservices( doctorname, lm);
		            	     					// Create TextView
		            	     					 for(int i=0; i<numproducts;i++){
		            	     					TextView tv = new TextView(context);
		            	     					tv.setId(i);
		            	     					tv.setText("• "+ productslist[i]);
		            	     					ll.addView(tv);
		            	     					 }
		            	     					
		            	     					
		            	     				// set dialog message
		            	     				alertDialogBuilder.setCancelable(false)
		            	     			.setNegativeButton("OK",new DialogInterface.OnClickListener() {
		            	     					    public void onClick(DialogInterface dialog,int id) {
		            	     						dialog.cancel();
		            	     					    }
		            	     					  });
		            	     				// create alert dialog
		            	     				AlertDialog alertDialog = alertDialogBuilder.create();
		            	      
		            	     				// show it
		            	     				alertDialog.show();
		            	     				
		            	     		   }
		            	     		  });
         return v;
	            	        }
	            	    }; //justafter adapter starting
	            	     setListAdapter(adapter);
	            	  
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} // get office name result async class ends here

	// Fetches all places from GooglePlaces AutoComplete Web Service
		private class PlacesTask extends AsyncTask<String, Void, String> {

			@Override
			protected String doInBackground(String... place) {
				// For storing data from web service
				String data = "";

				// Obtain browser key from https://code.google.com/apis/console
				// String key = "API_KEY";

				String input = "";

				try {
					input = "input=" + URLEncoder.encode(place[0], "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				// place type to be searched
				String types = "types=geocode";

				// Sensor enabled
				String sensor = "sensor=true";

				// Building the parameters to the web service
				// String parameters = input+"&"+types+"&"+sensor+"&"+key;

				// Output format
			//	String output = "json";

				// Building the url to the web service
				// https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Folsom&types=geocode&location=37.76999,-122.44696&radius=500&sensor=true&key=AIzaSyAwC0MUWe8X39v8FhDNQ-ginkUcT76IRx8
				String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?"+ input + "&" + types + "&" + sensor + "&key=" + API_KEY;
				// String url =
				// "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;

				try {
					// Fetching the data from we service
					data = downloadUrl(url);
				} catch (Exception e) {
					Log.d("Background Task", e.toString());
				}
				return data;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				// Creating ParserTask
				parserTask = new ParserTask();

				// Starting Parsing the JSON string returned by Web Service
				parserTask.execute(result);
			}
		} // places task ends here

		/** A class to parse the Google Places in JSON format */
		private class ParserTask extends
				AsyncTask<String, Integer, List<HashMap<String, String>>> {

			JSONObject jObject;

			@Override
			protected List<HashMap<String, String>> doInBackground(
					String... jsonData) {

				List<HashMap<String, String>> places = null;

				PlaceJSONParser placeJsonParser = new PlaceJSONParser();

				try {
					jObject = new JSONObject(jsonData[0]);

					// Getting the parsed data as a List construct
					places = placeJsonParser.parse(jObject);

				} catch (Exception e) {
					Log.d("Exception", e.toString());
				}
				return places;
			}

			@Override
			protected void onPostExecute(List<HashMap<String, String>> result) {

			String[] from = new String[] { "description" };
				int[] to = new int[] { android.R.id.text1 };

				// Creating a SimpleAdapter for the AutoCompleteTextView
				SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result,
						android.R.layout.simple_list_item_1, from, to);

				// Setting the adapter
				atvPlaces_source.setAdapter(adapter);

				atvPlaces_destination.setAdapter(adapter);
				
				System.out.println(tempgetsource +"adapter tempsoure nd dest"+tempgetdestination);
						
			}
		} // parser task ends here

		private class GetServices extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/
			@Override
			protected Void doInBackground(Void... arg0) {
				 String GETSERVICES = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/getservicesatdoctors/docfirstname/Pooja%20Shah";
//				 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/Pooja/doctorlastname/Shah/state/CA";
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();

				// Making a request to url and getting response
				String jsonStrsearchbydocname = sh.makeServiceCall(GETSERVICES,ServiceHandler.GET);

				Log.d("Response: logs for SEARCH by name---------------------------> ", "> " + jsonStrsearchbydocname);

				if (jsonStrsearchbydocname != null) {
					try {
						
						//get whole array
						 jsonarray = new JSONArray(jsonStrsearchbydocname);
	                    numservices = jsonarray.length();
						 
	                    serviceslist = new String[numservices];
						for(int r = 0; r < jsonarray.length(); r++){
							JSONObject jsobservicesobj = jsonarray.getJSONObject(r);
							
						     String tempservices = jsobservicesobj.getString("services");
						     System.out.println("services : "+ tempservices );				     
						     
						     serviceslist[r] = tempservices;
						     System.out.println("services list::::::::: : "+ serviceslist[r] );
						}//for loop ends here for jsonarray						
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
		         
		           
		        }
			
		} //async services class ends here
		
		private class GetProducts extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/
			@Override
			protected Void doInBackground(Void... arg0) {
				 String GETPRODUCTS = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/getproductsatdoctors/docfirstname/Pooja%20Shah";
//				 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/Pooja/doctorlastname/Shah/state/CA";
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();

				// Making a request to url and getting response
				String jsonStrsearchbydocname = sh.makeServiceCall(GETPRODUCTS,ServiceHandler.GET);

				Log.d("Response: logs for SEARCH by name---------------------------> ", "> " + jsonStrsearchbydocname);

				if (jsonStrsearchbydocname != null) {
					try {
						
						//get whole array
						 jsonarray = new JSONArray(jsonStrsearchbydocname);
	                    numproducts = jsonarray.length();
						 
	                    productslist = new String[numproducts];
						for(int r = 0; r < jsonarray.length(); r++){
							JSONObject jsobproductobj = jsonarray.getJSONObject(r);
							
						     String tempproducts = jsobproductobj.getString("products");
						     System.out.println("products : "+ tempproducts );				     
						     
						     productslist[r] = tempproducts;
						     System.out.println("products list::::::::: : "+ productslist[r] );
						}//for loop ends here for jsonarray						
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
		         
		           
		        }
			
		} //async class ends here

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

}//main activity ends here
