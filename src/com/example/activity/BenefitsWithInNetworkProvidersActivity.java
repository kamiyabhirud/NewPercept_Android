package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import com.example.newpercept.R;

public class BenefitsWithInNetworkProvidersActivity extends ActionBarActivity {
	ExpandableListAdapterInNetworkBenefits listAdapter;
	    ExpandableListView expListView;
	    List<String> listDataHeader;
	    HashMap<String, List<String>> listDataChild;
	    
	    String TAG_niceeligibility = "niceeligibility";
	    String TAG_nicefrequency = "nicefrequency";
	    String TAG_nicecopay = "nicecopay";
	    String TAG_nicecoverage = "nicecoverage";
	    String TAG_nicedesc = "nicedesc";
	    String TAG_contacteligibility = "contacteligibility";
	    String TAG_contactfrequency = "contactfrequency";
	    String TAG_contactcopay = "contactcopay";
	    String TAG_contactcoverage = "contactcoverage";
	    String TAG_contactdesc = "contactdesc";
	    String TAG_prescriptioneligibility = "prescriptioneligibility";
	    String TAG_prescriptionfrequency = "prescriptionfrequency";
	    String TAG_prescriptioncopay = "prescriptioncopay";
	    String TAG_prescriptioncoverage = "prescriptioncoverage";
	    String TAG_prescriptiondesc = "prescriptiondesc";
	    String TAG_frameeligibility = "frameeligibility";
	    String TAG_framefrequency = "framefrequency";
	    String TAG_framecopay = "framecopay";
	    String TAG_framecoverage = "framecoverage";
	    String TAG_framedesc = "framedesc";
	    String TAG_lasereligibility = "lasereligibility";
	    String TAG_laserfrequency = "laserfrequency";
	    String TAG_lasercopay = "lasercopay";
	    String TAG_lasercoverage = "lasercoverage";
	    String TAG_laserdesc = "laserdesc";
	    String TAG_diabeticeligibility = "diabeticeligibility";
	    String TAG_diabeticfrequency = "diabeticfrequency";
	    String TAG_diabeticcopay = "diabeticcopay";
	    String TAG_diabeticcoverage = "diabeticcoverage";
	    String TAG_diabeticdesc = "diabeticdesc";
	    String TAG_plandesc = "plandesc";
	    String TAG_comdesc = "comdesc";
	    String TAG_howdesc = "howdesc";
	    
	    public String niceeligibility = "";
	    public String nicefrequency = "";
	    public String nicecopay = "";
	    public String nicecoverage = "";
	    public String nicedesc = "";
	    public String contacteligibility = "";
	    public String contactfrequency = "";
	    public String contactcopay = "";
	    public String contactcoverage = "";
	    public String contactdesc = "";
	    public String prescriptioneligibility = "";
	    public String prescriptionfrequency = "";
	    public String prescriptioncopay = "";
	    public String prescriptioncoverage = "";
	    public String prescriptiondesc = "";
	    public String frameeligibility = "";
	    public String framefrequency = "";
	    public String framecopay = "";
	    public String framecoverage = "";
	    public String framedesc = "";
	    public String lasereligibility = "";
	    public String laserfrequency = "";
	    public String lasercopay = "";
	    public String lasercoverage = "";
	    public String laserdesc = "";
	    public String diabeticeligibility = "";
	    public String diabeticfrequency = "";
	    public String diabeticcopay = "";
	    public String diabeticcoverage = "";
	    public String diabeticdesc = "";
	    public String plandesc = "";
	    public String comdesc = "";
	    public String howtodesc = "";
	    
		  final Context context = this;
	    
	    public String howdesc;
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.benefitswithinnetworkproviderslayout);
	        
	        new GetWithInNetworkBenefits().execute();
	        
	        getActionBar().setHomeButtonEnabled(true);
	 
	        // get the listview
	        expListView = (ExpandableListView) findViewById(R.id.expandablelvbenefitsinnetwork);
	 
	        // preparing list data
	        prepareListData();
	 
	        listAdapter = new ExpandableListAdapterInNetworkBenefits(this, listDataHeader, listDataChild);
	 
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
//	                Toast.makeText(getApplicationContext(),
//	                        listDataHeader.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
	            }
	        });
	 
	        // Listview Group collasped listener
	        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
	 
	            @Override
	            public void onGroupCollapse(int groupPosition) {
//	                Toast.makeText(getApplicationContext(),
//	                        listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
	 
	            }
	        });
	 
	        // Listview on child click listener
	        expListView.setOnChildClickListener(new OnChildClickListener() {
	 
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
	            	 LayoutInflater li = LayoutInflater.from(context);
    	             
      	            // common textlayview
      	                View promptsView = li.inflate(R.layout.dialognicevisionlayout, null);
      	           
	            	
      	         	TextView tveligiblitiy = (TextView) promptsView.findViewById(R.id.tvnicevisioneligibility);
 	            	TextView tvfrequency = (TextView) promptsView.findViewById(R.id.tvnicevisionfrequency);
 	            	TextView tvcopay = (TextView) promptsView.findViewById(R.id.tvnicevisioncopay);
 	            	TextView tvcoverage = (TextView) promptsView.findViewById(R.id.tvnicevisionceverage);
 	            	TextView tvdesc = (TextView) promptsView.findViewById(R.id.tvnicevisiondesc);
 	            	
	            	switch(groupPosition)
	            	{ case 0:
	            		switch(childPosition)
	            		{  case 0: //nicevision      			
	            	        AlertDialog.Builder alertDialogBuildernv = new AlertDialog.Builder(context);
	                     // set prompts.xml to alertdialog builder
	                        alertDialogBuildernv.setView(promptsView);
         				//create text view	        				
	        				tveligiblitiy.setText("Eligibility : "+ niceeligibility);
	        				tvfrequency.setText("Frequency : "+ nicefrequency);
	        				tvcopay.setText("CoPay : "+ nicecopay);
	        				tvcoverage.setText("Coverage : "+ nicecoverage);
	        				tvdesc.setText("Description : "+ nicedesc);
	        			 
	            			// set title
	        				alertDialogBuildernv.setTitle("NiceVision");
							// set dialog message
	        				alertDialogBuildernv
								.setCancelable(false)
								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										dialog.cancel();
										
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialogn = alertDialogBuildernv.create();
				 
								// show it
								alertDialogn.show(); 
//								alertDialogn.dismiss();
								break;
	            		case 1://contact lens exam
	            			
	            	 	
	            			AlertDialog.Builder alertDialogBuilderlensexam = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	 	        				alertDialogBuilderlensexam.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Eligibility : "+ contacteligibility);
	 	        				tvfrequency.setText("Frequency : "+ contactfrequency);
	 	        				tvcopay.setText("CoPay : "+ contactcopay);
	 	        				tvcoverage.setText("Coverage : "+ contactcoverage);
	 	        				tvdesc.setText("Description : "+ contactdesc);
	 	        			    
	 	            			// set title
	 							alertDialogBuilderlensexam.setTitle("Contact Lens Exam");
	 							// set dialog message
	 							alertDialogBuilderlensexam
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 										
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogexam = alertDialogBuilderlensexam.create();
	 				 
	 								// show it
	 								alertDialogexam.show(); 
	 							break;
	            		case 2: //prescription
	            			 AlertDialog.Builder alertDialogBuilderprescription = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuilderprescription.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Eligibility : "+ prescriptioneligibility);
	 	        				tvfrequency.setText("Frequency : "+ prescriptionfrequency);
	 	        				tvcopay.setText("CoPay : "+ prescriptioncopay);
	 	        				tvcoverage.setText("Coverage : "+ prescriptioncoverage);
	 	        				tvdesc.setText("Description : "+ prescriptiondesc);
	 	        			 
	 	            			// set title
	 	        				alertDialogBuilderprescription.setTitle("Prescription Lenses");
	 							// set dialog message
	 	        				alertDialogBuilderprescription
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogp = alertDialogBuilderprescription.create();
	 				 
	 								// show it
	 								alertDialogp.show(); 
	 								break;
	            		case 3: //frame
	            			 AlertDialog.Builder alertDialogBuilderFrame = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuilderFrame.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Eligibility : "+ frameeligibility);
	 	        				tvfrequency.setText("Frequency : "+ framefrequency);
	 	        				tvcopay.setText("CoPay : "+ framecopay);
	 	        				tvcoverage.setText("Coverage : "+ framecoverage);
	 	        				tvdesc.setText("Description : "+ framedesc);
	 	        			
	 	            			// set title
	 	        				alertDialogBuilderFrame.setTitle("Frame");
	 							// set dialog message
	 	        				alertDialogBuilderFrame
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogf = alertDialogBuilderFrame.create();
	 				 
	 								// show it
	 								alertDialogf.show(); 
	 								break;
	            		case 4: //laser
	            			 AlertDialog.Builder alertDialogBuildervc = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuildervc.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Eligibility : "+ lasereligibility);
	 	        				tvfrequency.setText("Frequency : "+ laserfrequency);
	 	        				tvcopay.setText("CoPay : "+ lasercopay);
	 	        				tvcoverage.setText("Coverage : "+ lasercoverage);
	 	        				tvdesc.setText("Description : "+ laserdesc);
	 	        			
	 	            			// set title
	 	        				alertDialogBuildervc.setTitle("Laser VisionCare");
	 							// set dialog message
	 	        				alertDialogBuildervc
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogv = alertDialogBuildervc.create();
	 				 
	 								// show it
	 								alertDialogv.show(); 
	 								break;
	            		
	            		}
	            		break; //case 0 switch position
	            	case 1:
	            		switch(childPosition)
	            		{ case 0: //diabetic
	            			 View promptsViewlensexam = li.inflate(R.layout.dialogcontactlensexamlayout, null); //used for diabetics
	            			TextView tveligiblitiyex = (TextView) promptsViewlensexam.findViewById(R.id.tvlensexameligibility);
	     	            	TextView tvfrequencyex = (TextView) promptsViewlensexam.findViewById(R.id.tvlensexamfrequency);
	     	            	TextView tvcopayex = (TextView) promptsViewlensexam.findViewById(R.id.tvlensexamcopay);
	     	            	TextView tvcoverageex = (TextView) promptsViewlensexam.findViewById(R.id.tvlensexamceverage);
	     	            	TextView tvdescex = (TextView) promptsViewlensexam.findViewById(R.id.tvlensexamdesc);
	            			  AlertDialog.Builder alertDialogBuilderDiabetic = new AlertDialog.Builder(context);
		 	                     // set prompts.xml to alertdialog builder
		            			  alertDialogBuilderDiabetic.setView(promptsViewlensexam);
		          				//create text view	      
		            				tveligiblitiyex.setText("Eligibility : "+ diabeticeligibility);
		 	        				tvfrequencyex.setText("Frequency : "+ diabeticfrequency);
		 	        				tvcopayex.setText("CoPay : "+ diabeticcopay);
		 	        				tvcoverageex.setText("Coverage : "+ diabeticcoverage);
		 	        				tvdescex.setText("Description : "+ diabeticdesc);
		 	        			   
		 	            			// set title
		 	        				alertDialogBuilderDiabetic.setTitle("Diabetic Eyecare");
		 							// set dialog message
		 	        				alertDialogBuilderDiabetic
		 								.setCancelable(false)
		 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
		 									public void onClick(DialogInterface dialog,int id) {
		 										dialog.cancel();
		 									}
		 								});
		 				 
		 								// create alert dialog
		 								AlertDialog alertDialogd = alertDialogBuilderDiabetic.create();
		 				 
		 								// show it
		 								alertDialogd.show(); 
		            		    break;
	            		} 
	            		
	            		break;
	            		
	            	
	            	case 2:
	            		switch(childPosition)
	            		{ case 0://allowed by plan
	            			 AlertDialog.Builder alertDialogBuilderplan = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuilderplan.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Description : "+ plandesc);
	 	        				 
	 	            			// set title
	 	        				alertDialogBuilderplan.setTitle("What does my plan allow");
	 							// set dialog message
	 	        				alertDialogBuilderplan
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogpln = alertDialogBuilderplan.create();
	 				 
	 								// show it
	 								alertDialogpln.show(); 
	 								break;
	            		case 1: //.com
	            			AlertDialog.Builder alertDialogBuildercom = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuildercom.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Description : "+ comdesc);
	 	        			 
	 	            			// set title
	 	        				alertDialogBuildercom.setTitle("NewPercept.com Overview");
	 							// set dialog message
	 	        				alertDialogBuildercom
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogcm = alertDialogBuildercom.create();
	 				 
	 								// show it
	 								alertDialogcm.show(); 
	 								break;
	            		case 2: //hw does it work
	            			  AlertDialog.Builder alertDialogBuilderhow = new AlertDialog.Builder(context);
	 	                     // set prompts.xml to alertdialog builder
	            			  alertDialogBuilderhow.setView(promptsView);
	          				//create text view	        				
	 	        				tveligiblitiy.setText("Description : "+ howtodesc);
	 	        				
	 	            			// set title
	 	        				alertDialogBuilderhow.setTitle("How Does It Work");
	 							// set dialog message
	 	        				alertDialogBuilderhow
	 								.setCancelable(false)
	 								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
	 									public void onClick(DialogInterface dialog,int id) {
	 										dialog.cancel();
	 									}
	 								});
	 				 
	 								// create alert dialog
	 								AlertDialog alertDialogh = alertDialogBuilderhow.create();
	 				 
	 								// show it
	 								alertDialogh.show(); 
	 								break;
	            		}
	            		break; //case 2 switch position
	            	
	            		}//group position switch ends here
	                return false;
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
	        listDataHeader.add("NewPercept NiceVision Plan through NewPercept Doctor");
	        listDataHeader.add("Speciality EyeCare Service through NewPercept Doctor");
	        listDataHeader.add("Eyewear through newpercept.com - online store");
	 
	        // Adding child data
	        List<String> listnicevision = new ArrayList<String>();
	        listnicevision.add("NiceVision");
	        listnicevision.add("Contact Lens Exam");
	        listnicevision.add("Prescription lenses");
	        listnicevision.add("Frame");
	        listnicevision.add("Laser visioncare");
	 
	        List<String> listspecialityeyecare = new ArrayList<String>();
	        listspecialityeyecare.add("Diabetic eyecare");
	 
	        List<String> listonlinestore = new ArrayList<String>();
	        listonlinestore.add("Does my plan allow me to apply benefits to eyewear purchase at newpercept.com");
	        listonlinestore.add("NewPercept.com overview");
	        listonlinestore.add("How does it work");
	        
	        listDataChild.put(listDataHeader.get(0), listnicevision); // Header, Child data
	        listDataChild.put(listDataHeader.get(1), listspecialityeyecare);
	        listDataChild.put(listDataHeader.get(2), listonlinestore);
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
    
	    private class GetWithInNetworkBenefits extends AsyncTask<Void, Void, Void> {

			/******** fetches and parse json data and add to list ***********/
			@Override
			protected Void doInBackground(Void... arg0) {
				 String GETINNETWORKBENEFITS = "http://10.0.3.2:8080/NewPerceptServer/service/benefitswithinnetwork/getbenefitswithinnetwork/username/Tom";
				
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();

				// Making a request to url and getting response
				String jsonStrBenefitsDiabetic = sh.makeServiceCall(GETINNETWORKBENEFITS,ServiceHandler.GET);

				Log.d("Response: logs for inbenefits----> ", "> " + jsonStrBenefitsDiabetic);

				if (jsonStrBenefitsDiabetic != null) {
					try {
						
						JSONObject jsonObj = new JSONObject(jsonStrBenefitsDiabetic);

						    niceeligibility = jsonObj.getString(TAG_niceeligibility);
						    nicefrequency = jsonObj.getString(TAG_nicefrequency);
						    nicecopay = jsonObj.getString(TAG_nicecopay);
						    nicecoverage = jsonObj.getString(TAG_nicecoverage);
						    nicedesc = jsonObj.getString(TAG_nicedesc);
						    contacteligibility = jsonObj.getString(TAG_contacteligibility);
						    contactfrequency = jsonObj.getString(TAG_contactfrequency);
						    contactcopay = jsonObj.getString(TAG_contactcopay);
						    contactcoverage = jsonObj.getString(TAG_contactcoverage);
						    contactdesc = jsonObj.getString(TAG_contactdesc);
						    prescriptioneligibility = jsonObj.getString(TAG_prescriptioneligibility);
						    prescriptionfrequency = jsonObj.getString(TAG_prescriptionfrequency);
						    prescriptioncopay = jsonObj.getString(TAG_prescriptioncopay);
						    prescriptioncoverage = jsonObj.getString(TAG_prescriptioncoverage);
						    prescriptiondesc = jsonObj.getString(TAG_prescriptiondesc);
						    frameeligibility = jsonObj.getString(TAG_frameeligibility);
						    framefrequency = jsonObj.getString(TAG_framefrequency);
						    framecopay = jsonObj.getString(TAG_framecopay);
						    framecoverage = jsonObj.getString(TAG_framecoverage);
						    framedesc = jsonObj.getString(TAG_framedesc);
						    lasereligibility = jsonObj.getString(TAG_lasereligibility);
						    laserfrequency = jsonObj.getString(TAG_laserfrequency);
						    lasercopay = jsonObj.getString(TAG_lasercopay);
						    lasercoverage = jsonObj.getString(TAG_lasercoverage);
						    laserdesc = jsonObj.getString(TAG_laserdesc);
						    diabeticeligibility = jsonObj.getString(TAG_diabeticeligibility);
						    diabeticfrequency = jsonObj.getString(TAG_diabeticfrequency);
						    diabeticcopay = jsonObj.getString(TAG_diabeticcopay);
						    diabeticcoverage = jsonObj.getString(TAG_diabeticcoverage);
						    diabeticdesc = jsonObj.getString(TAG_diabeticdesc);
						    plandesc = jsonObj.getString(TAG_plandesc);
						    comdesc = jsonObj.getString(TAG_comdesc);
						    howtodesc = jsonObj.getString(TAG_howdesc);
						     
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.e("ServiceHandler", "Couldn't get any data from the url");
				}

				return null;
			} //on background ends here
			
			 @Override
		        protected void onPostExecute(Void result) {
		            try{
//		            	  System.out.println("before listadapter---------------->");
//		            	  setspeciality(eligibility, frequency, copay, coverage, description);
//	           System.out.println("After set listadapter----------------->");
		            } catch(Exception e){
		            	e.printStackTrace();
		            }
		        }
			
		} //async class ends here
}
