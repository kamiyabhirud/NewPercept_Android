package com.example.activity;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.newpercept.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
/**** search by doctor name***/

public class MapsRouteActivity extends FragmentActivity implements LocationListener, LocationSource {
 
    GoogleMap map;
    ArrayList<LatLng> markerPoints;
	LatLng startlocdocname = ListOfRoutesSearchByDoctorNameActivity.sourcelatlng;
	LatLng endlocdocname = ListOfRoutesSearchByDoctorNameActivity.destinationlatlng;
	LatLng startlocofcname = ListOfRoutesActivity.sourcelatlngofcname;
	LatLng endlocofcname = ListOfRoutesActivity.destinationlatlngofcname;
	LatLng startloclocservices = ListOfRoutesSearchByLocationActivity.sourcelatlngloc;
	LatLng endloclocservices = ListOfRoutesSearchByLocationActivity.destinationlatlngloc;
//    LatLng startloc = new LatLng(38.5891667,-121.3016667);
//    LatLng endloc = new LatLng(34.0522222,-118.2427778);
	LatLng startloc;
	LatLng endloc;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsrouteactivity);
        getActionBar().setHomeButtonEnabled(true);
        
        // Initializing
        markerPoints = new ArrayList<LatLng>();
 
        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
 
        // Getting Map for the SupportMapFragment
        map = fm.getMap();
 
        if(map!=null){
 
            // Enable MyLocation Button in the Map
            map.setMyLocationEnabled(true);
 
            // Setting onclick event listener for the map
//            map.setOnMapClickListener(new OnMapClickListener() {
 
//                @Override
//                public void onMapClick(LatLng point) {
 
                    // Already two locations
                    if(markerPoints.size()>1){
                        markerPoints.clear();
                        map.clear();
                    }
 
                    if(startlocdocname != null || endlocdocname != null){
                    	startloc = startlocdocname;
                    	endloc = endlocdocname;
                    } else if(startlocofcname !=null || endlocofcname != null){
                    	startloc = startlocofcname;
                    	endloc = endlocofcname;
                    } else if(startloclocservices !=null || endloclocservices != null){
                    	startloc = startloclocservices;
                    	endloc = endloclocservices; 
                    	}
                    // Adding new item to the ArrayList
                    markerPoints.add(startloc);
                    markerPoints.add(endloc);
 
                    // Creating MarkerOptions
                    MarkerOptions options = new MarkerOptions();
 
                    // Setting the position of the marker
                    options.position(startloc);
                    options.position(endloc);
 
                    /**
                    * For the start location, the color of marker is GREEN and
                    * for the end location, the color of marker is RED.
                    */
                    if(markerPoints.size()==1){
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                    if(markerPoints.size()==2){
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                    }
 
                    // Add new marker to the Google Map Android API V2
                    map.addMarker(options);
 
                    // Checks, whether start and end locations are captured
                    if(markerPoints.size() >= 2){
                        LatLng origin = markerPoints.get(0);
                        LatLng dest = markerPoints.get(1);
 
                        // Getting URL to the Google Directions API
                        String url = getDirectionsUrl(origin, dest);
 
                        DownloadTask downloadTask = new DownloadTask();
 
                        // Start downloading json data from Google Directions API
                        downloadTask.execute(url);
                        
                        // Creating an instance of GeoPoint corresponding to latitude and longitude
//                        map.getUiSettings().setMyLocationButtonEnabled(false);
                        map.setLocationSource(this);
                        map.setMyLocationEnabled(true);
                        
//                     // Instantiates a new CircleOptions object and defines the center and radius
//                        CircleOptions circleOptions = new CircleOptions()
//                            .center(startloc)
//                            .radius(1); // In meters
//
//                        // Get back the mutable Circle
//                        Circle circle = map.addCircle(circleOptions);

                        map.addMarker(new MarkerOptions()
                        .position(startloc)
                        .title("Geolocation system")
                        .snippet("Your last current location which was available!"));
                //      .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location)));

                // adding the circle around marker
                map.addCircle(new CircleOptions()
                        .center(startloc)
                        .radius(7)
                        .strokeWidth(3)
                        .strokeColor(Color.RED)   // you can choose any color, I just set some randomly
                        .fillColor(Color.CYAN))
                        .setStrokeColor(Color.BLUE);
                
                        //focud camera on route
                        CameraPosition cameraPosition = new CameraPosition.Builder() 
                        .target(startloc)      // Sets the center of the map to LatLng (refer to previous snippet) 
                        .zoom(17)                   // Sets the zoom 
                        .bearing(90)                // Sets the orientation of the camera to east 
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees 
                        .build();                   // Creates a CameraPosition from the builder 
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); 
                    }
                    
//                } // on map click ends here
//            }); // on map click listner ends here
        }
        startlocdocname = null;
    	endlocdocname = null;
    	startlocofcname = null;
    	endlocofcname = null;
    	startloclocservices = null;
    	endloclocservices = null;
    }
 
    
    private String getDirectionsUrl(LatLng origin,LatLng dest){
 
        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;
 
        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;
 
        // Sensor enabled
        String sensor = "sensor=false";
 
        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;
 
        // Output format
        String output = "json";
 
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
 
        return url;
    }
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
 
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
 
            // Connecting to url
            urlConnection.connect();
 
            // Reading data from url
            iStream = urlConnection.getInputStream();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
 
            StringBuffer sb = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }
 
            data = sb.toString();
 
            br.close();
 
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
 
    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{
 
        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {
 
            // For storing data from web service
            String data = "";
 
            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }
 
        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
 
            ParserTask parserTask = new ParserTask();
 
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
 
    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
 
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
 
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
 
            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
 
                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }
 
        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
 
            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();
 
                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);
 
                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
 
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
 
                    points.add(position);
                }
 
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(6);
                lineOptions.color(Color.BLUE);
            }
 
            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }
/**************locationlistner methods *****/
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	/****location source methods implemented********/
	@Override
	public void activate(OnLocationChangedListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) 
		   {        
		      case android.R.id.home:            
		         Intent intent = new Intent(this, StepByStepDirectionLocationSearchActivity.class);            
		         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		         startActivity(intent);            
		         return true;        
		      default:            
		         return super.onOptionsItemSelected(item);    
		   }
	}
	
	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
 
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
}