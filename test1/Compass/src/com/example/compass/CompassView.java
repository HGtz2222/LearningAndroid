package com.example.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class CompassView extends View{
	private float bearing;
	private Paint markerPaint;
	private Paint textPaint;
	private Paint circlePaint;
	private String northString;
	private String eastString;
	private String southString;
	private String westString;
	private int textHeight;
	
	public float getBearing() {
		return bearing;
	}

	public void setBearing(float bearing) {
		this.bearing = bearing;
		sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
		super.dispatchPopulateAccessibilityEvent(event);
		if (!isShown()){
			return false;
		}
		String bearingStr = String.valueOf(bearing);
		if (bearingStr.length() > AccessibilityEvent.MAX_TEXT_LENGTH){
			bearingStr = bearingStr.substring(0, AccessibilityEvent.MAX_TEXT_LENGTH);
		}
		event.getText().add(bearingStr);
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 1. 绘制背景圆盘;
		int mMeasuredW = getMeasuredWidth();
		int mMeasuredH = getMeasuredHeight();
		int x = mMeasuredW / 2;
		int y = mMeasuredH / 2;
		int radius = Math.min(x, y);	
		canvas.drawCircle(x, y, radius, circlePaint);
		// 2. 调整 "上" 方向面对当前的方向; 
		canvas.save();
		canvas.rotate(-bearing, x, y);
		// 3. 绘制标记; 
		int textWidth = (int)textPaint.measureText("W");
		int cardinalX = x - textWidth / 2;
		int cardinalY = y - radius + textHeight;
		for (int i = 0; i < 24; ++i){
			canvas.drawLine(x, y-radius, x, y-radius+10, markerPaint);	
			canvas.save();
			canvas.translate(0, textHeight);
			if (i % 6 == 0){
				String dirStr = "";
				switch (i){
					case(0):{
						dirStr = northString;
						int arrowY = 2*textHeight;
						canvas.drawLine(x, arrowY, x-5, 3*textHeight, markerPaint);
						canvas.drawLine(x, arrowY, x+5, 3*textHeight, markerPaint);
						break;
					}
					case(6):{
						dirStr = eastString;
						break;
					}
					case(12):{
						dirStr = southString;
						break;
					}
					case(18):{
						dirStr = westString;
						break;
					}
				}
				canvas.drawText(dirStr, cardinalX, cardinalY, textPaint);
			}else if(i % 3 == 0){
				String angle = String.valueOf(i*15);
				float angleTextWidth = textPaint.measureText(angle);
				int angleX = (int)(x - angleTextWidth / 2);
				int angleY = y - radius + textHeight;
				canvas.drawText(angle, angleX, angleY, textPaint);
			}
			canvas.restore();
			canvas.rotate(15, x, y);
		}
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureW = measure(widthMeasureSpec);
		int measureH = measure(heightMeasureSpec);
		int d = Math.min(measureW, measureH);
		setMeasuredDimension(d, d);
	}

	private int measure(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		if (specMode == MeasureSpec.UNSPECIFIED){
			result = 200;
		}else{
			result = specSize;
		}
		return result;
	}

	public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CompassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CompassView(Context context) {
		super(context);
		init();
	}

	private void init(){
		setFocusable(true);
		bearing = 0.0f;
		Resources r 	= this.getResources();
		circlePaint 	= new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(r.getColor(R.color.background_color));
		circlePaint.setStrokeWidth(1);
		circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
	
		textPaint 		= new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(r.getColor(R.color.text_color));
		
		markerPaint		= new Paint(Paint.ANTI_ALIAS_FLAG);
		markerPaint.setColor(r.getColor(R.color.marker_color));
		
		textHeight = (int)textPaint.measureText("yY"); // TODO 这是神马?
		
		northString 	= r.getString(R.string.cardinal_north);
		eastString		= r.getString(R.string.cardinal_east);
		southString		= r.getString(R.string.cardinal_south);
		westString		= r.getString(R.string.cardinal_west);		
	}

}
