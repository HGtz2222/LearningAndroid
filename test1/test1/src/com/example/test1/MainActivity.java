package com.example.test1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("tz", "" + requestCode + ", " + resultCode);
	}


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
//				Log.e("tz", "-----> ");
//				Intent intent = new Intent(MainActivity.this, activity2.class);
//				startActivityForResult(intent, 2);
				Intent intent = new Intent(Intent.ACTION_ALL_APPS);
				ComponentName cn = intent.resolveActivity(getPackageManager());
				if (cn == null){
					Log.e("tz", "====");
					return ; 
				}
				startActivity(intent);
			}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
