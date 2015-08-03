package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.example.newpercept.R;

public class BenefitsWithOutNetworkProvidersActivity extends ActionBarActivity {
	ExpandableListAdapterOutOfNetworkBenefits listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benefitswithoutnetworkproviderslayout);

		getActionBar().setHomeButtonEnabled(true);
		
		Button buttonregister = (Button) findViewById(R.id.button_okout);
		buttonregister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// goto register page after clicking
				Intent gotohomepage = new Intent(BenefitsWithOutNetworkProvidersActivity.this, BenefitsHomeActivity.class);
//				Intent gotoregisterpage = new Intent(LoginActivity.this, SimpleDemo.class);
				
				startActivity(gotohomepage);
			}
		});

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandablelvbenefitsoutofnetwork);
       
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapterOutOfNetworkBenefits(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
 
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
 
            }
        });
    }
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("EXAM");
        listDataHeader.add("PRESCRIPTION LENSES");
        listDataHeader.add("FRAME");
        listDataHeader.add("CONTACTS INSTEAD OF GLASSES");
 
        // Adding child data
        List<String> listexam = new ArrayList<String>();
        listexam.add("Eligibility : YOU ARE ELIGIBLE");
        listexam.add("Frequency : You can have an eye exam every 12 months beginning January");
        listexam.add("CoPay : $10.00");
        listexam.add("Coverage : You will be reimbursed up to $40.00");
 
        List<String> listprescriptionlenses = new ArrayList<String>();
        listprescriptionlenses.add("Eligibility : YOU ARE ELIGIBLE");
        listprescriptionlenses.add("Frequency : You can have new lenses every 12 months begining in January");
        listprescriptionlenses.add("CoPay : $25.00 for lenses and/or frame");
        listprescriptionlenses.add("Coverage : You will be reimbursed up to : Single Vision : $40.00");
 
        List<String> listframe = new ArrayList<String>();
        listframe.add("Eligibility : YOU ARE ELIGIBLE");
        listframe.add("Frequency : You can choose a new frame every 24 months begining in January");
        listframe.add("CoPay : $25.00 for lenses and/or frame");
        listframe.add("Coverage : You will be reimbursed up to $40.00");
        
        List<String> listcontacts = new ArrayList<String>();
        listcontacts.add("Eligibility : YOU ARE ELIGIBLE");
        listcontacts.add("Frequency : You can get contacts every 12 months begining in January");
        listcontacts.add("CoPay : None");
        listcontacts.add("Coverage : If you get an exam and contacts, your total reimbursement is up to $110.00");
        
        listDataChild.put(listDataHeader.get(0), listexam); // Header, Child data
        listDataChild.put(listDataHeader.get(1), listprescriptionlenses);
        listDataChild.put(listDataHeader.get(2), listframe);
        listDataChild.put(listDataHeader.get(3), listcontacts);
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
