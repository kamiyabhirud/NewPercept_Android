package com.example.activity;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newpercept.R;

public class PayMyBillActivity extends Activity {
	public String TAG_REMAININGBALANCE = "remainingbalance";
	public String TAG_AMOUNTDUE = "amountdue";
	public String TAG_PASTAMOUNTDUE = "pastdueamount";
	public String TAG_ACCOUNTDETAILS = "accountdetails";
	public String TAG_STATUS = "status";
	public String status = "";
	public  String remainingbalance ;
	public  String amountdue ;
	public  String pastdueamount ;
	public String accountdetails = "";
	JSONArray jsonarray;
	String[] accountslist ; 
	 int numberofradiobuttons ;
	
	RadioGroup radiogrouppaymentamt;
	RadioGroup radiogrouppaymentaccts;
	RadioButton radioremainingbalance;
	RadioButton radioamountdue;
	RadioButton radiopastdueamount;
	RadioButton radioselectedamount;
	RadioButton radioselectedaccount;
	
	public static String selectedamount; //for paypal
     String selectedaccount;
     String selectedaccttype;
     

	
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paymybilllayout);
		getActionBar().setHomeButtonEnabled(true);
		
		radiogrouppaymentamt = (RadioGroup) findViewById(R.id.radiopaymentamts);
		radiogrouppaymentaccts = (RadioGroup) findViewById(R.id.radiopaymentaccounts);
		radioremainingbalance = (RadioButton) findViewById(R.id.radioremainingbalance);
		radioamountdue = (RadioButton) findViewById(R.id.radioamountdue);
		radiopastdueamount = (RadioButton) findViewById(R.id.radiopastdueamount);
		
		  new GetPayMyBill().execute();
		  
		  Button buttonpay = (Button) findViewById(R.id.btnpay);
		  buttonpay.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {

		                // get selected radio button from radioGroup
		            int selectedamt = radiogrouppaymentamt.getCheckedRadioButtonId();
		            int selectedacct = radiogrouppaymentaccts.getCheckedRadioButtonId();

		            // find the radiobutton by returned id
		            radioselectedamount = (RadioButton) findViewById(selectedamt);
		            radioselectedaccount = (RadioButton) findViewById(selectedacct);
 
		            String amttype = (String) radioselectedamount.getText();
		            if(amttype.contains("Remaining Balance")){
		            	selectedaccttype = "remainingbalance";
		            	selectedamount = remainingbalance;
		            }else  if(amttype.contains("Amount Due")){
		            	selectedaccttype = "amountdue";
		            	selectedamount = amountdue;
		            }else  if(amttype.contains("Past Due")){
		            	selectedamount = pastdueamount;
		            	selectedaccttype = "pastdueamount";
		            }
		            
		            
		           
//		            Toast.makeText(MyAccounthomeActivity.this,
//		            		radiopreferredcontact.getText(), Toast.LENGTH_SHORT).show();
		            
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					 
								// set title
								alertDialogBuilder.setTitle("Payment confirmation");
								// set dialog message
								alertDialogBuilder
									.setMessage("Confirm amount : "+radioselectedamount.getText()+" from account : "+radioselectedaccount.getText())
									.setCancelable(false)
									.setPositiveButton("OK",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											//call another webservice to update amount
											new MakePayment().execute();
											dialog.cancel();
										}
									  })
									.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, just close
											// the dialog box and do nothing
											dialog.cancel();
										}
									});
					 
									// create alert dialog
									AlertDialog alertDialog = alertDialogBuilder.create();
					 
									// show it
									alertDialog.show();
			        }
		    });

		  Button buttonaddnewacct = (Button) findViewById(R.id.btnaddacct);
		  buttonaddnewacct.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	Intent gotoaddnewacct = new Intent(PayMyBillActivity.this, AddNewAccountActivity.class);
					startActivity(gotoaddnewacct);
		        }
		  });
		  
		  Button buttonpaypal = (Button) findViewById(R.id.btnpaybypaypal);
		  buttonpaypal.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	// get selected radio button from radioGroup
		            int selectedamt = radiogrouppaymentamt.getCheckedRadioButtonId();

		            // find the radiobutton by returned id
		            radioselectedamount = (RadioButton) findViewById(selectedamt);
		           
		            String amttype = (String) radioselectedamount.getText();
		            if(amttype.contains("Remaining Balance")){
		            	selectedaccttype = "remainingbalance";
		            }else  if(amttype.contains("Amount Due")){
		            	selectedaccttype = "amountdue";
		            }else  if(amttype.contains("Past Due")){
		            	selectedaccttype = "pastdueamount";
		            }
		            
		        	Intent gotopaypal = new Intent(PayMyBillActivity.this, SimpleDemo.class);
					startActivity(gotopaypal);
					new MakePaymentForPayPal().execute();
		        }
		  });

	}//oncreate ends here
	
	private void getpaymentaccounts(int numButtons) {
 
		radioremainingbalance.setText("Remaining Balance : $"+remainingbalance);
		radioamountdue.setText("Amount Due : $"+amountdue);
		radiopastdueamount.setText("Past Due : $"+pastdueamount);
		
		// get reference to radio group in layout
        radiogrouppaymentaccts = (RadioGroup) findViewById(R.id.radiopaymentaccounts);
    	// layout params to use when adding each radio button
        LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        // add 20 radio buttons to the group
        for (int i = 0; i < numButtons; i++) {
        	RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText( accountslist[i]);
	    radiogrouppaymentaccts.addView(rdbtn, layoutParams);
        }
        
	} //getpaymentaccts finish
		
	private class GetPayMyBill extends AsyncTask<Void, Void, Void> {

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
								if(r==0){
								 remainingbalance = jsonObj.getString(TAG_REMAININGBALANCE);
								 System.out.println("remainingbalance : "+ remainingbalance );	
								 amountdue = jsonObj.getString(TAG_AMOUNTDUE);
								 System.out.println("amountdue : "+ amountdue );	
								 pastdueamount = jsonObj.getString(TAG_PASTAMOUNTDUE);
								 System.out.println("pastdueamount : "+ pastdueamount );
								 }
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
			 System.out.println("button#====>onpost exe"+numberofradiobuttons);
//			 getpaymentamounts(remainingbalance,amountdue,pastdueamount);
			  getpaymentaccounts(numberofradiobuttons);
	        }
		
	} // get office name result async class ends here

	private class MakePayment extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
	String MAKEPAYMENT = "http://10.0.3.2:8080/NewPerceptServer/service/paymybill/makepayment/username/Tom/paymentamt/"+ selectedaccttype;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(MAKEPAYMENT,ServiceHandler.GET);

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
					Intent gotomypaymenthistory = new Intent(PayMyBillActivity.this, PaymentHistoryActivity.class);
					startActivity(gotomypaymenthistory);
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Payment failure", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get checkings acct async class ends here

	private class MakePaymentForPayPal extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
	String MAKEPAYMENT = "http://10.0.3.2:8080/NewPerceptServer/service/paymybill/makepayment/username/Tom/paymentamt/"+ selectedaccttype;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(MAKEPAYMENT,ServiceHandler.GET);

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
//					Toast.makeText(getApplicationContext(),
//							"Payment success", Toast.LENGTH_LONG).show();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Payment failure", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get checkings acct async class ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pay_my_bill, menu);
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
