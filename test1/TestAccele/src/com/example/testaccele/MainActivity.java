package com.example.testaccele;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView screen;
	private SensorEventListener listener;
	private SensorManager sm;
	private Sensor sensor;
	private Button clear;
	private int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		screen = (TextView)findViewById(R.id.screen);
		
		sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		listener = new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
					Log.e("tz", "value " + event.values[0] + ", " + event.values[1] + ", " + event.values[2]);
					//screen.setText("value " + event.values[0] + ", " + event.values[1] + ", " + event.values[2]);
					float total = event.values[0] * event.values[0] + event.values[1] * event.values[1] + (event.values[2] - 9.8f) * (event.values[2] - 9.8f); 
					Log.e("tz2", "" + total);
					if (total > 200.0f){
						count ++;
						screen.setText("" + count);
					}
				}
			}		
			@Override
			public void onAccuracyChanged(Sensor arg0, int arg1) {
				
			}
		};
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		clear = (Button)findViewById(R.id.btn_clear);
		clear.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				count = 0;
				screen.setText("" + count);				
			}
			
		});		
	}

	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sm.unregisterListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
