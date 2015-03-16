package com.bbi.Button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class FractionRelativeLayout extends RelativeLayout {

	public FractionRelativeLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public FractionRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FractionRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public float getFractionTranslationX() {
		
		return getWidth() > 0 ? super.getTranslationX() / getWidth() : Float.MAX_VALUE;
	}
	
	public void setFractionTranslationX(float translationX) {
		
		int width = getWidth();
		super.setTranslationX(width > 0 ? width * translationX : Float.MAX_VALUE);
	}
	
	public float getFractionTranslationY() {
		
		return getHeight() > 0 ? super.getTranslationX() / getHeight() : Float.MAX_VALUE;
	}
	
	public void setFractionTranslationY(float translationY) {
		int height = getHeight();
		super.setTranslationY(height > 0 ? height * translationY : Float.MAX_VALUE);
	}

}
