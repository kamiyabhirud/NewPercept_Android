//package com.example.activity;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.ListActivity;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.ListAdapter;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//
//import com.example.newpercept.R;
//
//public class WithInNetworkSpecialityDiabeticEyeCare extends ActionBarActivity {
//
//	String TAG_ELIGIBILITY = "specialitydiabetic_eligibility";
//	String TAG_FREQUENCY = "specialitydiabetic_frequency";
//	String TAG_COPAY = "specialitydiabetic_copay";
//	String TAG_COVERAGE = "specialitydiabetic_coverage";
//	String TAG_DESCRIPTION = "specialitydiabetic_description";
//	String TAG_USERNAME = "username";
//	
//	String username = "";
//	String eligibility = "";
//	String frequency = "";
//	String copay = "";
//	String coverage = "";
//	String description = "";
//	
////	ArrayList<HashMap<String, String>> benefitseyecarelist =  new ArrayList<HashMap<String, String>>();
//	
//	
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.withinnetworkspecialitydiabeticeyecarelayout);
//		
//		new GetSpecialityDiabeticBenefits().execute();
//		
////		final LinearLayout lm = (LinearLayout) findViewById(R.id.linearspeciality);
//		
//		//create for loop for textview
//		// Create LinearLayout
//			LinearLayout ll = new LinearLayout(this);
//			ll.setOrientation(LinearLayout.HORIZONTAL);
//			
////			setspeciality(lm,eligibility, frequency, copay, coverage, description);
//			
//			// Create TextView
////			TextView price = new TextView(this);
////			price.setText("  $"+j+"     ");
////			ll.addView(price);
//		
//			   //Add button to LinearLayout defined in XML
////			    lm.addView(ll);  
//	    
//	} // oncreate ends here
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(
//				R.menu.with_in_network_speciality_diabetic_eye_care, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//	
//	public void setspeciality(String eligibility,String frequency,String copay,String coverage,String description){
//		 LinearLayout lm = (LinearLayout) findViewById(R.id.linearspeciality);
//		// Create TextView
//		TextView tveligibility = new TextView(this);
//		tveligibility.setText(" Eligibility : "+ eligibility );
//		lm.addView(tveligibility);
////		lm.addView(ll);
//		TextView tvfrequency = new TextView(this);
//		tvfrequency.setText(" Frequency : "+ frequency );
//		lm.addView(tvfrequency);
////		lm.addView(ll);
//		TextView tvcopay = new TextView(this);
//		tvcopay.setText(" Copay : "+ copay );
//		lm.addView(tvcopay);
////		lm.addView(ll);
//		TextView tvcoverage = new TextView(this);
//		tvcoverage.setText(" Coverage : "+ coverage );
//		lm.addView(tvcoverage);
//		System.out.println("coverage ================="+coverage);
////		lm.addView(ll);
//		TextView tvdescription = new TextView(this);
//		tvdescription.setText(" Description : "+ description );
//		lm.addView(tvdescription);
////		lm.addView(ll);
//		
//		}
//
//	private class GetSpecialityDiabeticBenefits extends AsyncTask<Void, Void, Void> {
//
//	
//
//		/******** fetches and parse json data and add to list ***********/
//		@Override
//		protected Void doInBackground(Void... arg0) {
//			 String GETDIABETICURL = "http://10.0.3.2:8080/NewPerceptServer/service/benefitswithinnetworkspecialityeyecare/getbenefitswithinnetworkspecialityeyecare/username/Tom";
//			
//			// Creating service handler class instance
//			ServiceHandler sh = new ServiceHandler();
//
//			// Making a request to url and getting response
//			String jsonStrBenefitsDiabetic = sh.makeServiceCall(GETDIABETICURL,ServiceHandler.GET);
//
//			Log.d("Response: logs for benefits----> ", "> " + jsonStrBenefitsDiabetic);
//
//			if (jsonStrBenefitsDiabetic != null) {
//				try {
//					
//					JSONObject jsonObj = new JSONObject(jsonStrBenefitsDiabetic);
//	
//						 username = jsonObj.getString(TAG_USERNAME);
//					     System.out.println("username : "+ username );
//						 eligibility = jsonObj.getString(TAG_ELIGIBILITY);
//						 System.out.println("eligibility : "+ eligibility );				
//						 frequency = jsonObj.getString(TAG_FREQUENCY);
//						 System.out.println("frequency : "+ frequency );
//						 copay = jsonObj.getString(TAG_COPAY);
//						 System.out.println("copay : "+ copay );
//						 coverage = jsonObj.getString(TAG_COVERAGE);
//						 System.out.println("coverage : "+ coverage );
//						 description = jsonObj.getString(TAG_DESCRIPTION);
//						 System.out.println("description : "+ description );
//									
//					// adding each child node to HashMap key => value
//							System.out.println("eligibility hm : "+ eligibility );
//					
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			} else {
//				Log.e("ServiceHandler", "Couldn't get any data from the url");
//			}
//
//			return null;
//		} //on background ends here
//		
//		 @Override
//	        protected void onPostExecute(Void result) {
//	            try{
//	            	  System.out.println("before listadapter---------------->");
//	            	  setspeciality(eligibility, frequency, copay, coverage, description);
//           System.out.println("After set listadapter----------------->");
//	            } catch(Exception e){
//	            	e.printStackTrace();
//	            }
//	        }
//		
//	} //async class ends here
//}
