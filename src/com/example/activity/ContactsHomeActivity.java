package com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.newpercept.R;

public class ContactsHomeActivity extends ActionBarActivity {

	final Context context = this;
	GridView gv;
	
	public static String[] contactsbrandnamelist = { "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6",  "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6"};
	public static int[] contactsbrandimages = { R.drawable.brand1,
			R.drawable.brand2, R.drawable.brand3, R.drawable.brand4,
			R.drawable.brand5, R.drawable.brand6,
			 R.drawable.brand1,	R.drawable.brand2, R.drawable.brand3, 
			 R.drawable.brand4,	R.drawable.brand5, R.drawable.brand6};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactshomelayout);
		getActionBar().setHomeButtonEnabled(true);

		
		gv = (GridView) findViewById(R.id.gridviewbrandscontacts);
		gv.setAdapter(new CustomAdapterContacts(this, contactsbrandnamelist,contactsbrandimages));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts_home, menu);
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
