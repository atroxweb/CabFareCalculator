package com.yourspider.taxifarecalculator;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

public class MyDistanceJsonReader {

	
	String dAddress[];
	String oAddress[];
	String distanceTxt;
	String durationTxt;
	String mystatus;
	long distanceVal;
	long durationVal;

	
	JSONObject json;
    String str;
    
    HttpResponse response;
    HttpClient myClient; 
    HttpPost myConnection;
   
    MyDistanceJsonReader (String firstAddress, String secondAddress)
    {   
    	distanceTxt="";
    	durationTxt="";
    	mystatus="";
    	distanceVal=0;
    	durationVal=0;
    	str = "";
    	Log.w("****",">> GOT HERE  <<");
    	
       	mystatus = "wrong";
    	json = null;
    	myClient = new DefaultHttpClient();
    	myConnection = new HttpPost("http://maps.googleapis.com/maps/api/distancematrix/json?origins="+firstAddress+"&destinations="+secondAddress+"&mode=driving&language=en-EN&sensor=false");
    	Log.w("JSON Reading", "just read the URL");
    	
    }
    public String showDetails()
    {
    	String result = "";
    	//result +="Status: "+mystatus+" \n";
    	//result += "First Address: "+dAddress[0]+" \n";
    	//result += "Second Address: "+oAddress[0]+" \n";
    	//result += "Distance: "+distanceVal+" \n";
    	result += "Distance: "+distanceTxt+" \n";
    	//result += "Duration: "+durationVal+" \n";
    	result += "Duration: "+durationTxt+" \n";

    	return result;
    }
    
    
    
    public void doit()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    	
    	try 
	    {
	        response = myClient.execute(myConnection);
	        Log.w("JSON Reading", "Just executed the connection");
	        str = EntityUtils.toString(response.getEntity(), "UTF-8");
	        
	         
	    } 
	    catch (ClientProtocolException e) 
	    {
	        e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
    	catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	     
    	 try
 	    {

    		 DistanceFinder dFinder = new Gson().fromJson(str, DistanceFinder.class);
    		 
    		 mystatus=dFinder.getStatus();
    	     dAddress=dFinder.getDestination_addresses();
    	     oAddress=dFinder.getOrigin_addresses();
    	     //RowElement[] 
    	     distanceVal=(long) dFinder.getRows()[0].getElements()[0].getDistance().getValue();
    	     distanceTxt=(String) dFinder.getRows()[0].getElements()[0].getDistance().getText();
 
    	     durationVal=(long) dFinder.getRows()[0].getElements()[0].getDuration().getValue();
    	     durationTxt=(String) dFinder.getRows()[0].getElements()[0].getDuration().getText();
    		 
	        Log.w("MyStatus--",""+mystatus );
	         Log.w("D Address--",""+dAddress[0]);
	         Log.w("O Address--",""+oAddress[0]);
	         Log.w("Distance--",""+distanceVal);   	
	         Log.w("DistancT--",""+distanceTxt);
	         Log.w("Duration--",""+durationVal);   	
	         Log.w("DurationT--",""+durationTxt);
	        
        
 	     } 
 	    catch ( Exception e) 
 	    {
 	        e.printStackTrace();    
 	        
 	    }

    }
	
	
}
