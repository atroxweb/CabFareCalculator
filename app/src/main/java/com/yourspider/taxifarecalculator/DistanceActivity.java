package com.yourspider.taxifarecalculator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class DistanceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distance);
	}

	public void showResult(View view)
    {
		String newAddress = "768 College St, Toronto ON";
		String secondAddress = "715 Don Mills Rd, Toronto ON";
		
        //TextView textView = (TextView) findViewById(R.id.txtResult);
		 EditText edtTxt1 = (EditText) findViewById(R.id.editText1);
		 newAddress = edtTxt1.getText().toString();
		
		
		Intent intent = new Intent(DistanceActivity.this, ResultActivity.class);
		intent.putExtra("key","distance");
		intent.putExtra("add1", newAddress);
		intent.putExtra("add2", secondAddress);
		startActivity(intent);
		
		
		
    }

}
