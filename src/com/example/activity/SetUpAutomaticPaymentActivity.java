package com.example.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newpercept.R;

public class SetUpAutomaticPaymentActivity extends Activity {

	public String TAG_STATUS = "status";
    public String TAG_ACCOUNTDETAILS = "accountdetails";
	public String accountdetails = "";
	EditText etfrequency;
	EditText etstartday;
	
	String frequency = "";
	String startday = "";
	String status = "";
	
	RadioGroup radiogroupautopayaccts;
	RadioButton radioselectedaccount;
	String selectedaccountdetails;
	JSONArray jsonarray;
	String[] accountslist ; 
	 int numberofradiobuttons ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setupautomaticpaymentlayout);
		
		radiogroupautopayaccts = (RadioGroup) findViewById(R.id.radiogrpaccts);
		
		new GetAutoPayAccountDetails().execute();
		etfrequency   = (EditText)findViewById(R.id.enterfrequency );	
		etstartday = (EditText)findViewById(R.id.enterstartday);
		
		
		Button buttondone = (Button) findViewById(R.id.btndone);
		buttondone.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	frequency = etfrequency.getText().toString();
	        	startday = etstartday.getText().toString();
	        	
	        	   int selectedacct = radiogroupautopayaccts.getCheckedRadioButtonId();
	        	   radioselectedaccount = (RadioButton) findViewById(selectedacct);
	        	   selectedaccountdetails = (String) radioselectedaccount.getText();
	        	   
	        	   new SetAutoPayAccount().execute();
//	        	setContentView(R.layout.homepagelayout);
	        
	        }
	  });
	}// oncreate ends here
	
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
	
	private void getpaymentaccounts(int numButtons) {
		
		// get reference to radio group in layout
		radiogroupautopayaccts = (RadioGroup) findViewById(R.id.radiogrpaccts);
    	// layout params to use when adding each radio button
        LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        // add 20 radio buttons to the group
        for (int i = 0; i < numButtons; i++) {
        	RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText( accountslist[i]);
            radiogroupautopayaccts.addView(rdbtn, layoutParams);
        }
        
	} //getpaymentaccts finish

   private class GetAutoPayAccountDetails extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/

		@Override
		protected Void doInBackground(Void... arg0) {
			String GETPAYMYBILL = "http://10.0.3.2:8080/NewPerceptServer/service/paymybill/getpaymybilldetails/username/Tom";

			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrpaymybill = sh.makeServiceCall(GETPAYMYBILL,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by billpay----> ", "> " + jsonStrpaymybill);

			if (jsonStrpaymybill != null) {
				try {
					 jsonarray = new JSONArray(jsonStrpaymybill);
					 System.out.println("lllllklkkkkkkkkkkkkl---------"+jsonarray.length());
					 numberofradiobuttons = jsonarray.length();
					 System.out.println("button#====>"+numberofradiobuttons);
					 accountslist = new String[numberofradiobuttons];
						for(int r = 0; r < jsonarray.length(); r++){
							
							JSONObject jsonObj = jsonarray.getJSONObject(r);
						    
						 accountdetails = jsonObj.getString(TAG_ACCOUNTDETAILS);
						 System.out.println("accountdetails : "+ accountdetails );	
				
							// adding contact to contact list
							accountslist[r] = accountdetails;
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
				 getpaymentaccounts(numberofradiobuttons);
		        }
			
		} // get office name result async class ends here

   private class SetAutoPayAccount extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
		String encodedfreq = encodeforurl(frequency);
		String encodedacct = encodeforurl(selectedaccountdetails);
//		String SETAUTOPAYACCT = "http://10.0.3.2:8080/NewPerceptServer/service/autopayment/setautopayaccount/frequency/"+frequency+
//"/startday/"+startday+"/autopayaccount/"+selectedaccountdetails;
		String SETAUTOPAYACCT = "http://10.0.3.2:8080/NewPerceptServer/service/autopayment/setautopayaccount/frequency/"
				+encodedfreq+"/startday/"+startday+"/autopayaccount/"+encodedacct;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(SETAUTOPAYACCT,ServiceHandler.GET);

		Log.d("Response: logs for autopay update----> ", "> " + jsonstr);

		if (jsonstr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonstr);
				
			     status = jsonObj.getString(TAG_STATUS);
				 System.out.println("status : "+ status );
				 
					}
			catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}

		return null;
	} //on background ends here

		
		 @Override
	        protected void onPostExecute(Void result) {
			 if (status.equalsIgnoreCase("success")) {
				 Intent gotoautopay = new Intent(SetUpAutomaticPaymentActivity.this, AutomaticPaymentsActivity.class);
					startActivity(gotoautopay);
					System.out.println(status + "==============>autopay set here");
					//pDialog.dismiss();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Account cannot be setup for AutoPay.Please contact customer service", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get office name result async class ends here



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_up_automatic_payment, menu);
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
