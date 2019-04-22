package com.yourspider.taxifarecalculator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ResultActivity extends Activity {
	
	String result="";
	TotalCalculator tc;
	double dis=0.0;
	double TotalPrice=0.0;
	double tm=0.0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		// Show the Up button in the action bar.
		
		
		setupActionBar();

		
		String value = getIntent().getExtras().getString("key");
		String newAddress = getIntent().getExtras().getString("add1");
		String secondAddress = getIntent().getExtras().getString("add2");
		
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //prefs.getString("prefUserBasePrice", defValue);
		String syncConnPrefBase ="Not set yet";
		String syncConnPrefUnit ="Not set yet";
		String syncConnPrefMin ="Not set yet";
		double base=0.0;
		double perKm=0.0;
		double perMin=0.0;
       
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        syncConnPrefBase = sharedPref.getString("prefUserBasePrice", "");
        syncConnPrefUnit = sharedPref.getString("prefUserUnitPrice", "");
        syncConnPrefMin = sharedPref.getString("prefUserMinPrice", "");
		try{
		base = Double.parseDouble(syncConnPrefBase);
		perKm = Double.parseDouble(syncConnPrefUnit);
		perMin = Double.parseDouble(syncConnPrefMin);
		}catch(NumberFormatException e){Log.w("&&&&&&&&&", "jjjjjjjjjjjjjjf");}
		
		if (value.compareTo("distance")!=0)
		{	
		
	
			try {
				newAddress = URLEncoder.encode(newAddress, "UTF-8");
				secondAddress = URLEncoder.encode(secondAddress, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MyJsonReader mjr = new MyJsonReader(newAddress);
			mjr.doit();
			MyJsonReader mjr2 = new MyJsonReader(secondAddress);
			mjr2.doit();
			
			MyDistanceJsonReader mdjr = new MyDistanceJsonReader(newAddress,secondAddress);
			mdjr.doit();
			
			result = mjr.showDetails() + "\n\n";
			result +="Second Address \n";
			result += mjr2.showDetails()+ "\n\n";
			result +="Distance and Duration \n";
			result += mdjr.showDetails();

			
			dis = (double)mdjr.distanceVal;
			tm = (double)mdjr.durationVal;

		}//if di
		else
		{	try{
		   dis = Double.parseDouble(newAddress);
            tm = dis - dis/4;
            result +="Distance:"+dis+"\n";
            result +="Time:"+tm+"\n";
            dis = dis*1000;
            tm = tm*60;
			} catch (NumberFormatException e){Log.w("**********>","test dis double");}
            
		}
		
		tc = new TotalCalculator(dis, tm, base, perMin, perKm);
		TotalPrice = tc.totalCal();
		
		result +="BasePrice:"+syncConnPrefBase + "\n";
		result +="Per Km Price:"+syncConnPrefUnit + "\n";
		result +="Per Min Price:"+syncConnPrefMin + "\n";
		result +="TOTAL:"+ TotalPrice + "\n";
        TextView textView = (TextView) findViewById(R.id.txtResult);
        textView.setText(result);
        

		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
