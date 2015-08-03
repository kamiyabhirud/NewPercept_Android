package com.example.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

public class PaymentAccountsActivity extends Activity {
	public String TAG_STATUS = "status";
	public String TAG_ACCOUNTDETAILS = "accountdetails";
	EditText edittextexpirymonth;
	EditText edittextexpiryyear;
	
	public String expirymonth = "";
	public String expiryyear = "";
	final Context context = this;
	public String status = "status";
	public String accountdetails = "";
	RadioGroup radiogrpacctlist;
	RadioButton radioselectedacct;
	String selectedaccountdetails;
	JSONArray jsonarray;
	String[] accountslist ; 
	int numberofradiobuttons ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paymentaccountslayout);
		
		getActionBar().setHomeButtonEnabled(true);
		
		radiogrpacctlist = (RadioGroup) findViewById(R.id.radiogrpmypayacct);
		new GetAccountDetails().execute();
			
		Button buttondelete = (Button) findViewById(R.id.btndelete);
		buttondelete.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	int selectedacct = radiogrpacctlist.getCheckedRadioButtonId();
	   		    radioselectedacct = (RadioButton) findViewById(selectedacct);
	        	 selectedaccountdetails = (String) radioselectedacct.getText();
	        	 new DeleteAccount().execute();
	        }
	  });
		
		Button buttonedit = (Button) findViewById(R.id.btnedit);
		buttonedit.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	int selectedacctforedit = radiogrpacctlist.getCheckedRadioButtonId();
	   		    radioselectedacct = (RadioButton) findViewById(selectedacctforedit);
	        	 selectedaccountdetails = (String) radioselectedacct.getText();
	        	 
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.editaccountdialoglayout, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
				
				// set dialog message
				alertDialogBuilder.setCancelable(false).setPositiveButton("Edit",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {  	
							new EditAccount().execute();
						
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
				
				edittextexpirymonth   = (EditText) promptsView.findViewById(R.id.enterexpmonth );
		    	expirymonth = edittextexpirymonth.getText().toString();
		    	edittextexpiryyear = (EditText) promptsView.findViewById(R.id.enterexpyear);
				expiryyear = edittextexpiryyear.getText().toString();  
				
	        }
	  });
		
		Button buttonaddnewacct = (Button) findViewById(R.id.btnaddnewaccount);
		buttonaddnewacct.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	 Intent gotoautopay = new Intent(PaymentAccountsActivity.this, AddNewAccountActivity.class);
					startActivity(gotoautopay);
	        }
	  });
	}//on create ends here
	
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
		radiogrpacctlist = (RadioGroup) findViewById(R.id.radiogrpmypayacct);
    	// layout params to use when adding each radio button
        LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        // add 20 radio buttons to the group
        for (int i = 0; i < numButtons; i++) {
        	RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText( accountslist[i]);
            radiogrpacctlist.addView(rdbtn, layoutParams);
        }
        
	} 
	private class GetAccountDetails extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/

		@Override
		protected Void doInBackground(Void... arg0) {
			String GETACCOUNTNUM = "http://10.0.3.2:8080/NewPerceptServer/service/paymybill/getpaymybilldetails/username/Tom";
             //above webservice used for paymybill to retireve account numbers
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStrpaymybill = sh.makeServiceCall(GETACCOUNTNUM,ServiceHandler.GET);

			Log.d("Response: logs for SEARCH by billpay----> ", "> " + jsonStrpaymybill);

			if (jsonStrpaymybill != null) {
				try {
					 jsonarray = new JSONArray(jsonStrpaymybill);
					 
					 numberofradiobuttons = jsonarray.length();
					 
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
    private class DeleteAccount extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/

		@Override
		protected Void doInBackground(Void... arg0) {

			String encodedacct = encodeforurl(selectedaccountdetails);
//			String SETAUTOPAYACCT = "http://10.0.3.2:8080/NewPerceptServer/service/autopayment/setautopayaccount/frequency/"+frequency+
	//"/startday/"+startday+"/autopayaccount/"+selectedaccountdetails;
			String SETAUTOPAYACCT = "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/deleteaccount/acctnumber/"+encodedacct;
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
					 Intent gotoautopay = new Intent(PaymentAccountsActivity.this, MyAccountHomeActivity.class);
						startActivity(gotoautopay);
						System.out.println(status + "==============>autopay set here");
						//pDialog.dismiss();
			
				} else {	
					//pDialog.dismiss();
					Toast.makeText(getApplicationContext(),
							"Account cannot be deleted.Please contact customer service", Toast.LENGTH_LONG).show();
				}

				
		        }
			
		} // get office name result async class ends here

    private class EditAccount extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {

		String encodedacct = encodeforurl(selectedaccountdetails);
		String EDITACCOUNTURL= "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/editaccount/expirationmonth/11/expirationyear/17/acctnumber/visa111";
//		String EDITACCOUNTURL= "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/editaccount/expirationmonth/"
//        +expirymonth+"/expirationyear/"+expiryyear+"/acctnumber/"+encodedacct;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(EDITACCOUNTURL,ServiceHandler.GET);

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
				 Toast.makeText(getApplicationContext(),
							"Account Edited", Toast.LENGTH_LONG).show();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Account cannot be deleted.Please contact customer service", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get office name result async class ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment_accounts, menu);
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
