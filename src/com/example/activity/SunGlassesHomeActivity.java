package com.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.newpercept.R;

public class SunGlassesHomeActivity extends Activity {
	GridView gv;
	Context context;
	public static String[] sunglassbrandnamelist = { "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6",  "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6"};
	public static int[] sunglassbrandimages = { R.drawable.brand1,
			R.drawable.brand2, R.drawable.brand3, R.drawable.brand4,
			R.drawable.brand5, R.drawable.brand6,
			 R.drawable.brand1,	R.drawable.brand2, R.drawable.brand3, 
			 R.drawable.brand4,	R.drawable.brand5, R.drawable.brand6};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sunglasseshomelayout);
		getActionBar().setHomeButtonEnabled(true);
		
		gv = (GridView) findViewById(R.id.gridviewbrandssunglasses);
		gv.setAdapter(new CustomAdapterSunGlasses(this, sunglassbrandnamelist,sunglassbrandimages));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sun_glasses_home, menu);
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
