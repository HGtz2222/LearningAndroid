package com.example.todolist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ListView;


public class ToDoListItemView extends ListView{
	
	public ToDoListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ToDoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ToDoListItemView(Context context) {
		super(context);
		init();
	}
	
	private Paint 	marginPaint;
	private Paint 	linePaint;
	private int 	paperColor;
	private float	margin;

	private void init(){
		Resources myRes = getResources();
		
		// 创建好一个绘制边界的画刷; 
		marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(myRes.getColor(R.color.notepad_margin));
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(myRes.getColor(R.color.notepad_lines));
		
		paperColor = myRes.getColor(R.color.notepad_paper);
		margin = myRes.getDimension(R.dimen.notepad_margin);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(paperColor);
		canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
		
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		//canvas.restore();
		//canvas.save();
		canvas.translate(margin, 0);
		
		super.onDraw(canvas);
		//canvas.restore();
	}

}
