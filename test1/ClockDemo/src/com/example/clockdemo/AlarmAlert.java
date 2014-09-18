package com.example.clockdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmAlert extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new AlertDialog.Builder(AlarmAlert.this)
						.setTitle("ʱ�䵽!")
						.setMessage("�ر�����?")
						.setPositiveButton("ֹͣ", 
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int whichButton) {
										System.exit(0);
										android.os.Process.killProcess(android.os.Process.myPid());										
									}
						}).show();
	}
	
}
