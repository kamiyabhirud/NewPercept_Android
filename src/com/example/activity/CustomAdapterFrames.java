	package com.example.activity;

	import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newpercept.R;

	public class CustomAdapterFrames extends BaseAdapter{

	    String [] result;
	    Context context;
	 int [] imageId;
	      private static LayoutInflater inflater=null;
	    public CustomAdapterFrames(FramesHomeActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
	        // TODO Auto-generated constructor stub
	        result=prgmNameList;
	        context=mainActivity;
	        imageId=prgmImages;
	         inflater = ( LayoutInflater )context.
	                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    }

	    @Override
	    public int getCount() {
	        // TODO Auto-generated method stub
	        return result.length;
	    }

	    @Override
	    public Object getItem(int position) {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    @Override
	    public long getItemId(int position) {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    public class Holder
	    {
	        TextView tv;
	        ImageView img;
	    }
	    @SuppressLint("ViewHolder")
		@Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        // TODO Auto-generated method stub
	        Holder holder=new Holder();
	        View rowView;

	             rowView = inflater.inflate(R.layout.framesbrandslist, null);
	             holder.tv=(TextView) rowView.findViewById(R.id.tvbrandsframes);
	             holder.img=(ImageView) rowView.findViewById(R.id.ivbrandsframes);

	         holder.tv.setText(result[position]);
	         holder.img.setImageResource(imageId[position]);

	         rowView.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                // TODO Auto-generated method stub
//	                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
//	            	   switch (position) {
//			            case 0:
							//create layout for dialog
							LayoutInflater li = LayoutInflater.from(context);
							View promptsView = li.inflate(R.layout.dialoggucciframes, null);
							
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
							
							// set prompts.xml to alertdialog builder
							alertDialogBuilder.setView(promptsView);
							
							
//							 set dialog message
							alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
								    public void onClick(DialogInterface dialog,int id) {
									dialog.cancel();
								    }
								  }).setCancelable(false).setPositiveButton("Find A Doctor that carries this brand",
										  new DialogInterface.OnClickListener() {
									    public void onClick(DialogInterface dialog,int id) {
									    	//goto page with list of routes
									    	Intent intent = new Intent(context,FindADocActivity.class);
									    	context.startActivity(intent);
									    	
										// get user input and set it to result
										// edit text
//										result.setText(userInput.getText());
									    }
									  });
							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();
			 
							// show it
							alertDialog.show();
//			            break;
			           
			           
//			            default:
			              // Nothing do!
//			          }
	            } //onlcikc ends here
	        });

	        return rowView;
	    }

	}