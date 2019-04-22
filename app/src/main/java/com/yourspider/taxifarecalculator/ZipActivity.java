package com.yourspider.taxifarecalculator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;



public class ZipActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zip);
	}

	public void showResult(View view)
    {
		String newAddress = "768 College St, Toronto ON";
		String secondAddress = "715 Don Mills Rd, Toronto ON";
		
        //TextView textView = (TextView) findViewById(R.id.txtResult);
		 EditText edtTxt1 = (EditText) findViewById(R.id.editText1);
		 EditText edtTxt2 = (EditText) findViewById(R.id.editText2);
		 newAddress = edtTxt1.getText().toString();
		 secondAddress = edtTxt2.getText().toString();
		 
		Intent intent = new Intent(ZipActivity.this, ResultActivity.class);
		intent.putExtra("key","address");
		intent.putExtra("add1", newAddress);
		intent.putExtra("add2", secondAddress);
		startActivity(intent);
    }
	
}
