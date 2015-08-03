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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newpercept.R;

public class LoginActivity extends ActionBarActivity {
	
	EditText usernameedittext;
	EditText passwordedittext;
	private String getlogincredentials;
	private String uservalidity = "";
	private String password = "" ;
	
	public String username = "";
	public String TAG_STATUS = "status";
	public String status = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginlayout);
		//	MenuItem signinup = (MenuItem) findViewById(R.id.signinup);

		// after pressing GOTO HOME PAGE button
		Button buttonlogin = (Button) findViewById(R.id.button_login);
		buttonlogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//capture edittext values
				usernameedittext   = (EditText)findViewById(R.id.enter_username );
				username = usernameedittext.getText().toString();
				
				passwordedittext = (EditText)findViewById(R.id.enter_password);
				password = passwordedittext.getText().toString();
				// goto home page after clicking
				Intent onlogingotohomepage = new Intent(LoginActivity.this, HomeActivity.class);
				startActivity(onlogingotohomepage);
				new CheckLogin().execute();
			}
		});
		
		Button buttonregister = (Button) findViewById(R.id.button_register);
		buttonregister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// goto register page after clicking
//				Intent gotoregisterpage = new Intent(LoginActivity.this, RegisterActivity.class);
				Intent gotoregisterpage = new Intent(LoginActivity.this, MapsRouteActivity.class);
				
				startActivity(gotoregisterpage);
			}
		});
		
		
		new CheckLogin().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/**public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}*/

	private class CheckLogin extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected Void doInBackground(Void... arg0) {
			
			  String NAMESPACE = "http://10.0.3.2:8080/NewPerceptServer/";
              String SERVICE = "service/logincredentials/";
              System.out.println(username +": uname : "+password+" : pwd");
             // String LOGINURL = NAMESPACE + SERVICE + "username/"+username+"/password/"+password;
//              String LOGINURL  = "http://10.0.3.2:8080/NewPerceptServer/service/logincredentials/username/"+username+"/password/"+password;
              String LOGINURL  = "http://10.0.3.2:8080/NewPerceptServer/service/logincredentials/username/Tom/password/Tom";
//                RestTemplate restTemplate = new RestTemplate();
//                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//                LoginBean loginobj = restTemplate.getForObject(LOGINURL, LoginBean.class);
                
                ServiceHandler sh = new ServiceHandler();

    			// Making a request to url and getting response
                String jsonstr = sh.makeServiceCall(LOGINURL, ServiceHandler.GET);

    			Log.d("Response: is here------ ------------", "> " + jsonstr);

    			if (jsonstr != null) {
    						
					try {
						JSONObject jsonObj = new JSONObject(jsonstr);
					
					     status = jsonObj.getString(TAG_STATUS);
						 System.out.println("status : "+ status );
						 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
                
		}
    			return null;
			}
		
		protected void onPostExecute(Void result) {
			if (status.equalsIgnoreCase("success")) {
					Intent i = new Intent(LoginActivity.this, HomeActivity.class);
					startActivity(i);
					System.out.println(status + "==============>result");
					//pDialog.dismiss();
		
			} else {	
				//pDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Invalid User Credentials", Toast.LENGTH_LONG).show();
			}

		};
}
	}