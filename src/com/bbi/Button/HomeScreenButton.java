package com.bbi.Button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

public class HomeScreenButton extends View {

	private int shadowColor = Color.parseColor("#000000");
	
	public HomeScreenButton(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public HomeScreenButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public HomeScreenButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init()
	{
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		setShadow(50, 50, 50, 50);
	}

	Paint paint;
	@Override
	public boolean onDragEvent(DragEvent event) {
		
		
		return super.onDragEvent(event);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
        paint.setShadowLayer(10, 10, 10, Color.YELLOW);
        paint.set
        paint.setStrokeWidth(30);
        // Important for certain APIs 
        setLayerType(LAYER_TYPE_SOFTWARE, paint);
        ViewGroup.LayoutParams param = getLayoutParams();
		canvas.drawRect(0+shadowLeft, 0+shadowTop, param.width-shadowRight, param.height-shadowBottom, paint);
		super.onDraw(canvas);
	}

	public int getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(int shadowColor) {
		this.shadowColor = shadowColor;
	}
	
	private int shadowLeft,shadowRight,shadowTop,shadowBottom;
	
	void setShadow(int left, int top, int right, int bottom)
	{
		shadowLeft = left;
		shadowTop = top;
		shadowRight = right;
		shadowBottom = bottom;
	}
}
