package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.newpercept.R;

public class PaymentHistoryActivity extends ListActivity {

    public String TAG_USERNAME = "username";
    public String TAG_CUSTOMERNUMBER = "customernumber";
    public String TAG_PASTDUEAMOUNT = "pastdueamount";
    public String TAG_AMOUNTDUE = "amountdue";
    public String TAG_REMAININGBALANCE = "remainingbalance";
    public String TAG_DATE = "date";
    public String TAG_DOCTORTYPE = "doctortype";
    public String TAG_TRANSACTIONNUMBER = "transactionnumber";
    public String TAG_CREDIT = "credit";
    public String TAG_DEBIT = "debit";
    public String TAG_NAME = "name";
    public String TAG_DESCRIPTION = "description";
    public String DATE = "Date";
    public String DOCTORTYPE = "Doctor Type";
    public String TRANSACTIONNUMBER = "Transaction Number";
    public String DESCRIPTION = "Description";
    public String DEBIT = "Debit";
    public String CREDIT = "Credit";
    
	public String username = "";
	public String customernumber = "";
	public String pastdueamount = "";
	public String amountdue = "";
	public String remainingbalance = "";
	public String date = "";
	public String doctortype = "";
	public String transactionnumber = "";
	public String credit = "";
	public String debit = "";
	public String name = "";
	public String description = "";
	
	final Context context = this;
	
	JSONArray jsonarray;
	ArrayList<HashMap<String, String>> paymenthistorylist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paymenthistorylayout);
		
		getActionBar().setHomeButtonEnabled(true);
		
		paymenthistorylist =  new ArrayList<HashMap<String, String>>();
		new GetPaymentHistory().execute();
		
		Button buttonpaynow = (Button) findViewById(R.id.button_paynow);
		buttonpaynow.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						 Intent gotopaymybillact = new Intent(PaymentHistoryActivity.this,PayMyBillActivity.class);     
			             startActivity(gotopaymybillact);					
	}  	}); 
		Button buttonautopay = (Button) findViewById(R.id.button_autopay);
		buttonautopay.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						 Intent gotoautopay = new Intent(PaymentHistoryActivity.this,AutomaticPaymentsActivity.class);     
			             startActivity(gotoautopay);					
	}  	}); 
		} //oncreate ends here 
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment_history, menu);
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

	public void getpaymenthistorydetails(String name,String customernumber,String pastdueamount, String amountdue, String remainingbalance ){
		 LinearLayout lm = (LinearLayout) findViewById(R.id.linearpaymenthistory);
		// Create TextView
		TextView tvname = new TextView(this);
		tvname.setText("Member Name : Tom  ");
		lm.addView(tvname);
		TextView tvcustomernumber = new TextView(this);
		tvcustomernumber.setText("Customer Number : "+ customernumber );
		lm.addView(tvcustomernumber);
		TextView tvpastdueamount = new TextView(this);
		tvpastdueamount.setText("Past Due Amount : $"+ pastdueamount );
		lm.addView(tvpastdueamount);
		TextView tvamountdue = new TextView(this);
		tvamountdue.setText("Amount Due : $"+ amountdue );
		lm.addView(tvamountdue);
		TextView tvremainingbalance = new TextView(this);
		tvremainingbalance.setText( "Remaining Balance : $"+ remainingbalance);
		lm.addView(tvremainingbalance);
		
		}
	
	private class GetPaymentHistory extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/
		@Override
		protected Void doInBackground(Void... arg0) {
			 String GETPAYMENTHISTORYURL = "http://10.0.3.2:8080/NewPerceptServer/service/paymenthistory/getpaymenthistory/username/Tom";
//             String GETSEARCHBYOFFICENAMEURL = "http://10.0.3.2:8080/NewPerceptServer/service/findadoctorsearch/searchbyofficename/officename/TestClinic/city/Folsom/state/CA";
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrpaymenthistory = sh.makeServiceCall(GETPAYMENTHISTORYURL,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by office----> ", "> " + jsonStrpaymenthistory);

			if (jsonStrpaymenthistory != null) {
				try {
					 jsonarray = new JSONArray(jsonStrpaymenthistory);
						for(int r = 0; r < jsonarray.length(); r++){
							JSONObject jsonObj = jsonarray.getJSONObject(r);
//					JSONObject jsonObj = new JSONObject(jsonStrsearchbyofficename);
						    
					     customernumber = jsonObj.getString(TAG_CUSTOMERNUMBER);
						 System.out.println("lastname : "+ customernumber );				
						 pastdueamount = jsonObj.getString(TAG_PASTDUEAMOUNT);
						 System.out.println("office : "+ pastdueamount );
						 amountdue = jsonObj.getString(TAG_AMOUNTDUE);
						 System.out.println("hrs : "+ amountdue );
						 remainingbalance = jsonObj.getString(TAG_REMAININGBALANCE);
						 System.out.println("coverage : "+ remainingbalance );
						 date = DATE + " : "+jsonObj.getString(TAG_DATE);
						 System.out.println("description : "+ date );
						 doctortype = DOCTORTYPE +" : "+ jsonObj.getString(TAG_DOCTORTYPE);
					     System.out.println("username : "+ doctortype );
					     transactionnumber = TRANSACTIONNUMBER + " : "+ jsonObj.getString(TAG_TRANSACTIONNUMBER);
					     System.out.println("username : "+ transactionnumber );
					     credit = CREDIT +" : "+jsonObj.getString(TAG_CREDIT);
					     System.out.println("username : "+ credit );
					     debit = DEBIT +" : "+jsonObj.getString(TAG_DEBIT);
					     System.out.println("username : "+ debit );
					     name = jsonObj.getString(TAG_NAME);
						 System.out.println("lastname : "+ name );
						 description = DESCRIPTION + " : "+jsonObj.getString(TAG_DESCRIPTION);
						 System.out.println("lastname : "+ description );
					     
					     HashMap<String, String> hmpaymenthistory = new HashMap<String, String>();
			
							hmpaymenthistory.put(TAG_DOCTORTYPE,doctortype);
							hmpaymenthistory.put(TAG_DATE,date);
							hmpaymenthistory.put(TAG_TRANSACTIONNUMBER,transactionnumber);
							hmpaymenthistory.put(TAG_CREDIT,credit);
							hmpaymenthistory.put(TAG_DEBIT,debit);
							hmpaymenthistory.put(TAG_DESCRIPTION,description);

							// adding contact to contact list
							paymenthistorylist.add(hmpaymenthistory);
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
	            			  PaymentHistoryActivity.this, paymenthistorylist,
	                          R.layout.eachlistitempaymenthistory, new String[] { 
	            					  TAG_DOCTORTYPE,TAG_DATE,TAG_TRANSACTIONNUMBER,TAG_CREDIT, TAG_DEBIT,TAG_DESCRIPTION  }, 
	            					  new int[] { R.id.tvdoctype,  R.id.tvdate, R.id.tvtrxnumber , R.id.tvcredit
	            					   , R.id.tvdebit,R.id.tvdesc});
	            	  getpaymenthistorydetails(name,customernumber,pastdueamount, amountdue, remainingbalance );
	            	  
	            	     setListAdapter(adapter);
	            	  System.out.println("After set listadapter----------------->");
	            } catch(Exception e){
	            	e.printStackTrace();
	            }
	        }
		
	} // get office name result async class ends here

}
