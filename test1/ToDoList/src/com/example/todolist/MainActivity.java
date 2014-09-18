package com.example.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ArrayList<String> todoItems = new ArrayList<String>();

        ToDoListItemView listView = (ToDoListItemView)findViewById(R.id.listView);
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, todoItems);
        listView.setAdapter(aa);
        
        final EditText editText = (EditText)findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.e("tz", "onKey");
				if (event.getAction() == KeyEvent.ACTION_DOWN){
					if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
						Log.e("tz", "key down");
						todoItems.add(editText.getText().toString());
						aa.notifyDataSetChanged();
						editText.setText("");
						return true;
					}
				}
				return false;
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
