package com.example.myview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class MyView extends View{
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredHeight = measureHeight(heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	private int measureWidth(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		int result = 500;
		
		if (specMode == MeasureSpec.AT_MOST){
			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY){
			result = specSize;
		}
		
		return result;
	}

	private int measureHeight(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		int result = 500;
		
		if (specMode == MeasureSpec.AT_MOST){
			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY){
			result = specSize;
		}
		
		return result;
	}

	private Paint textPaint;
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		Log.e("tz", "--------------<>");
		int h = getMeasuredHeight();
		int w = getMeasuredWidth();
		int x = w / 2;
		int y = h / 2;
		String displayText = "HGtz2222";
		float textWidth = textPaint.measureText(displayText);
		canvas.drawText(displayText, x - textWidth/2, y, textPaint);
	}

	private void init(){
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.BLACK);
	}
	
	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {	
		super(context, attrs, defStyleAttr);
		init();
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyView(Context context) {
		super(context);
		init();
	}


	
}
