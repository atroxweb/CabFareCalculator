package com.yourspider.taxifarecalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.os.StrictMode;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.widget.Toast;


public class MyJsonReader {

	double mylat;
	double mylng;
	String mystatus;
	String myPostalCode;
	String myFormatedAddress;
	String myCountry;
	String myState;
	String myCity;
	String mytype;
	
	JSONObject json;
    String str;
    
    HttpResponse response;
    HttpClient myClient; 
    HttpPost myConnection;
   
    MyJsonReader(String addr)
    {   
    	mylat = 0.0;
    	mylng = 0.0;
    	str = "";
    	myPostalCode="";
    	myFormatedAddress="";
    	myCountry="";
    	myState="";
    	myCity="";
    	mytype="";
    	
    	mystatus = "wrong";
    	json = null;
    	myClient = new DefaultHttpClient();
    	myConnection = new HttpPost("http://maps.googleapis.com/maps/api/geocode/json?address="+addr+"&sensor=false");
    	
    	Log.w("JSON Reading", "just read the URL");
    }
    MyJsonReader(String latFun, String lngFun)
    {   
    	mylat = 0.0;
    	mylng = 0.0;
    	str = "";
    	myPostalCode="";
    	myFormatedAddress="";
    	myCountry="";
    	myState="";
    	myCity="";
    	mytype="";
    	
    	mystatus = "wrong";
    	json = null;
    	myClient = new DefaultHttpClient();
    	//myConnection = new HttpPost("http://maps.googleapis.com/maps/api/geocode/json?address="+addr+"&sensor=false");
    	myConnection = new HttpPost("http://maps.googleapis.com/maps/api/geocode/json?latlng="+latFun+","+lngFun+"&sensor=false");
    	Log.w("JSON Reading", "secondConstructor");
    }
    public String showDetails()
    {
    	String result;
		result = "Latitude: "+ mylat +" \n";
		result += "Longitude: "+ mylng +" \n";
		//result += "Status: "+ mystatus +" \n";
		//result += "PostalCode: "+  myPostalCode +" \n";
		result += "FormatedAddress: "+ myFormatedAddress +" \n";
		//result += "Country: "+ myCountry +" \n";
		//result += "State: "+ myState +" \n";
		//result += "City: "+ myCity +" \n";
		result += "Type: "+ mytype +" \n";
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

    		 
    		 GeocodeResponse geor = new Gson().fromJson(str, GeocodeResponse.class);
    		 
    		 geor.getResults().size();
    		 mystatus=geor.getStatus();
    		 
    		 Log.w("STATUS AFTER READ",""+mystatus);
    		 
    		 List <Geocode>l = geor.getResults();
    		 Geometry gg=l.get(0).getGeometry();
    		 mylat=(double)gg.getLocation().getLat();
    		 mylng=(double)gg.getLocation().getLng();
    		 String lctype = (String)gg.getLocation_type();
    		 myFormatedAddress = (String)l.get(0).getFormatted_address();
    		 Collection<String> ptype = l.get(0).getTypes();
    		 mytype = ptype.toString();
    		 
    		 
	         Log.w("ADDRESS LAT",""+mylat );
	         Log.w("ADDRESS LNG",""+mylng);
	         Log.w("ADDRESS TYP",""+mytype);
	         Log.w("ADDRESS ADD",""+myFormatedAddress);   	
	         Log.w("LOCATION TYPE",""+lctype);
    		 
	         Collection<AddressComponent> ac=l.get(0).getAddress_components();	
	    
	         
    		 Iterator<AddressComponent> itr = ac.iterator();
    		 AddressComponent aaa;
    		 String sss;
    	      while (itr.hasNext()) {
    	    	  
    	    	  aaa = itr.next();
    	    	  sss = (String)aaa.getShort_name();
    	    	  Log.w("ADDRESS SHT NM",""+sss);
    	    	  sss = "";
    	         Collection<String> str =  aaa.getTypes();
    	         Iterator<String> it =  str.iterator();
    	         
    	         while(it.hasNext())
    	         {   sss=it.next();
    	              Log.w("ADDRESS TYPE",sss);
    	        	 if(sss.compareToIgnoreCase("country")==0){myCountry=aaa.getLong_name();}
    	        	 else if(sss.compareToIgnoreCase("locality")==0){myCity=aaa.getLong_name();}
    	        	 else if(sss.compareToIgnoreCase("administrative_area_level_1")==0){myState=aaa.getLong_name();}
    	         }
   
    	      }


    	         Log.w("ADDRESS CNT",""+myCountry);
    	         Log.w("ADDRESS STT",""+myState);
    	         Log.w("ADDRESS CTY",""+myCity);
        
 	     } 
 	    catch ( Exception e) 
 	    {
 	        e.printStackTrace();    
 	        
 	    }
 

	   
    	
    }
}
