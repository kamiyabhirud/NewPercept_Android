package com.example.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.newpercept.R;

public class UserProfileActivity extends Activity {

	public String TAG_USERNAME = "username";
	public String TAG_FIRSTNAME = "firstname";
	public String TAG_LASTNAME = "lastname";
	public String TAG_EMAIL = "email";
	public String TAG_PHONENUMBER = "phonenumber";
	public String TAG_PREFERREDCONTACT = "preferredcontact";
	public String TAG_MAILINGADDRESS = "mailingaddress";
	
	public String username = "";
	public String firstname = "";
	public String lastname = "";
	public String email = "";
	public String phonenumber = "";
	public String preferredcontact = "";
	public String mailingaddress = "";
	final Context context = this;
	
	  private RadioGroup radiobuttongroup;
	  private RadioButton radiopreferredcontact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofilelayout);
		getActionBar().setHomeButtonEnabled(true);
		
		new GetUserProfile().execute();
		
		 //Back button navigates to home page
		Button buttonback = (Button) findViewById(R.id.buttonback);
		buttonback.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							// goto home page after clicking
							Intent gotohomefrommyaccount = new Intent(UserProfileActivity.this, HomeActivity.class);
							startActivity(gotohomefrommyaccount);
						}	
					}); 

			addListenerOnButton();
	}

	public void addListenerOnButton() {

		radiobuttongroup = (RadioGroup) findViewById(R.id.radiopreferredcontact);
		Button buttonupdate = (Button) findViewById(R.id.buttonupdate);

		buttonupdate.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {

	                // get selected radio button from radioGroup
	            int selectedId = radiobuttongroup.getCheckedRadioButtonId();

	            // find the radiobutton by returned id
	            radiopreferredcontact = (RadioButton) findViewById(selectedId);

//	            Toast.makeText(MyAccounthomeActivity.this,
//	            		radiopreferredcontact.getText(), Toast.LENGTH_SHORT).show();
	            
	            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				 
							// set title
							alertDialogBuilder.setTitle("Preferred Contact Updated To");
							// set dialog message
							alertDialogBuilder
								.setMessage(""+radiopreferredcontact.getText())
								.setCancelable(false)
								.setPositiveButton("OK",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
										// current activity
//										MyAccounthomeActivity.this.finish();
										dialog.cancel();
									}
								  });
//								.setNegativeButton("No",new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,int id) {
//										// if this button is clicked, just close
//										// the dialog box and do nothing
//										dialog.cancel();
//									}
//								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show();
				

	        }

	    });

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

	
	/**********Url encoder *******/
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

	public void getuserdetails(String username, String firstname,String lastname,String email,String phonenumber,String mailingaddress){
		 LinearLayout lm = (LinearLayout) findViewById(R.id.linearuserdetails);
		// Create TextView
		TextView tvusername = new TextView(this);
		tvusername.setText("Username : "+ username);
		tvusername.setTypeface(null, Typeface.BOLD);
		lm.addView(tvusername);
		TextView tvname = new TextView(this);
		tvname.setText("Name : "+ firstname+ " "+ lastname);
		lm.addView(tvname);
		TextView tvemail = new TextView(this);
		tvemail.setText("Email : "+ email);
		lm.addView(tvemail);
		TextView tvphonenumber = new TextView(this);
		tvphonenumber.setText("Contact Number : "+ phonenumber);
		lm.addView(tvphonenumber);
		TextView tvmailingaddress = new TextView(this);
		tvmailingaddress.setText("Mailing address : "+ mailingaddress);
		lm.addView(tvmailingaddress);
		
		}
	
	private class GetUserProfile extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			 String GETUSERPROFILEURL = "http://10.0.3.2:8080/NewPerceptServer/service/userprofile/getuserprofile/username/Tom";
//             String GETSEARCHBYOFFICENAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbyofficename/officename/TestClinic/city/Folsom/state/CA";
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsongetuserprofile = sh.makeServiceCall(GETUSERPROFILEURL,ServiceHandler.GET);

			Log.d("Response: logs for userprofile ---> ", "> " + jsongetuserprofile);

			if (jsongetuserprofile != null) {
				try {
					
					JSONObject jsonObj = new JSONObject(jsongetuserprofile);

				    	 username = jsonObj.getString(TAG_USERNAME);
				         System.out.println("firstname : "+ username );
					     firstname = jsonObj.getString(TAG_FIRSTNAME);
					     System.out.println("firstname : "+ firstname );
					     lastname = jsonObj.getString(TAG_LASTNAME);
						 System.out.println("lastname : "+ lastname );				
						 email = jsonObj.getString(TAG_EMAIL);
						 System.out.println("office : "+ email );
						 phonenumber = jsonObj.getString(TAG_PHONENUMBER);
						 System.out.println("hrs : "+ phonenumber );
						 preferredcontact = jsonObj.getString(TAG_PREFERREDCONTACT);
						 System.out.println("coverage : "+ preferredcontact );
						 mailingaddress = jsonObj.getString(TAG_MAILINGADDRESS);
						 System.out.println("description : "+ mailingaddress );
						 
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
	            	  getuserdetails(username, firstname,lastname, email, phonenumber,mailingaddress);
	            	  
//	            	  System.out.println("data here ->1 "+getofficename + "->2 "+getstate+"->3" +getcity);
	            System.out.println("After set listadapter----------------->");
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} // get office name result async class ends here

}
