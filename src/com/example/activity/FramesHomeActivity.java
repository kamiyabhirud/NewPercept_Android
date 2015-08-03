package com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.newpercept.R;

public class FramesHomeActivity extends ActionBarActivity {

	final Context context = this;
	GridView gv;
	
	public static String[] framesbrandnamelist = { "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6",  "Brand1", "Brand2",
			"Brand3", "Brand4", "Brand5", "Brand6"};
	public static int[] framesbrandimages = { R.drawable.brand1,
			R.drawable.brand2, R.drawable.brand3, R.drawable.brand4,
			R.drawable.brand5, R.drawable.brand6,
			 R.drawable.brand1,	R.drawable.brand2, R.drawable.brand3, 
			 R.drawable.brand4,	R.drawable.brand5, R.drawable.brand6};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frameshomelayout);
		getActionBar().setHomeButtonEnabled(true);
		
		gv = (GridView) findViewById(R.id.gridviewbrandsframes);
		gv.setAdapter(new CustomAdapterFrames(this, framesbrandnamelist,framesbrandimages));
//		ImageButton dialogbuttongucci = (ImageButton) findViewById(R.id.ibuttongucci);
//		dialogbuttongucci.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				//create layout for dialog
//				LayoutInflater li = LayoutInflater.from(context);
//				View promptsView = li.inflate(R.layout.dialoggucciframes, null);
//				
//				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//				
//				// set prompts.xml to alertdialog builder
//				alertDialogBuilder.setView(promptsView);
//				
//				
////				 set dialog message
//				alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
//					    public void onClick(DialogInterface dialog,int id) {
//						dialog.cancel();
//					    }
//					  }).setCancelable(false).setPositiveButton("Find A Doctor that carries this brand",
//							  new DialogInterface.OnClickListener() {
//						    public void onClick(DialogInterface dialog,int id) {
//						    	//goto page with list of routes
//						    	 Intent gotosearchbylocation = new Intent(FramesHomeActivity.this,FindADocActivity.class);     
//					             startActivity(gotosearchbylocation);
//							// get user input and set it to result
//							// edit text
////							result.setText(userInput.getText());
//						    }
//						  });
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
// 
//				// show it
//				alertDialog.show();
//			 }// on click ends here
//		});
	}//oncreate ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frames_home, menu);
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
