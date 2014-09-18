package com.example.clearableedittext;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ClearableEditTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ClearableEditText text = new ClearableEditText(this);	
		setContentView(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clearable_edit_text, menu);
		return true;
	}

}
