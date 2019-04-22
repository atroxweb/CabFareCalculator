package com.yourspider.taxifarecalculator;

import java.text.DecimalFormat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private static final int OK_MENU_ITEM = Menu.FIRST;
	String newAnswerString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        /**************************************************************************************/

        MenuItem userMenu = (MenuItem)findViewById(R.id.action_settings);

        boolean x;
      //  x = onOptionsItemSelected(userMenu); 
 
        /****************************************************************************************/
        
    
      
        

        
    }     
    
    
    public void byZip(View view)
    {
    	Intent intent = new Intent(MainActivity.this, ZipActivity.class);
		intent.putExtra("perform","zip");
		startActivity(intent);
    }
    public void byAddress(View view)
    {
    	Intent intent = new Intent(MainActivity.this, AddressActivity.class);
		intent.putExtra("perform","address");
		startActivity(intent);
    }
    public void byDistance(View view)
    {
    	Intent intent = new Intent(MainActivity.this, DistanceActivity.class);
		intent.putExtra("perform","city");
		startActivity(intent);
    }
    public void userSetting(View view)
    {
    	Intent intent = new Intent(MainActivity.this, SettingActivity.class);
		intent.putExtra("perform","Setting");
		startActivity(intent);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    double RoundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
    return Double.valueOf(df2.format(val));
    }

    
    private void showMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
      }



      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
    	  
    	  Intent intent = new  Intent(MainActivity.this, SettingActivity.class);
    	  startActivity(intent);
    	  return super.onOptionsItemSelected(item);
      }
}
