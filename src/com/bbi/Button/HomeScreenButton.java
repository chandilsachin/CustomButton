package com.bbi.Button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class HomeScreenButton extends FrameLayout {

	
	public HomeScreenButton(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public HomeScreenButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HomeScreenButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	ShadowView view ;
	private void init()
	{
		ViewGroup.LayoutParams param = getLayoutParams();
		ViewGroup.LayoutParams param1 = new LayoutParams(param.width+40,param.height+40);
		view = new ShadowView(getContext());
		view.setLayoutParams(param1);
		int i=param.width;
		setClipChildren(false);
		addView(view);
	}
	
	

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
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed)
			init();
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
