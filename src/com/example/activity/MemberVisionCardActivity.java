package com.example.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newpercept.R;

public class MemberVisionCardActivity extends ActionBarActivity {

public String TAG_USERNAME = "username";
public String TAG_COVERAGETYPE = "coveragetype";
public String TAG_DOCTORNETWORK = "doctornetwork";
public String TAG_COPAYEXAM = "copayexam";
public String TAG_COPAYMATERIALS = "copaymaterials";
	
	public String username = "";
	public String coveragetype = "";
	public String doctornetwork = "";
	public String copayexam = "";
	public String copaymaterials = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.membervisioncardlayout);
		getActionBar().setHomeButtonEnabled(true);
		
		//Back button navigates to home page
		 Button buttonmembervisioncard = (Button) findViewById(R.id.mvcbutton_back);
			buttonmembervisioncard.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							// goto home page after clicking
							Intent gotohomefrommvc = new Intent(MemberVisionCardActivity.this, HomeActivity.class);
							startActivity(gotohomefrommvc);
						}
					});
			
			new GetResultsSearchByOfficeName().execute();
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.member_vision_card, menu);
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

	
	public void getmembervisioncarddetails(String username,String coveragetype,String doctornetwork, String copayexam, String copaymaterials ){
		 LinearLayout lm = (LinearLayout) findViewById(R.id.linearmembervisioncard);
		// Create TextView
		TextView tvusername = new TextView(this);
		tvusername.setText("Member : "+ username);
		lm.addView(tvusername);
		TextView tvcoveragetype = new TextView(this);
		tvcoveragetype.setText("Coverage Type : "+ coveragetype );
		lm.addView(tvcoveragetype);
		TextView tvdoctornetwork = new TextView(this);
		tvdoctornetwork.setText("Doctor Network : "+ doctornetwork );
		lm.addView(tvdoctornetwork);
		TextView tvcopayexam = new TextView(this);
		tvcopayexam.setText("Copay Exam : "+ copayexam );
		lm.addView(tvcopayexam);
		TextView tvcopaymaterials = new TextView(this);
		tvcopaymaterials.setText( "Copay Materials : "+ copaymaterials);
		lm.addView(tvcopaymaterials);
		
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
			 String GETMEMBERVISIONCARDURL = "http://10.0.3.2:8080/NewPerceptServer/service/membervisioncard/username/Tom";
//             String GETSEARCHBYOFFICENAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbyofficename/officename/TestClinic/city/Folsom/state/CA";
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrmembervisioncarddetails = sh.makeServiceCall(GETMEMBERVISIONCARDURL,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by office----> ", "> " + jsonStrmembervisioncarddetails);

			if (jsonStrmembervisioncarddetails != null) {
				try {
					
					JSONObject jsonObj = new JSONObject(jsonStrmembervisioncarddetails);
					
					     username = jsonObj.getString(TAG_USERNAME);
					     System.out.println("firstname : "+ username );
					     coveragetype = jsonObj.getString(TAG_COVERAGETYPE);
						 System.out.println("lastname : "+ coveragetype );				
						 doctornetwork = jsonObj.getString(TAG_DOCTORNETWORK);
						 System.out.println("office : "+ doctornetwork );
						 copayexam = jsonObj.getString(TAG_COPAYEXAM);
						 System.out.println("hrs : "+ copayexam );
						 copaymaterials = jsonObj.getString(TAG_COPAYMATERIALS);
						 System.out.println("coverage : "+ copaymaterials );
						 
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
//	            	  System.out.println("before listadapter---------------->");
	            	getmembervisioncarddetails( username, coveragetype, doctornetwork,  copayexam,  copaymaterials );
	            	  System.out.println("data here ->1 "+username + "->2 "+coveragetype+"->3" +doctornetwork);
	            System.out.println("After set listadapter----------------->");
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} // get membervision card details async class ends here

} //activity ends here
