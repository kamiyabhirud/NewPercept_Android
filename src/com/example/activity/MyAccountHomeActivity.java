package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.newpercept.R;

public class MyAccountHomeActivity extends Activity {

	ImageButton userprofilebutton;
	ImageButton paymenthistorybutton;
	ImageButton paymentaccountsbutton;
	ImageButton paymybillbutton;
	ImageButton autopaymentbutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccounthomelayout);
		
		getActionBar().setHomeButtonEnabled(true);
		 addListenerOnButton();
	} // oncreate ends here
	
private void addListenerOnButton() {
		
	userprofilebutton = (ImageButton) findViewById (R.id.imageuserprofile);
	userprofilebutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(MyAccountHomeActivity.this,UserProfileActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
	paymenthistorybutton = (ImageButton) findViewById (R.id.imagepaymenthistory);
	paymenthistorybutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(MyAccountHomeActivity.this,PaymentHistoryActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
		
	paymentaccountsbutton = (ImageButton) findViewById (R.id.imagepaymentaccounts);
	paymentaccountsbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(MyAccountHomeActivity.this,PaymentAccountsActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
	
	paymybillbutton = (ImageButton) findViewById (R.id.imagepaymybill);
	paymybillbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(MyAccountHomeActivity.this,PayMyBillActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
	
	autopaymentbutton = (ImageButton) findViewById (R.id.imageautopayment);
	autopaymentbutton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
        	  Intent benefitshomeactivity = new Intent(MyAccountHomeActivity.this,AutomaticPaymentsActivity.class);     
	             startActivity(benefitshomeactivity);
          }
        });	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account_home, menu);
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
