package com.bbi.Button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class ResourceManager {

	/**
	 * <h1>public static Drawable createRectangleShape(String[] colors,int[] stroke ,int[] corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - colors for gradient.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for corners. Array length 1 for using same corder value, length 4 for each corner value.
	 * @return
	 */
	public static Drawable createRectangleShape(String[] colors,int[] stroke ,int[] corner)
	{
		GradientDrawable drawable;
		
		// color
		if(colors.length > 1)
		{
			drawable = new GradientDrawable(Orientation.TOP_BOTTOM, parseColors(colors));
		}
		else
		{
			drawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.parseColor(colors[0]),
					Color.parseColor(colors[0])});
		}
		
		// shape
		drawable.setShape(GradientDrawable.RECTANGLE);
		
		// stroke
		if(stroke != null)
			drawable.setStroke(stroke[0],stroke[1]);
		
		// corner
		if(corner != null)
		{
			if(corner.length == 1)
				drawable.setCornerRadius(corner[0]);
			else
			{
				drawable.setCornerRadii(new float[] {corner[0],corner[0],corner[1],corner[1],
						corner[2],corner[2],corner[3],corner[3]});
			}
		}
		return drawable;
	}
	
	/**
	 * <h1>public static Drawable createRectangleShape(int[] colors,int[] stroke ,int[] corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - colors for gradient.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for corners. Array length 1 for using same corder value, length 4 for each corner value.
	 * @return
	 */
	public static Drawable createRectangleShape(int[] colors,int[] stroke ,int[] corner)
	{
		GradientDrawable drawable;
		
			if(colors != null && colors.length > 1)
			{
				drawable = new GradientDrawable(Orientation.TOP_BOTTOM, colors);
			}
			else if(colors != null && colors.length == 1)
			{
				drawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{colors[0],colors[0]});
			}
			else
			{
				drawable = new GradientDrawable();
			}
		
		// shape
		drawable.setShape(GradientDrawable.RECTANGLE);
		
		// stroke
		if(stroke != null)
			drawable.setStroke(stroke[0],stroke[1]);
		
		// corner
		if(corner != null)
		{
			if(corner.length == 1)
				drawable.setCornerRadius(corner[0]);
			else
			{
				drawable.setCornerRadii(new float[] {corner[0],corner[0],corner[1],corner[1],
						corner[2],corner[2],corner[3],corner[3]});
			}
		}
		
		return drawable;
	}
	
	/**
	 * <h1>public static Drawable createRectangleShape(String colors,int[] stroke ,int[] corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - solid color.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for corners. Array length 1 for using same corder value, length 4 for each corner value.
	 * @return
	 */
	public static Drawable createRectangleShape(String colors,int[] stroke ,int[] corner)
	{
		return createRectangleShape(new String[] {colors}, stroke, corner);
	}
	
	/**
	 * <h1>public static Drawable createRectangleShape(String colors,int[] stroke ,int corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - solid color.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for each corner.
	 * @return
	 */
	public static Drawable createRectangleShape(String colors,int[] stroke ,int corner)
	{
		return createRectangleShape(new String[] {colors}, stroke, new int[]{corner});
	}
	
	/**
	 * <h1>public static Drawable createRectangleShape(in[] colors,int[] stroke ,int corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - colors for gradient.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for corners.
	 * @return
	 */
	public static Drawable createRectangleShape(int[] colors,int[] stroke ,int corner)
	{
		return createRectangleShape(colors, stroke, new int[]{corner});
	}
	
	/**
	 * <h1>public static Drawable createRectangleShape(String[] colors,int[] stroke ,int corner)</h1>
	 * <p>Creates rectangle drawable.</p>
	 * @param colors - colors for gradient.
	 * @param stroke - values for border, 0 index value for width and 1 index for color. 
	 * @param corner - radius for corners.
	 * @return
	 */
	public static Drawable createRectangleShape(String[] colors,int[] stroke ,int corner)
	{
		return createRectangleShape(colors, stroke, new int[]{corner});
	}
	
	/**
	 * <h1>public static int[] parseColors(String[] colors)</h1>
	 * <p>Parse the color string, and return the corresponding color-int.</p>
	 */
	public static int[] parseColors(String[] colors)
	{
		if(colors == null)
			return null;
		if(colors.length == 1)
			return new int[]{Color.parseColor(colors[0])};
		else
		{
			int[] arr = new int[colors.length];
			for(int i =0;i<colors.length;i++)
				arr[i] = Color.parseColor(colors[i]);
			return arr;
		}
	}
	
	/**
	 * <h1>public static Drawable createSelectors(Drawable byDefault,Drawable onPress)</h1>
	 * <p>Creates Selectors.</p>
	 * @param byDefault - Default drawable
	 * @param onPress - On pressed state drawable
	 * @return
	 */
	public static StateListDrawable createSelectors(Drawable byDefault,Drawable onPress)
	{
		StateListDrawable state = new StateListDrawable();
		state.addState(new int[]{android.R.attr.state_pressed}, onPress);
		state.addState(new int[]{}, byDefault);
		return state;
	}
	
	/**
	 * <h1>public static Drawable createSelectors(int[] colors)</h1>
	 * <p>Creates Selector.</p>
	 * @param colors - array of colors. if color.length should be Two or Four. Four in case of gradient otherwise two.
	 * @return Drawable
	 */
	public static StateListDrawable createSelectors(int[] colors)
	{
		Drawable byDefault = null ,onPress = null;
		if(colors.length == 4)
		{
			byDefault = createGradientDrawable(new int[]{colors[0],colors[1]});
			onPress = createGradientDrawable(new int[]{colors[2],colors[3]});
		}
		else if(colors.length == 2)
		{
			byDefault = createGradientDrawable(new int[]{colors[0],colors[0]});
			onPress = createGradientDrawable(new int[]{colors[1],colors[1]});
		}
		else if(colors.length == 1)
		{
			byDefault = createGradientDrawable(new int[]{colors[0],colors[0]});
			onPress = null;
		}
		
		StateListDrawable state = new StateListDrawable();
		state.addState(new int[]{android.R.attr.state_pressed}, onPress);
		state.addState(new int[]{}, byDefault);
		return state;
	}
	
	/**
	 * Creates selector for TextView 
	 * @param colors - integer array containing at most two color.
	 * @return StateListDrawable
	 */
	public static ColorStateList createSelectorsForTextView(int[] colors)
	{
		int[][] states = new int[][]{
				new int[]{android.R.attr.state_pressed},
				new int[]{}
		};
		
		ColorStateList state = null;
		if(colors.length > 1)
			state = new ColorStateList(states,new int[]{ colors[1],colors[0]});
		else
			state = new ColorStateList(states,new int[]{ colors[0],colors[0]});
		return state;
	}
	
	/**
	 * <h1>public static Drawable createSelectors(Drawable byDefault,Drawable onPress)</h1>
	 * <p>Creates Selectors.</p>
	 * @param context
	 * @param byDefault - default resource
	 * @param onPress - on press resource.
	 * @return Drawable
	 */
	public static Drawable createSelectors(Context context,int byDefault,int onPress)
	{
		Drawable Default = context.getResources().getDrawable(byDefault);
		Drawable Press = context.getResources().getDrawable(onPress);
		StateListDrawable state = new StateListDrawable();
		state.addState(new int[]{android.R.attr.state_pressed}, Press);
		state.addState(new int[]{}, Default);
		return state;
	}
	
	/**
	 * <h1>public static Drawable createGradientDrawable(int[] colors)</h1>
	 * <p>Returns gradient drawable.</p>
	 * @param colors - array of colors.
	 */
	public static Drawable createGradientDrawable(int[] colors)
	{
		GradientDrawable d = null;
		
		if(colors.length == 1)
		{
			d = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{colors[0],colors[0]});
		}
		else
			d = new GradientDrawable(Orientation.TOP_BOTTOM, colors);
		d.setShape(GradientDrawable.RECTANGLE);
		return d;
	}
	
	@SuppressLint("NewApi")
	public static void setDrawable(View v,Drawable d)
	{
		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			v.setBackgroundDrawable(d);
		else
			v.setBackground(d);
	}
	
	@SuppressLint("NewApi")
	public static void setDrawable(View v,int []colors)
	{
		if(colors.length == 1)
		{
			v.setBackgroundColor(colors[0]);
		}
		else
		{
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
				v.setBackgroundDrawable(createGradientDrawable(colors));
			else
				v.setBackground(createGradientDrawable(colors));
		}
	}
	
}
