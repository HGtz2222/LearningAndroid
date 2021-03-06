package com.example.clearableedittext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;



public class ClearableEditText extends LinearLayout {

	public ClearableEditText(Context context) {
		super(context);
		
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.clearable_edit_text, this);
	
		final EditText editText = (EditText)findViewById(R.id.editText);
		final Button clearBtn = (Button)findViewById(R.id.clearBtn);
		
		clearBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				editText.setText("");			
			}
		});
	}

}
