package com.example.testsensor;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView screen;
	private Button refresh;
	private WindowManager wm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> allSensor = sm.getSensorList(Sensor.TYPE_ALL);
		for (Sensor s : allSensor){
			Log.e("tz", "->" + s.getName());
		}
		Sensor s = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
		Log.e("tz", "=>" + s.getName());
		
		screen = (TextView)findViewById(R.id.screen);
		refresh = (Button)findViewById(R.id.btn_refresh);
		wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		
		refresh.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Display display = wm.getDefaultDisplay();
				int rotation = display.getRotation();
				Resources rs = getResources();
				switch (rotation){
					case Surface.ROTATION_0:
						Log.e("tz", "ROTATION_0");
						screen.setText(rs.getString(R.string.ROTATION_0));
						break;
					case Surface.ROTATION_90:
						Log.e("tz", "ROTATION_90");
						screen.setText(rs.getString(R.string.ROTATION_90));
						break;
					case Surface.ROTATION_180:
						Log.e("tz", "ROTATION_180");
						screen.setText(rs.getString(R.string.ROTATION_180));
						break;
					case Surface.ROTATION_270:
						Log.e("tz", "ROTATION_270");
						screen.setText(rs.getString(R.string.ROTATION_270));
						break;
					default:
						Log.e("tz", "error!");
						break;
				}
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
