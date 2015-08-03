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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newpercept.R;

public class AddNewAccountActivity extends Activity {
	 public String TAG_STATUS = "status";
	 public String status = "";
	  private RadioGroup radiogrpaccounttype;
	  private RadioButton radioaccounttype;
	  final Context context = this;
	  
	  EditText edittextfirstname;
	  EditText edittextlastname;
	  EditText edittextacctnumber;
	  EditText edittextexpirymonth;
	  EditText edittextexpiryyear;
	  EditText edittextcvv;
	  EditText edittextcardtype;
	  EditText edittextroutingnumber;
	  
	  public String firstname = "";
	  public String lastname = "";
	  public String acctnumber = "";
	  public String expirymonth = "";
	  public String expiryyear = "";
	  public String cvv = "";
	  public String cardtype = "";
	  public String routingnumber = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewaccountlayout);
		getActionBar().setHomeButtonEnabled(true);
		
		addListenerOnButton();
			}

	public void addListenerOnButton() {

		radiogrpaccounttype = (RadioGroup) findViewById(R.id.radiogrpacct);
		
		Button buttonaddacct = (Button) findViewById(R.id.buttonadd);
		buttonaddacct.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	                // get selected radio button from radioGroup
	            int selectedId = radiogrpaccounttype.getCheckedRadioButtonId();
	            radioaccounttype = (RadioButton) findViewById(selectedId);
	            String selectedacct =  (String) radioaccounttype.getText();
	            LayoutInflater li = LayoutInflater.from(context);
	            
	            if(selectedacct.equalsIgnoreCase("Credit Card")){
				View promptsView = li.inflate(R.layout.addaccountcreditcarddialoglayout, null);
	           
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
             // set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
			      edittextfirstname   = (EditText) promptsView.findViewById(R.id.enterfirstname );
				  edittextlastname   = (EditText) promptsView.findViewById(R.id.enterLastname );
				  edittextacctnumber   = (EditText) promptsView.findViewById(R.id.etacctnumber );
				  edittextexpirymonth   = (EditText) promptsView.findViewById(R.id.etexpirymonth );
				  edittextexpiryyear   = (EditText) promptsView.findViewById(R.id.etexpiryyear );
				  edittextcvv   = (EditText) promptsView.findViewById(R.id.etcvv );
				  edittextcardtype   = (EditText) promptsView.findViewById(R.id.etcardtype );
				  
							// set title
							alertDialogBuilder.setTitle("Add Credit Card Details");
							// set dialog message
							alertDialogBuilder
								.setCancelable(false)
								.setPositiveButton("Add",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										  firstname = edittextfirstname.getText().toString();
										  lastname = edittextlastname.getText().toString();
										  acctnumber = edittextacctnumber.getText().toString();
										  expirymonth = edittextexpirymonth.getText().toString();
										  expiryyear = edittextexpiryyear.getText().toString();
										  cvv = edittextcvv.getText().toString();
										  cardtype = edittextcardtype.getText().toString();
										new AddCreditCard().execute();
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
								  
								  
								//if loop for credit ends
	        }else if(selectedacct.equalsIgnoreCase("Checkings")){
	        	View promptsView = li.inflate(R.layout.addaccountcheckingsdialoglayout, null);
		           
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
             // set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
				  edittextfirstname   = (EditText) promptsView.findViewById(R.id.chkenterfirstname );
				  edittextlastname   = (EditText) promptsView.findViewById(R.id.chkenterLastname );
				  edittextacctnumber   = (EditText) promptsView.findViewById(R.id.chketacctnumber );
				  edittextroutingnumber   = (EditText) promptsView.findViewById(R.id.chketroutingnumber );
							// set title
							alertDialogBuilder.setTitle("Add Checkings account");
							// set dialog message
							alertDialogBuilder
								.setCancelable(false)
								.setPositiveButton("Add",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										  firstname = edittextfirstname.getText().toString();
										  lastname = edittextlastname.getText().toString();
										  acctnumber = edittextacctnumber.getText().toString();
										  routingnumber = edittextroutingnumber.getText().toString();
										  new AddCheckingsAccount().execute();
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
								alertDialog.show(); //if loop for checkings ends
	        }else if(selectedacct.equalsIgnoreCase("Savings")){
	        	View promptsView = li.inflate(R.layout.addaccountsavingsdialoglayout, null);
		           
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
             // set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
				edittextfirstname   = (EditText) promptsView.findViewById(R.id.saventerfirstname );
				  edittextlastname   = (EditText) promptsView.findViewById(R.id.saventerLastname );
				  edittextacctnumber   = (EditText) promptsView.findViewById(R.id.savetacctnumber );
				  edittextroutingnumber   = (EditText) promptsView.findViewById(R.id.savetroutingnumber );
							// set title
							alertDialogBuilder.setTitle("Add Savings account");
							// set dialog message
							alertDialogBuilder
								.setCancelable(false)
								.setPositiveButton("Add",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										 firstname = edittextfirstname.getText().toString();
										  lastname = edittextlastname.getText().toString();
										  acctnumber = edittextacctnumber.getText().toString();
										  routingnumber = edittextroutingnumber.getText().toString();
										  new AddSavingsAccount().execute();
										dialog.cancel();
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
								alertDialog.show(); //if loop for credit ends	
								
	        }
	        }
	    }); 
	}//oncreate ends here
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
	private class AddCreditCard extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
		String tempcardnumber = cardtype+" "+acctnumber;
		String encodecard = encodeforurl(tempcardnumber);
//		String ADDCREDITCARDACCOUNT = "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/addcreditcardaccount/username/Tom/firstname/tomy/lastname/shah/accountdetails/visaxxxx-xxxx-xxxx-4444/expirationmonth/12/expirationyear/17/cvvnumber/166/cardtype/visa";
		String ADDCREDITCARDACCOUNT = "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/addcreditcardaccount/username/Tom/"
				+ "firstname/"+firstname+"/lastname/"+lastname+"/accountdetails/"+encodecard+"/expirationmonth/"+expirymonth+"/expirationyear/"
						+expiryyear+ "/cvvnumber/"+cvv+"/cardtype/"+cardtype;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(ADDCREDITCARDACCOUNT,ServiceHandler.GET);

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
							"Credit Card Added", Toast.LENGTH_LONG).show();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Account cannot be added.Please contact customer service", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get office name result async class ends here
	private class AddCheckingsAccount extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
		String encodeacct = encodeforurl(acctnumber);
	String ADDCHECKINGSACCOUNT = "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/addcheckingsaccount/username/Tom/"
				+ "firstname/"+firstname+"/lastname/"+lastname+"/accountdetails/"+encodeacct+"/routingnumber/"+routingnumber;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(ADDCHECKINGSACCOUNT,ServiceHandler.GET);

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
							"Checkings Account Added", Toast.LENGTH_LONG).show();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Account cannot be added.Please contact customer service", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get checkings acct async class ends here
	private class AddSavingsAccount extends AsyncTask<Void, Void, Void> {

		/******** fetches and parse json data and add to list ***********/

	@Override
	protected Void doInBackground(Void... arg0) {
		String encodeacct = encodeforurl(acctnumber);
	String ADDSAVINGSACCOUNT = "http://10.0.3.2:8080/NewPerceptServer/service/mypaymentaccounts/addsavingsaccount/username/Tom/"
				+ "firstname/"+firstname+"/lastname/"+lastname+"/accountdetails/"+encodeacct+"/routingnumber/"+routingnumber;
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonstr = sh.makeServiceCall(ADDSAVINGSACCOUNT,ServiceHandler.GET);

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
							"Savings Account Added", Toast.LENGTH_LONG).show();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Account cannot be added.Please contact customer service", Toast.LENGTH_LONG).show();
			}

			
	        }
		
	} // get checkings acct async class ends here


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_account, menu);
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
