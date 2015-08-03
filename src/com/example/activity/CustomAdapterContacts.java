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

	public class CustomAdapterContacts extends BaseAdapter{

	    String [] result;
	    Context context;
	 int [] imageId;
	      private static LayoutInflater inflater=null;
	    public CustomAdapterContacts(ContactsHomeActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
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
	          
									    	Intent intent = new Intent(context,FindADocActivity.class);
									    	context.startActivity(intent);
			
	            } //onlcikc ends here
	        });

	        return rowView;
	    }

	}