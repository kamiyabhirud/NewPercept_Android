package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.newpercept.R;

public class GlassesContactShadesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glassescontactshadeslayout);
		getActionBar().setHomeButtonEnabled(true);


			// setting image resource from drawable for frames
			ImageView imageviewframes = (ImageView) findViewById(R.id.ivframes);
			imageviewframes.setImageResource(R.drawable.glassesframes);
			imageviewframes.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent gotoframeshome = new Intent(GlassesContactShadesActivity.this, FramesHomeActivity.class);
					startActivity(gotoframeshome);
				}
			});

			ImageView imageviewcontacts = (ImageView) findViewById(R.id.ivcontacts);
			imageviewcontacts.setImageResource(R.drawable.contactlens);
			imageviewcontacts.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent gotocontactshome = new Intent(GlassesContactShadesActivity.this, ContactsHomeActivity.class);
					startActivity(gotocontactshome);
				}
			});
			ImageView imageviewsunglasses = (ImageView) findViewById(R.id.ivsunglasses);
			imageviewsunglasses.setImageResource(R.drawable.sunglasses);
			imageviewsunglasses.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent gotosunglasseshome = new Intent(GlassesContactShadesActivity.this, SunGlassesHomeActivity.class);
					startActivity(gotosunglasseshome);
				}
			});
		} //oncreate ends here


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.glasses_contact_shades, menu);
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
