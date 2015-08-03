package com.example.activity;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.newpercept.R;

public class AutomaticPaymentsActivity extends ActionBarActivity {

	public String TAG_AUTOPAYACCOUNTNUMBER = "autopayaccountnumber";
	public String TAG_AUTOPAYFREQUENCY = "autopayfrequency";
	public String TAG_AUTOPAYSTARTDAY = "autopaystartday";
	
	public String autopayaccountnumber = "";
	public String autopayfrequency = "";
	public String autopaystartday = "";
	
	TextView tvacctnumber ;
	TextView tvfrequncy ;
	TextView tvrecurringdate ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.automaticpaymentslayout);
		getActionBar().setHomeButtonEnabled(true);
		
		tvacctnumber = (TextView) findViewById(R.id.tvaccnumber);
		tvfrequncy = (TextView) findViewById(R.id.tvfrequency);
		tvrecurringdate = (TextView) findViewById(R.id.tvrecurringdate);
		   
		Button buttoncancel = (Button) findViewById(R.id.button_cancel);
		buttoncancel.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
//		        	setContentView(R.layout.homepagelayout);
		        	 tvacctnumber.setText("");
			          tvfrequncy.setText("");
			           tvrecurringdate.setText("");
		        }
		  });
		Button buttonsetupautopay = (Button) findViewById(R.id.button_setupautopay);
		buttonsetupautopay.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	Intent gotosetupautopy = new Intent(AutomaticPaymentsActivity.this, SetUpAutomaticPaymentActivity.class);
				startActivity(gotosetupautopy);
	        }
	  });
		
		new GetAutopaymentDetails().execute();
        
	}// oncreate ends here

	private class GetAutopaymentDetails extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

				@Override
				protected Void doInBackground(Void... arg0) {
					String GETAUTOPAYDETAILS = "http://10.0.3.2:8080/NewPerceptServer/service/autopayment/getautopaymentdetails/username/Tom";

					// Creating service handler class instance
					ServiceHandler sh = new ServiceHandler();

					// Making a request to url and getting response
					String jsonstr = sh.makeServiceCall(GETAUTOPAYDETAILS,ServiceHandler.GET);

					Log.d("Response: logs for autopay----> ", "> " + jsonstr);

					if (jsonstr != null) {
						
						try {
							JSONObject jsonObj = new JSONObject(jsonstr);
						
							autopayaccountnumber = jsonObj.getString(TAG_AUTOPAYACCOUNTNUMBER);
							 System.out.println("status : "+ autopayaccountnumber );
							 autopayfrequency = jsonObj.getString(TAG_AUTOPAYFREQUENCY);
							 System.out.println("status : "+ autopayfrequency );
							 autopaystartday = jsonObj.getString(TAG_AUTOPAYSTARTDAY);
							 System.out.println("status : "+ autopaystartday );
							 
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
	                
			}else {
						Log.e("ServiceHandler", "Couldn't get any data from the url");
					}

					return null;
				} //on background ends here
		
		 @Override
	        protected void onPostExecute(Void result) {
			 tvacctnumber.setText("Account Number : "+ autopayaccountnumber);
	          tvfrequncy.setText("Frequency : "+autopayfrequency);
	           tvrecurringdate.setText("Recurring Date : "+autopaystartday);
	          
//			 gettvforautopaydetails( autopayaccountnumber,  autopayfrequency,  autopaystartday);
	        }
		
	} // get office name result async class ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.automatic_payments, menu);
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

}
