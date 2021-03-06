package com.example.calc;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigDecimal;

public class MainActivity extends Activity {
	private final static int OP_PLUS = 1;
	private final static int OP_MINUS = 2;
	private final static int OP_MUTLI = 3;
	private final static int OP_DIVIDE = 4;
	private final static int OP_EQUAL = 5;
	
	private Button num0;
	private Button num1;
	private Button num2;
	private Button num3;
	private Button num4;
	private Button num5;
	private Button num6;
	private Button num7;
	private Button num8;
	private Button num9;
	private Button plus;
	private Button minus;
	private Button multi;
	private Button divide;
	private Button equal;
	private Button point;
	private Button clear;
	private Button allClear;
	private EditText screen; 
	
	private boolean inputDone = false;
	private String lastNum = "0";
	private int curOperation = OP_EQUAL;
	
	private void add_num(String num){
		String result = "";
		if (!inputDone){
			result = screen.getText().toString();
		}else{
			inputDone = false; // 如果数据开始输入, 则重新计算标志; 
		}
		result += num;
		screen.setText(result);
	}
	
	private String calc(){
		BigDecimal result = new BigDecimal(0.0);
		try{
			BigDecimal op_num1 = new BigDecimal(lastNum);
			String curText = screen.getText().toString();
			if (curText == null || curText.equals("")){
				Log.e("tz", "curText is null");
				return "0.0";	
			}	
			BigDecimal op_num2 = new BigDecimal(curText);
			if (curOperation == OP_PLUS){
				Log.e("tz", "In calc Plus");
				result = op_num1.add(op_num2);
			}else if(curOperation == OP_MINUS){
				result = op_num1.subtract(op_num2);
			}else if(curOperation == OP_MUTLI){
				result = op_num1.multiply(op_num2);
			}else if (curOperation == OP_DIVIDE){
				if (op_num2.compareTo(BigDecimal.ZERO) == 0){
					result = op_num1.divide(op_num2);	
				}else{
					Log.e("tz", "Cannot divide 0!");
					result = BigDecimal.valueOf(0.0);
				}
			}else if (curOperation == OP_EQUAL){
				Log.e("tz", "In calc Equal");
				result = op_num2;
			}else{
				Log.e("tz", "Error Oper!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "" + result;
	}
	
	private void init_ctrl(){
		screen = (EditText)findViewById(R.id.screen);
		num0 = (Button)findViewById(R.id.btn_0);
		num1 = (Button)findViewById(R.id.btn_1);
		num2 = (Button)findViewById(R.id.btn_2);
		num3 = (Button)findViewById(R.id.btn_3);
		num4 = (Button)findViewById(R.id.btn_4);
		num5 = (Button)findViewById(R.id.btn_5);
		num6 = (Button)findViewById(R.id.btn_6);
		num7 = (Button)findViewById(R.id.btn_7);
		num8 = (Button)findViewById(R.id.btn_8);
		num9 = (Button)findViewById(R.id.btn_9);
		plus = (Button)findViewById(R.id.btn_plus);
		minus = (Button)findViewById(R.id.btn_minus);
		multi = (Button)findViewById(R.id.btn_multi);
		divide = (Button)findViewById(R.id.btn_divide);
		point = (Button)findViewById(R.id.btn_point);
		clear = (Button)findViewById(R.id.btn_clear);
		allClear = (Button)findViewById(R.id.btn_ac);
		equal = (Button)findViewById(R.id.btn_equal);
	}
	
	private String getNumById(int id){
		final HashMap<Integer, String> table = new HashMap<Integer, String>();
		table.put(R.id.btn_0, "0");
		table.put(R.id.btn_1, "1");
		table.put(R.id.btn_2, "2");
		table.put(R.id.btn_3, "3");
		table.put(R.id.btn_4, "4");
		table.put(R.id.btn_5, "5");
		table.put(R.id.btn_6, "6");
		table.put(R.id.btn_7, "7");
		table.put(R.id.btn_8, "8");
		table.put(R.id.btn_9, "9");
		table.put(R.id.btn_point, ".");
		return table.get(id);
	}

	private int getOperationById(int id){
		final HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		table.put(R.id.btn_plus, OP_PLUS);
		table.put(R.id.btn_minus, OP_MINUS);
		table.put(R.id.btn_multi, OP_MUTLI);
		table.put(R.id.btn_divide, OP_DIVIDE);
		table.put(R.id.btn_equal, OP_EQUAL);
		return table.get(id);
	}
	
	private void init_event(){
		// 1. 绑定数字键的listener
		Button.OnClickListener numListener = new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Button self = (Button)arg0;
				String num = getNumById(self.getId());
				add_num(num);				
			}
		};
		num0.setOnClickListener(numListener);
		num1.setOnClickListener(numListener);
		num2.setOnClickListener(numListener);
		num3.setOnClickListener(numListener);
		num4.setOnClickListener(numListener);
		num5.setOnClickListener(numListener);
		num6.setOnClickListener(numListener);
		num7.setOnClickListener(numListener);
		num8.setOnClickListener(numListener);
		num9.setOnClickListener(numListener);
		point.setOnClickListener(numListener);
		// 2. 绑定清除按钮的listener
		clear.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				screen.setText("");
			}
		});
		
		allClear.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				screen.setText("");
				lastNum = "0.0";
				inputDone = false;
				curOperation = OP_EQUAL;
			}
		});
		// 3. 绑定运算符的listener
		Button.OnClickListener opListener = new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// 1. 计算上一次结果;
				String rs = calc();
				Log.e("tz", "---> rs " + rs);
				// 2. 区分哪个按钮并记录; 
				Button self = (Button)arg0;
				curOperation = getOperationById(self.getId());
				// 3. 结束当前输入, 等待下次输入; 
				inputDone = true;
				// 4. 保存当前数值为lastNum; 
				screen.setText(rs);
				lastNum = rs;
			}
		};
		plus.setOnClickListener(opListener);
		minus.setOnClickListener(opListener);
		multi.setOnClickListener(opListener);
		divide.setOnClickListener(opListener);
		equal.setOnClickListener(opListener);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init_ctrl();
		init_event();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
