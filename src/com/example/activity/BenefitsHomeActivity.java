package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newpercept.R;

public class BenefitsHomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benefitshomelayout);
		//enable clicking actionbar
		getActionBar().setHomeButtonEnabled(true);
		
		ListView listbenefitsmenu;
		String list_array[]={"How to use my Benefits"
				                    ,"Benefits with NewPercept network providers",
				                     "Benefits with out-of-network providers",
				                     "Member Vision Card"};
		 
		 listbenefitsmenu=(ListView)findViewById(R.id.benefitslist);
		 listbenefitsmenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,list_array));
 
		 listbenefitsmenu.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//		          final TextView mTextView = (TextView)view;
		          switch (position) {
		            case 0:
		             Intent howtousebenefitsact = new Intent(BenefitsHomeActivity.this,HowToUseMyBenefitsActivity.class);     
		             startActivity(howtousebenefitsact);
		            break;
		            case 1:
		             Intent benefitswithinnetworkact = new Intent(BenefitsHomeActivity.this,BenefitsWithInNetworkProvidersActivity.class);     
		             startActivity(benefitswithinnetworkact);
		            break;
		            case 2:
		             Intent benefitswithoutnetworkact = new Intent(BenefitsHomeActivity.this,BenefitsWithOutNetworkProvidersActivity.class);     
		             startActivity(benefitswithoutnetworkact);
		            break;
		            case 3:
			             Intent membervisioncardact = new Intent(BenefitsHomeActivity.this,MemberVisionCardActivity.class);     
			             startActivity(membervisioncardact);
			            break;
		           
		            default:
		              // Nothing do!
		          }

		      }
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.benefits_home, menu);
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
