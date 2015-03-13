package com.bbi.Button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ShadowView extends View {

	private int shadowColor = Color.parseColor("#000000");
	private int radius = 15;

	private final int gap = 10;
	
	public ShadowView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public ShadowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public ShadowView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init()
	{
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		setShadow(10, 10,10, 10);
	}

	Paint paint;
	@Override
	public boolean onDragEvent(DragEvent event) {
		
		
		return super.onDragEvent(event);
	}
	
	private void validateValues()
	{
		
        System.out.println(shadowBottom+"|"+shadowTop+":"+(parseBinary(shadowBottom)^parseBinary(shadowTop)^parseBinary(shadowLeft)^parseBinary(shadowRight)));
        System.out.println(shadowLeft+"|"+shadowRight+":"+(parseBinary(shadowLeft)^parseBinary(shadowRight)));
        
	}
	
	private int parseBinary(int value)
	{
		return (value > 0)?1:0; 
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		drawWithShadow1(canvas);
		super.onDraw(canvas);
	}
	
	private void drawWithShadow1(Canvas canvas)
	{
		paint.setShadowLayer(15, 0, 0, shadowColor);
		if(getBackground() != null)
		{
	        setLayerType(LAYER_TYPE_SOFTWARE, paint);
	        paint.setColor(Color.TRANSPARENT);
	        ViewGroup.LayoutParams param = getLayoutParams();
	        //paint.setColor(Color.TRANSPARENT);
	        paint.setStyle(Style.FILL);
	        
			canvas.drawRect(0+shadowLeft+gap*parseBinary(shadowLeft),
							0+shadowTop+gap*parseBinary(shadowTop),
					param.width-shadowRight-gap*parseBinary(shadowRight),
					param.height-shadowBottom-gap*parseBinary(shadowBottom), paint);
		}
	}
	
	private void drawWithShadow(Canvas canvas)
	{
		/*Rect rect = new Rect(getBackground().getBounds());
		rect
		canvas.drawRect(rect, paint);*/
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
