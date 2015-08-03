package com.example.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newpercept.R;
import com.example.newpercept.R.layout;

public class ResultSearchByDoctorNameActivity extends ListActivity {

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
	
	public String doctorfirstname = "";
	public String doctorname = "";
	public String officename = "";
	public String workinghrs = "";
	public String workingdays = "";
	public String streetaddress = "";
	public String state = "";
	public String city = "";
	public String zipcode = "";
	public String doctorcontact = "";
	
	final Context context = this;
	public String getdoctorfirstname = SearchByDoctorNameActivity.doctorfirstname;
	public String getdoctorlastname = SearchByDoctorNameActivity.doctorlastname;
	public String getstate = SearchByDoctorNameActivity.state;
	
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
	 ListView doctorslist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultsearchbydoctornamelayout);
		getActionBar().setHomeButtonEnabled(true);
		
		doctorlist =  new ArrayList<HashMap<String, String>>();
		new GetResultsSearchByDoctorName().execute();
		
		//getservices and products
		  final ListView doctorslist = getListView();
		  new GetServices().execute();
		   new GetProducts().execute();
		   
//		  doctorslist.setOnItemClickListener(new OnItemClickListener() {
//			   public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//				 
//				   // When clicked, goto directions page
//				   HashMap<String, String> data=(HashMap<String, String>) parent.getItemAtPosition(position);
//				   System.out.println("========================"+data);
//				   String docname=(String)data.get("Name");
//				   System.out.println("========================>>>>>>>"+docname);
//				  
//				   
//				   LayoutInflater li = LayoutInflater.from(context);
//					View promptsView = li.inflate(R.layout.dialogservicesproductslayout, null);
//					
//					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//					
//					// set prompts.xml to alertdialog builder
//					alertDialogBuilder.setView(promptsView);
//					
//					LinearLayout lm = (LinearLayout) promptsView.findViewById(R.id.linearservices);
////						 getservices( doctorname, lm);
//						// Create TextView
//						 for(int i=0; i<numservices;i++){
//						TextView tv = new TextView(context);
//						tv.setId(i);
//						tv.setText("• "+ serviceslist[i]);
//						lm.addView(tv);
//						 }
//						 
//						 LinearLayout ll = (LinearLayout) promptsView.findViewById(R.id.linearproducts);
////						 getservices( doctorname, lm);
//						// Create TextView
//						 for(int i=0; i<numproducts;i++){
//						TextView tv = new TextView(context);
//						tv.setId(i);
//						tv.setText("• "+ productslist[i]);
//						ll.addView(tv);
//						 }
//						
//						
//					// set dialog message
//					alertDialogBuilder.setCancelable(false)
//				.setNegativeButton("OK",new DialogInterface.OnClickListener() {
//						    public void onClick(DialogInterface dialog,int id) {
//							dialog.cancel();
//						    }
//						  });
//					// create alert dialog
//					AlertDialog alertDialog = alertDialogBuilder.create();
//	 
//					// show it
//					alertDialog.show();
//					
//			   }
//			  });
		
//		Button buttongetdialogbox = (Button) findViewById(R.id.button_searchbydoctornamemap);
//		  Button buttongetdialogbox = (Button) findViewById(R.id.button_listviewonmap);
//		buttongetdialogbox.setOnClickListener(new View.OnClickListener() {
//					public void onClick(View v) {
//						// goto result page after clicking
////						Intent resultsearchbyofficename = new Intent(SearchbyOfficeNameActivity.this, ResultSearchByOfficeNameActivity.class);
////						startActivity(resultsearchbyofficename);
//						// get prompts.xml view
//						LayoutInflater li = LayoutInflater.from(context);
//						View promptsView = li.inflate(R.layout.routelistdialogsearchbydocnamelayout, null);
//						
//						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//						
//						// set prompts.xml to alertdialog builder
//						alertDialogBuilder.setView(promptsView);
//						
//						// set dialog message
//						alertDialogBuilder.setCancelable(false).setPositiveButton("Go",
//							  new DialogInterface.OnClickListener() {
//							    public void onClick(DialogInterface dialog,int id) {
//							    	//goto page with list of routes
//							    	 Intent findroutelist = new Intent(ResultSearchByDoctorNameActivity.this,ListOfRoutesSearchByDoctorNameActivity.class);     
//						             startActivity(findroutelist);
//								// get user input and set it to result
//								// edit text
////								result.setText(userInput.getText());
//							    }
//							  })
//							.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//							    public void onClick(DialogInterface dialog,int id) {
//								dialog.cancel();
//							    }
//							  });
//						// create alert dialog
//						AlertDialog alertDialog = alertDialogBuilder.create();
//		 
//						// show it
//						alertDialog.show();
//						
//						//get source and destination using autocomplete
//						atvPlaces_source = (AutoCompleteTextView) promptsView.findViewById(R.id.etsourcesearchbydocname);
//						atvPlaces_source.setThreshold(1);
////						atvPlaces_destination = (EditText) promptsView.findViewById(R.id.etdestination);
//						atvPlaces_destination = (AutoCompleteTextView) promptsView.findViewById(R.id.etdestinationsearchbydocname);
//						atvPlaces_source.setThreshold(1);
//						
//						// on text change listner
//						atvPlaces_source.addTextChangedListener(new TextWatcher() {
//
//							@Override
//							public void onTextChanged(CharSequence s, int start, int before,int count) {
//								placesTask = new PlacesTask();
//								placesTask.execute(s.toString());
//							}
//
//							@Override
//							public void beforeTextChanged(CharSequence s, int start, int count,
//									int after) {
//								// TODO Auto-generated method stub
//							}
//
//							@Override
//							public void afterTextChanged(Editable s) {
//								// TODO Auto-generated method stub
//							}
//						});
//						
//						// ontext listner for destination
//						atvPlaces_destination.addTextChangedListener(new TextWatcher() {
//
//							@Override
//							public void onTextChanged(CharSequence s, int start, int before,
//									int count) {
//								placesTask = new PlacesTask();
//								placesTask.execute(s.toString());
//							}
//
//							@Override
//							public void beforeTextChanged(CharSequence s, int start, int count,
//									int after) {
//								// TODO Auto-generated method stub
//							}
//
//							@Override
//							public void afterTextChanged(Editable s) {
//								// TODO Auto-generated method stub
//							}
//
//						});
//						atvPlaces_source.setOnItemClickListener(new OnItemClickListener() {
//							 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//							    			        //tempgetsource = (String) arg0.getItemAtPosition(arg2);
//							    			      tempgetsource= atvPlaces_source.getText().toString();
//							    }
//							});
//									
//						atvPlaces_destination.setOnItemClickListener(new OnItemClickListener() {
//							 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//							     			      //  tempgetdestination =  (String) arg0.getItemAtPosition(arg2);
//							     			    tempgetdestination= atvPlaces_destination.getText().toString();
//							       // String starttext=arg0.getItemAtPosition(arg2);
//							    }
//							});
//				
//					} //view map button ends here
//				});

		
		
	}// on create ends here
	
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_search_by_doctor_name, menu);
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

	
	private class GetResultsSearchByDoctorName extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/"+getdoctorfirstname+
					 "/doctorlastname/"+getdoctorlastname+"/state/"+getstate;
//			 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/Pooja/doctorlastname/Shah/state/CA";
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrsearchbydocname = sh.makeServiceCall(GETSEARCHBYDOCNAMEURL,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by name---------------------------> ", "> " + jsonStrsearchbydocname);

			if (jsonStrsearchbydocname != null) {
				try {
					
					//get whole array
					 jsonarray = new JSONArray(jsonStrsearchbydocname);
					
					for(int r = 0; r < jsonarray.length(); r++){
						JSONObject jsondoctorobject = jsonarray.getJSONObject(r);
						
					     String tempdoctorfirstname = jsondoctorobject.getString(TAG_FIRSTNAME);
					     System.out.println("firstname : "+ tempdoctorfirstname );
					     doctorname = tempdoctorfirstname + " " +jsondoctorobject.getString(TAG_LASTNAME);
						 System.out.println("lastname : "+ doctorname );				
						 officename = jsondoctorobject.getString(TAG_OFFICENAME);
						 System.out.println("office : "+ officename );
						 workinghrs = jsondoctorobject.getString(TAG_WORKINGHOURS);
						 System.out.println("hrs : "+ workinghrs );
						 workingdays = jsondoctorobject.getString(TAG_WORKINGDAYS);
						 System.out.println("coverage : "+ workingdays );
						 streetaddress = jsondoctorobject.getString(TAG_STREETADDRESS);
						 System.out.println("description : "+ streetaddress );
					     city = streetaddress + ", "+ jsondoctorobject.getString(TAG_CITY);
					     System.out.println("username : "+ city );
					     state = city + ","+jsondoctorobject.getString(TAG_STATE);
					     System.out.println("username : "+ state );
					     zipcode = state +" "+ jsondoctorobject.getString(TAG_ZIPCODE);
					     System.out.println("username : "+ zipcode );
					     doctorcontact = jsondoctorobject.getString(TAG_DOCTORCONTACT);
					     System.out.println("username : "+ doctorcontact );
					     
							// tmp hashmap for single route
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
							doctorlist.add(hmdoctorlist);
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
	            try{
	            	ListAdapter adapter = new SimpleAdapter(
	            			  ResultSearchByDoctorNameActivity.this, doctorlist,
	                          R.layout.eachdoctordetailssearchbydocnamelayout, new String[] { 
	            					  TAG_NAME,TAG_OFFICENAME,TAG_WORKINGHOURS,TAG_WORKINGDAYS, TAG_ZIPCODE,TAG_DOCTORCONTACT  }, 
	            					  new int[] { R.id.docname,  R.id.officename, R.id.workinghrs , R.id.workingdays
	            					   , R.id.zipcode,R.id.drcontact})
	            	  {
	            		  @Override
	            	        public View getView (int position, View convertView, ViewGroup parent)
	            	        {
	            	            View v = super.getView(position, convertView, parent);
	            	           
	            	             Button b=(Button)v.findViewById(R.id.button_listviewonmap);
	            	             b.setOnClickListener(new OnClickListener() {

	            	                @Override
	            	                public void onClick(View arg0) {
//	            	                	   HashMap<String, String> data=(HashMap<String, String>) getItemAtPosition(position);
//	            	    				   System.out.println("========================"+data);
//	            	    				   String docname=(String)data.get("Name");
	            	    				   
	            	                	/****map stuff***/
	            						LayoutInflater li = LayoutInflater.from(context);
	            						View promptsView = li.inflate(R.layout.routelistdialogsearchbydocnamelayout, null);
	            						
	            						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	            						
	            						// set prompts.xml to alertdialog builder
	            						alertDialogBuilder.setView(promptsView);
	            						
	            						// set dialog message
	            						alertDialogBuilder.setCancelable(false).setPositiveButton("Go",
	            							  new DialogInterface.OnClickListener() {
	            							    public void onClick(DialogInterface dialog,int id) {
	            							    	//goto page with list of routes
	            							    	 Intent findroutelist = new Intent(ResultSearchByDoctorNameActivity.this,ListOfRoutesSearchByDoctorNameActivity.class);     
	            						             startActivity(findroutelist);
	            								// get user input and set it to result
	            								// edit text
//	            								result.setText(userInput.getText());
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
	            						
	            						//get source and destination using autocomplete
	            						atvPlaces_source = (AutoCompleteTextView) promptsView.findViewById(R.id.etsourcesearchbydocname);
	            						atvPlaces_source.setThreshold(1);
//	            						atvPlaces_destination = (EditText) promptsView.findViewById(R.id.etdestination);
	            						atvPlaces_destination = (AutoCompleteTextView) promptsView.findViewById(R.id.etdestinationsearchbydocname);
	            						atvPlaces_destination.setText("3333 Quality Drive, Rancho Cordova, CA, United States");
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
	            							    			        //tempgetsource = (String) arg0.getItemAtPosition(arg2);
	            							    			      tempgetsource= atvPlaces_source.getText().toString();
	            							    }
	            							});
	            									
	            						atvPlaces_destination.setOnItemClickListener(new OnItemClickListener() {
	            							 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	            							     			      //  tempgetdestination =  (String) arg0.getItemAtPosition(arg2);
	            							     			    tempgetdestination= atvPlaces_destination.getText().toString();
	            							       // String starttext=arg0.getItemAtPosition(arg2);
	            							    }
	            							});
	            				
	            					}
	            	            });
	            	            
	            	             /**********producst and services view****/
	            	             Button bprod=(Button)v.findViewById(R.id.button_products);
	            	             bprod.setOnClickListener(new OnClickListener() {

		            	                @Override
		            	                public void onClick(View arg0) {
	            	  				   LayoutInflater li = LayoutInflater.from(context);
	            	  					View promptsView = li.inflate(R.layout.dialogservicesproductslayout, null);
	            	  					
	            	  					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	            	  					
	            	  					// set prompts.xml to alertdialog builder
	            	  					alertDialogBuilder.setView(promptsView);
	            	  					
	            	  					LinearLayout lm = (LinearLayout) promptsView.findViewById(R.id.linearservices);
//	            	  						 getservices( doctorname, lm);
	            	  						// Create TextView
	            	  						 for(int i=0; i<numservices;i++){
	            	  						TextView tv = new TextView(context);
	            	  						tv.setId(i);
	            	  						tv.setText("• "+ serviceslist[i]);
	            	  						lm.addView(tv);
	            	  						 }
	            	  						 
	            	  						 LinearLayout ll = (LinearLayout) promptsView.findViewById(R.id.linearproducts);
//	            	  						 getservices( doctorname, lm);
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
	          
//	            	    doctorslist.setListAdapter(adapter);
	            	    setListAdapter(adapter);
	          
	            	    	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} //async class ends here

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
			
			System.out.println(tempgetsource +"adapter for doctor name search tempsoure nd dest"+tempgetdestination);
					
		}
	} // parser task ends here

	/***** get services and products***/
	
	private class GetServices extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			 String GETSERVICES = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/getservicesatdoctors/docfirstname/Pooja%20Shah";
//			 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/Pooja/doctorlastname/Shah/state/CA";
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
//			 String GETSEARCHBYDOCNAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbydoctorname/doctorfirstname/Pooja/doctorlastname/Shah/state/CA";
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

}
