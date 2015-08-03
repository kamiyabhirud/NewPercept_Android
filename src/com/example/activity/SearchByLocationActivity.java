package com.example.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.newpercept.R;
public class SearchByLocationActivity extends Fragment  implements OnItemSelectedListener{

	static String zipcode = "";
	static String statename = "";
	static String framebrand = "";
	EditText etzipcode;
	EditText etstate;
//	EditText etframebrand;
	String brandselected = "";
	
	static String s_eyeexam = "null";
	static String s_extendedhrs = "null";
	static String s_visiontherapy = "null";
	static String s_smallchildren = "null";
	static String s_advance = "null";
	static String s_expresseyewear = "null";
	static String s_laservisioncare = "null";
	static String s_bigchildren = "null";
	static String s_preventativeeyecare = "null";
	static String s_specialoffers = "null";
	
	static String p_glasses = "null";
	static String p_featuredframebrand = "null";
	static String p_contactlens = "null";
	static String p_sportseyewear = "null";
	static String p_unitylenses = "null";
	static String p_hardtofit = "null";
	static String p_otisandpiper = "null";
	static String p_safetyprotec = "null";
	static String p_lowvision = "null";
	static String p_googleglasslenses = "null";
	
	//listof chekcbox
	CheckBox chks_eyeexam ;
	CheckBox chks_extendedhrs ;
	CheckBox chks_visiontherapy ;
	CheckBox chks_smallchildren ;
	CheckBox chks_advance ;
	CheckBox chks_expresseyewear ;
	CheckBox chks_laservisioncare ;
	CheckBox chks_bigchildren ;
	CheckBox chks_preventativeeyecare ;
	CheckBox chks_specialoffers ;
	
	CheckBox chkp_glasses ;
	CheckBox chkp_featuredframebrand ;
	CheckBox chkp_contactlens ;
	CheckBox chkp_sportseyewear ;
	CheckBox chkp_unitylenses ;
	CheckBox chkp_hardtofit ;
	CheckBox chkp_otisandpiper ;
	CheckBox chkp_safetyprotec ;
	CheckBox chkp_lowvision ;
	CheckBox chkp_googleglasslenses ;
			
	Spinner spinnerframebrands;
	 View rootView;
//	 TextView selVersion;
	 private String[] framebrandlist = { "Brand1","Brand2" ,"Brand3","Brand4","Brand5"};
	 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.searchbylocationlayout, container, false);
    	etzipcode   = (EditText) rootView.findViewById(R.id.et_zipcode);
		etstate   = (EditText) rootView.findViewById(R.id.et_state);
//		etframebrand   = (EditText) rootView.findViewById(R.id.et_framebrand);
		 chks_eyeexam = (CheckBox) rootView.findViewById(R.id.chkeyeexam);
		 chks_extendedhrs = (CheckBox) rootView.findViewById(R.id.chkextendedhrs);
		 chks_visiontherapy = (CheckBox) rootView.findViewById(R.id.chkvisiontherapy);
		 chks_smallchildren = (CheckBox) rootView.findViewById(R.id.chkchildrensmall);
		 chks_advance = (CheckBox) rootView.findViewById(R.id.chkadvanceeyeexam);
		 chks_expresseyewear = (CheckBox) rootView.findViewById(R.id.chkexpresseyewear);
		 chks_laservisioncare = (CheckBox) rootView.findViewById(R.id.chklaservisioncare);
		 chks_bigchildren = (CheckBox) rootView.findViewById(R.id.chkchildrenbig);
		 chks_preventativeeyecare = (CheckBox) rootView.findViewById(R.id.chkpreventative);
		 chks_specialoffers = (CheckBox) rootView.findViewById(R.id.chkspecialoffers);
		
		 chkp_glasses = (CheckBox) rootView.findViewById(R.id.chkglasses);
		 chkp_featuredframebrand = (CheckBox) rootView.findViewById(R.id.chkfeaturedframebrand);
		 chkp_contactlens = (CheckBox) rootView.findViewById(R.id.chkcontactlens);
		 chkp_sportseyewear = (CheckBox) rootView.findViewById(R.id.chksportseyewear);
		 chkp_unitylenses = (CheckBox) rootView.findViewById(R.id.chkunitylenses);
		 chkp_hardtofit = (CheckBox) rootView.findViewById(R.id.chkhardtofit);
		 chkp_otisandpiper = (CheckBox) rootView.findViewById(R.id.chkotiseyewear);
		 chkp_safetyprotec = (CheckBox) rootView.findViewById(R.id.chksafety);
		 chkp_lowvision = (CheckBox) rootView.findViewById(R.id.chklowvision);
		 chkp_googleglasslenses = (CheckBox) rootView.findViewById(R.id.chkgoogleglasses);
		
		 /****listners for services checkbox***/
		 addListenerOnChkEyeExam();
		 addListenerOnChkExtendedHrs();
		 addListenerOnChkVisionTherapy();
		 addListenerOnChkSmallChildren();
		 addListenerOnChkAdvance();
		 addListenerOnChkExpressEyewear();
		 addListenerOnChkLaserVisionCare();
		 addListenerOnChkBigChildren();
		 addListenerOnChkPreventativeEyeCare();
		 addListenerOnChkSpecialOffers();
		 
		 /****listners for products checkbox***/
		    addListenerOnChkGlasses();
			addListenerOnChkFeaturedFrameBrands();
			addListenerOnChkContactLens();
			addListenerOnChkSportsEyewear();
			addListenerOnChkUnityLenses();
			addListenerOnChkHardToFit();
			addListenerOnChkOtisAndPiper();
			addListenerOnChkSafetyProtec();
			addListenerOnChkLowVision();
			addListenerOnChkGoogleGlassLenses();
		 
	// after pressing GOTO SEARCH PAGE button
	Button buttonsearchbyofficename = (Button) rootView.findViewById(R.id.button_searchbylocation);
	buttonsearchbyofficename.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// goto result page after clicking
					
					zipcode  = etzipcode.getText().toString();
					statename  = etstate.getText().toString();
//					framebrand  = String.valueOf(spinnerframebrands.getSelectedItem());
//					= etframebrand.getText().toString();
									
					Intent resultsearchbylocation = new Intent(getActivity(), ResultSearchByLocationActivity.class);
					startActivity(resultsearchbylocation);
				}
				
				
			}); //search button click ends here
       
        
        spinnerframebrands = (Spinner) rootView.findViewById(R.id.dropdownframe);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, framebrandlist);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerframebrands.setAdapter(adapter_state);
        spinnerframebrands.setOnItemSelectedListener(this);
        
        return rootView;
    }//oncreate ends here
	
	/****methods for services ***/
	public void addListenerOnChkEyeExam() {
		 chks_eyeexam.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_eyeexam = "Eye Exam";
			} }		});  }
	
	public void addListenerOnChkExtendedHrs() {
		chks_extendedhrs.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_extendedhrs = "Extended Hours";
			} }		});  }
	
	public void addListenerOnChkVisionTherapy() {
		chks_visiontherapy.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_visiontherapy = "Vision Therapy";
			} }		});  }
	
	public void addListenerOnChkSmallChildren() {
		chks_smallchildren.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_smallchildren = "Children ages between 0 and 3";
			} }		});  }
	
	public void addListenerOnChkAdvance() {
		chks_advance.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_advance = "Advance";
			} }		});  }
	
	public void addListenerOnChkExpressEyewear() {
		chks_expresseyewear.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_expresseyewear = "Express Eyewear";
			} }		});  }
	
	public void addListenerOnChkLaserVisionCare() {
		chks_laservisioncare.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_laservisioncare = "Laser Vision Care";
			} }		});  }
	
	public void addListenerOnChkBigChildren() {
		chks_bigchildren.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_bigchildren = "Children ages between 3 and 5";
			} }		});  }
	
	public void addListenerOnChkPreventativeEyeCare() {
		chks_preventativeeyecare.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_preventativeeyecare = "Preventative Eye Care";
			} }		});  }
	
	public void addListenerOnChkSpecialOffers() {
		chks_specialoffers.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				s_specialoffers = "Special Offers and Savings";
			} }		});  }

	public void addListenerOnChkGlasses() {
		chkp_glasses.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_glasses = "Glasses";
			} }		});  }
	
	public void addListenerOnChkFeaturedFrameBrands() {
		chkp_featuredframebrand.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_featuredframebrand = "Featured Frame Brands";
			} }		});  }
	
	public void addListenerOnChkContactLens() {
		chkp_contactlens.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_contactlens = "Contact Lenses";
			} }		});  }
	
	public void addListenerOnChkSportsEyewear() {
		chkp_sportseyewear.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_sportseyewear = "Sports Eyewear";
			} }		});  }
	
	public void addListenerOnChkUnityLenses() {
		chkp_unitylenses.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_unitylenses = "Unity Lenses";
			} }		});  }
	
	public void addListenerOnChkHardToFit() {
		chkp_hardtofit.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_hardtofit = "Hard-to-fit contacts";
			} }		});  }
	
	public void addListenerOnChkOtisAndPiper() {
		chkp_otisandpiper.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_otisandpiper = "Otis and Piper Eyewear";
			} }		});  }
	
	public void addListenerOnChkSafetyProtec() {
		chkp_safetyprotec.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_safetyprotec = "Safety Pro Tec Eyewear";
			} }		});  }
	
	public void addListenerOnChkLowVision() {
		chkp_lowvision.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_lowvision = "Low Vision";
			} }		});  }
	
	public void addListenerOnChkGoogleGlassLenses() {
		chkp_googleglasslenses.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				p_googleglasslenses = "Google Glass Lenses";
			} }		});  }
	
	
	 public void onItemSelected(AdapterView<?> parent, View view, int position,  long id) {

			  spinnerframebrands.setSelection(position);
			  framebrand = (String) spinnerframebrands.getSelectedItem();
//			  selVersion.setText("Selected Android OS:" + selState);
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

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
