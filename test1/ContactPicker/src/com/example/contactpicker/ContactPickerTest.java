package com.example.contactpicker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactPickerTest extends Activity{
	public static final int PICK_CONTACT = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactpickertester);
		
		Button button = (Button)findViewById(R.id.pick_contact_button);
		button.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(Intent.ACTION_PICK, 
						Uri.parse("content://contacts/people/")); //TODO ������URI����Ҫ!!
				startActivityForResult(intent, PICK_CONTACT);
			}
			
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
			case (PICK_CONTACT):{
				if (resultCode == Activity.RESULT_OK){
					Uri contactData = data.getData();
					Cursor c = getContentResolver().query(contactData, null, null, null, null);
					c.moveToFirst();
					String name = c.getString(c.getColumnIndexOrThrow(
								ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
					c.close();
					Log.e("tz", name);
					TextView tv = (TextView)findViewById(R.id.selected_contact_textview);
					tv.setText(name);
				}
				break;
			}
			default:break;
		}
	}

	
}
