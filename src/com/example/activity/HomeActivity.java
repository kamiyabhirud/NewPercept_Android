package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.newpercept.R;

public class HomeActivity extends Activity{
	
	ImageButton benefitsbutton;
	ImageButton findadocbutton;
	ImageButton membervisioncardbutton;
	ImageButton glassesbutton;
	ImageButton myccountbutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepagelayout);
		
		 addListenerOnButton();
	}
	private void addListenerOnButton() {
		
		benefitsbutton = (ImageButton) findViewById (R.id.buttonbenefits);
		benefitsbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(HomeActivity.this,BenefitsHomeActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
		findadocbutton = (ImageButton) findViewById (R.id.buttonfindadoc);
		findadocbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(HomeActivity.this,FindADocActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
		membervisioncardbutton = (ImageButton) findViewById (R.id.buttonmembervisioncard);
		membervisioncardbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(HomeActivity.this,MemberVisionCardActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
		glassesbutton = (ImageButton) findViewById (R.id.buttonglasses);
		glassesbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(HomeActivity.this,GlassesContactShadesActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
		myccountbutton = (ImageButton) findViewById (R.id.buttonmyaccount);
		myccountbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(HomeActivity.this,MyAccountHomeActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
	}//on create ends here
//		ListView listmainmenu;
//		String list_array[]={"Benefits","Find A Doctor","My Account","Glasses, Contacts and Sun Glasses"};
//		 
//		 listmainmenu=(ListView)findViewById(R.id.homelist);
//		 listmainmenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,list_array));
// 
//		 listmainmenu.setOnItemClickListener(new OnItemClickListener() {
//		      public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
////		          final TextView mTextView = (TextView)view;
//		          switch (position) {
//		            case 0:
//		             Intent benefitshomeactivity = new Intent(HomeActivity.this,BenefitsHomeActivity.class);     
//		             startActivity(benefitshomeactivity);
//		            break;
//		            case 1:
//		             Intent findadocact = new Intent(HomeActivity.this,FindADocActivity.class);     
//		             startActivity(findadocact);
//		            break;
//		            case 2:
//		             Intent myaccountact = new Intent(HomeActivity.this,MyAccounthomeActivity.class);     
//		             startActivity(myaccountact);
//		            break;
//		            case 3:
//			             Intent glassframeact = new Intent(HomeActivity.this,GlassesContactShadesActivity.class);     
//			             startActivity(glassframeact);
//			            break;
//		           
//		            default:
//		              // Nothing do!
//		          }
//
//		      }
//		    });

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.home_page, menu);
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_homepage,
					container, false);
			return rootView;
		}
	}


}
