package com.example.clockdemo;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class ClockDemo extends Activity {
	private Button mSet;
	Calendar mCalender = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock_demo);
		ObjectPool.mAlarmHelper = new AlarmHelper(this);
		mSet = (Button)findViewById(R.id.mSet);
		setListener();
	}

	private void setListener() {
		mSet.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				mCalender.setTimeInMillis(System.currentTimeMillis());				
				int mHour = mCalender.get(Calendar.HOUR_OF_DAY);
				int mMinute = mCalender.get(Calendar.MINUTE);
				new TimePickerDialog(ClockDemo.this, 
						new TimePickerDialog.OnTimeSetListener(){

							@Override
							public void onTimeSet(TimePicker view, int hour,
									int minute) {
								mCalender.setTimeInMillis(System.currentTimeMillis());
								mCalender.set(Calendar.HOUR_OF_DAY, hour);
								mCalender.set(Calendar.MINUTE, minute);
								mCalender.set(Calendar.SECOND, 0);
								mCalender.set(Calendar.MILLISECOND, 0);
								// 设置闹钟!!
								ObjectPool.mAlarmHelper.openAlarm(32, "ddd", 
										"ffff", mCalender.getTimeInMillis());
							}
					
				}, mHour, mMinute, true).show();
			}
			
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clock_demo, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			showBackDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showBackDialog(){
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示")
				.setMessage("是否退出?")
				.setPositiveButton("sure",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								System.exit(0);
								android.os.Process
										.killProcess(android.os.Process.myPid());

								dialog.dismiss();
							}
						})
				.setNegativeButton("cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		AlertDialog ad = builder.create();
		ad.show();
	}
}
