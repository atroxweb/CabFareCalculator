package com.yourspider.taxifarecalculator;



import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddressActivity extends Activity implements LocationListener{
	double latitude;
	double longitude;
	boolean runflag;
	String foundAddress;
	Location locationMy;
	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		foundAddress = "";
		runflag = true;
		latitude = 0.0;
		longitude = 0.0;
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_address);

	  //TextView textView = (TextView) findViewById(R.id.txtResult);
		 EditText edtTxt1 = (EditText) findViewById(R.id.editText1);

		 edtTxt1.setText("Please wait till the address appears here.");
		 
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
		
	    //Location loco = locationManager.getLastKnownLocation(Context.LOCATION_SERVICE);
		//latitude = loco.getLatitude(); 
		//longitude =loco.getLongitude();
		 Log.i("lastKnow", "Latitude: " + latitude + ", Longitude: " + longitude);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.address, menu);
	    return true;
	}

	@Override
	public void onLocationChanged(Location location) {
	    // TODO Auto-generated method stub

	   latitude =location.getLatitude();
	   longitude =location.getLongitude();
	   
	   if (runflag ==true)
	   {
		   runflag = false;
		   MyJsonReader mjr = new MyJsonReader(Double.toString(latitude), Double.toString(longitude));
			mjr.doit();
		   foundAddress = mjr.myFormatedAddress;
			EditText edtTxt1 = (EditText) findViewById(R.id.editText1);
			edtTxt1.setText(foundAddress);
			locationMy = location;
	
			Log.w("======>>>>>"," ADDRESS = "+foundAddress);
		   
	   }
 Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: " + longitude);
	    
	}

	public void showResult(View view)
    {
		
		//String newAddress = foundAddress;
		String secondAddress = "715 Don Mills Rd, Toronto ON";
		
        //TextView textView = (TextView) findViewById(R.id.txtResult);
		 EditText edtTxt1 = (EditText) findViewById(R.id.editText1);
		 EditText edtTxt2 = (EditText) findViewById(R.id.editText2);
		 edtTxt1.setText("Please wait till the address appears");
		// = edtTxt1.getText().toString();
		 secondAddress = edtTxt2.getText().toString();
		 
		Intent intent = new Intent(AddressActivity.this, ResultActivity.class);
		intent.putExtra("key","address");
		intent.putExtra("add1", foundAddress);
		intent.putExtra("add2", secondAddress);
		
		//try{
		//locationManager.removeUpdates(locationListener);
		//}catch(Exception e){
		//	Log.w("this is ", "OOO MMY GGGOD");
		//}

	        if(locationManager != null){
	          //  locationManager.removeUpdates(AddressActivity.this);
	        }
		
		startActivity(intent);
    }

	
	@Override
	public void onProviderDisabled(String provider) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
	    // TODO Auto-generated method stub
		Log.i("Pro Enabled", "Latitude: " + latitude + ", Longitude: " + longitude);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	}
	

}
