package com.bbi.Button;

import android.R.interpolator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OverlayNotificationView extends FractionLinearLayout {

	public static final int TEXT = 1;
	public static final int IMAGE = 2;
	public static final int HTML = 3;
	public static final int LIST = 4;
	
	public static final int ANIMATION_LEFT_TO_RIGHT = 4;
	public static final int ANIMATION_RIGHT_TO_LEFT = 5;
	public static final int ANIMATION_TOP_TO_BOTTOM = 6;
	public static final int ANIMATION_BOTTOM_TO_TOP = 7;
	
	private Context context;
	
	private int type;
	private int animationType;
	private int openBtnResource;
	private int closeBtnResource;
	private int contentImageResource;
	private int counter = 1000;
	private int titleSize = 26;
	private int titleColor;
	private int titleGravity;
	private int titleStyle;
	private int contentTextSize = 24;
	private int contentTextColor;
	private int contentTextGravity;
	private int contentTextStyle;
	private int fractionalHeight = LayoutParams.WRAP_CONTENT;
	private int sliderImageResource;
	
	
	private String title;
	private String fontFamily;
	private String contentText;
	private String htmlUrl;
	
	private Drawable openBtnDrawable;
	private Drawable closeBtnDrawable;
	private Drawable contentImageDrawable;
	private Drawable sliderImageDrawable;
	
	private boolean manualOpen;
	private boolean automaticClose;
	private boolean stateOpen;
	
	private ImageView slider;
	private ListAdapter listAdapter;
	private FractionLinearLayout container;
	
	@SuppressLint("SetJavaScriptEnabled")
	public void load()
	{
		removeAllViews();
		LayoutParams param;
		param = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		container = new FractionLinearLayout(context);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(param);
		
		// Separator 
		param = new LayoutParams(LayoutParams.MATCH_PARENT,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,context.getResources().getDisplayMetrics()));
		View topLine = new View(context);
		topLine.setLayoutParams(param);
		topLine.setBackgroundColor(titleColor);
		container.addView(topLine);
		
		// Slider 
		param = new LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
				getResources().getDisplayMetrics()),(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
						getResources().getDisplayMetrics()));
		
		setClipChildren(false);
		slider = new ImageView(context);
		slider.setLayoutParams(param);
		if(sliderImageDrawable != null)
			slider.setImageDrawable(sliderImageDrawable);
		else
			slider.setImageResource(sliderImageResource);
		slider.setX(0);
		slider.setY(0);
		slider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				triggerState();
			}
		});
		container.addView(slider);
		
		if(fractionalHeight != LayoutParams.WRAP_CONTENT || fractionalHeight != LayoutParams.MATCH_PARENT)
		{
			int height = getResources().getDisplayMetrics().heightPixels;
			
			getLayoutParams().height = (height/100)*fractionalHeight;
		}
		else
			param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		if(title != null)
		{

			param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			param.leftMargin = param.rightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,context.getResources().getDisplayMetrics());
			TextView titleView = new TextView(context);
			titleView.setText(title);
			titleView.setLayoutParams(param);
			if(titleStyle != 0)
				titleView.setTypeface(Typeface.create(fontFamily, titleStyle));
			if(titleColor != 0)
				titleView.setTextColor(titleColor);
			if(titleGravity != 0)
				titleView.setGravity(titleGravity);
			titleView.setSingleLine();
			titleView.setTextSize(titleSize);
			container.addView(titleView);
			
			
		}
		
		
		
		// Preparing content
		switch(type)
		{
		case TEXT:
			{
				param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				param.leftMargin = param.rightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
						5,context.getResources().getDisplayMetrics());
				param.topMargin = param.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
						5,context.getResources().getDisplayMetrics());
				TextView contentTextView = new TextView(context);
				contentTextView.setPadding(5, 5, 5, 5);
				contentTextView.setLayoutParams(param);
				contentTextView.setText(contentText);
				if(contentTextStyle != 0)
					contentTextView.setTypeface(Typeface.create(fontFamily, contentTextStyle));
				if(contentTextColor != 0)
					contentTextView.setTextColor(contentTextColor);
				if(contentTextGravity != 0)
					contentTextView.setGravity(contentTextGravity);
				if(contentTextSize != 0)
					contentTextView.setTextSize(contentTextSize);
				container.addView(contentTextView);
			}
			break;
		case IMAGE:
			{
				
				param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				ImageView imageView = new ImageView(context);
				imageView.setLayoutParams(param);
				if(contentImageDrawable != null)
					imageView.setImageDrawable(contentImageDrawable);
				else
					imageView.setImageResource(contentImageResource);
				container.addView(imageView);
			}
			break;
		case HTML:
			{
				param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				WebView webView = new WebView(context);
				webView.setLayoutParams(param);
				webView.getSettings().setJavaScriptEnabled(true);
				webView.setWebViewClient(new WebViewClient());
				webView.loadUrl(htmlUrl);
				container.addView(webView);
			}
			break;
		case LIST:
			{
				param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				ListView list = new ListView(context);
				list.setLayoutParams(param);
				list.setCacheColorHint(Color.TRANSPARENT);
				list.setAdapter(listAdapter);
				container.addView(list);
			}
			break;
		default:
		}
		
		addView(container);
		setCustomAnimation(container);
	}
	
	private LayoutTransition mTransition;
	
	private void setCustomAnimation(View target)
	{
		mTransition = new LayoutTransition();
		setLayoutTransition(mTransition);
		
		ObjectAnimator slideIn = null;
		ObjectAnimator slideOut = null;
		switch(animationType)
		{
		case ANIMATION_RIGHT_TO_LEFT:
			slideIn = ObjectAnimator.ofFloat(target, 
					"fractionTranslationX", 1f,0f).setDuration(counter);
			slideIn.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					super.onAnimationEnd(animation);
				}
			});
			slideOut = ObjectAnimator.ofFloat(target, 
					"fractionTranslationX", 0f,1f).setDuration(counter);
			slideOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					
					super.onAnimationEnd(animation);
				}
			});
			break;
		case ANIMATION_LEFT_TO_RIGHT:
			slideIn = ObjectAnimator.ofFloat(target, 
					"fractionTranslationX", -1f,0f).setDuration(counter);
			slideIn.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					super.onAnimationEnd(animation);
				}
			});
			slideOut = ObjectAnimator.ofFloat(target, 
					"fractionTranslationX", 0f,-1f).setDuration(counter);
			slideOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					
					super.onAnimationEnd(animation);
				}
			});
			break;
		case ANIMATION_TOP_TO_BOTTOM:
			slideIn = ObjectAnimator.ofFloat(target, 
					"fractionTranslationY", -1f,0f).setDuration(counter);
			slideIn.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					super.onAnimationEnd(animation);
				}
			});
			slideOut = ObjectAnimator.ofFloat(target, 
					"fractionTranslationY", 0f,-1f).setDuration(counter);
			slideOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					
					super.onAnimationEnd(animation);
				}
			});
			break;
		case ANIMATION_BOTTOM_TO_TOP:
			slideIn = ObjectAnimator.ofFloat(target, 
					"fractionTranslationY", 1f,0f).setDuration(counter);
			slideIn.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					super.onAnimationEnd(animation);
				}
			});
			slideOut = ObjectAnimator.ofFloat(target, 
					"fractionTranslationY", 0f,1f).setDuration(counter);
			slideOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					View target = (View) ((ObjectAnimator)animation).getTarget();
					
					super.onAnimationEnd(animation);
				}
			});
			break;
		}
		
		mTransition.setAnimator(LayoutTransition.DISAPPEARING, slideOut);
		mTransition.setAnimator(LayoutTransition.APPEARING, slideIn);
	
	}
	
	public void triggerState()
	{
		if(stateOpen)
			close();
		else
			open();
	}
	
	public void close()
	{
		container.setVisibility(View.GONE);
		stateOpen = false;
	}
	
	public void open()
	{
		container.setVisibility(View.VISIBLE);
		stateOpen = true;
	}
	
	
	public OverlayNotificationView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	public OverlayNotificationView(Context context, AttributeSet attrs) {
		super(context, attrs);
	
		init(context);
	}

	public OverlayNotificationView(Context context) {
		super(context);
		
		init(context);
	}

	private void init(Context context)
	{
		this.context = context;
		setOrientation(VERTICAL);
		
	}
	
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		load();
		System.out.println("loaded");
	}
	
	
	
	
	private class webVewClient extends WebViewClient
	{
		@Override
		public void onPageFinished(WebView view, String url) {
			
			super.onPageFinished(view, url);
		}
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			return super.shouldOverrideUrlLoading(view, url);
		}
	}


	public int getType() {
		return type;
	}


	public int getAnimationType() {
		return animationType;
	}


	public int getOpenBtnResource() {
		return openBtnResource;
	}


	public int getCloseBtnResource() {
		return closeBtnResource;
	}


	public int getCounter() {
		return counter;
	}


	public int getTitleSize() {
		return titleSize;
	}


	public int getTitleColor() {
		return titleColor;
	}


	public int getTitleGravity() {
		return titleGravity;
	}


	public int getTitleStyle() {
		return titleStyle;
	}


	public int getContentTextSize() {
		return contentTextSize;
	}


	public int getContentTextColor() {
		return contentTextColor;
	}


	public int getContentTextGravity() {
		return contentTextGravity;
	}


	public int getContentTextStyle() {
		return contentTextStyle;
	}


	public String getTitle() {
		return title;
	}


	public String getFontFamily() {
		return fontFamily;
	}


	public String getContentText() {
		return contentText;
	}


	public Drawable getOpenBtnDrawable() {
		return openBtnDrawable;
	}


	public Drawable getCloseBtnDrawable() {
		return closeBtnDrawable;
	}


	public boolean isManualOpen() {
		return manualOpen;
	}


	public boolean isAutomaticClose() {
		return automaticClose;
	}


	public ListAdapter getListAdapter() {
		return listAdapter;
	}


	public void setContext(Context context) {
		this.context = context;
	}


	public void setType(int type) {
		this.type = type;
	}


	public void setAnimationType(int animationType) {
		this.animationType = animationType;
	}


	public void setOpenBtnResource(int openBtnResource) {
		this.openBtnResource = openBtnResource;
	}


	public void setCloseBtnResource(int closeBtnResource) {
		this.closeBtnResource = closeBtnResource;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public void setTitleSize(int titleSize) {
		this.titleSize = titleSize;
	}


	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}


	public void setTitleGravity(int titleGravity) {
		this.titleGravity = titleGravity;
	}


	public void setTitleStyle(int titleStyle) {
		this.titleStyle = titleStyle;
	}


	public void setContentTextSize(int contentTextSize) {
		this.contentTextSize = contentTextSize;
	}


	public void setContentTextColor(int contentTextColor) {
		this.contentTextColor = contentTextColor;
	}


	public void setContentTextGravity(int contentTextGravity) {
		this.contentTextGravity = contentTextGravity;
	}


	public void setContentTextStyle(int contentTextStyle) {
		this.contentTextStyle = contentTextStyle;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}


	public void setContentText(String contentText) {
		this.contentText = contentText;
	}


	public void setOpenBtnDrawable(Drawable openBtnDrawable) {
		this.openBtnDrawable = openBtnDrawable;
	}


	public void setCloseBtnDrawable(Drawable closeBtnDrawable) {
		this.closeBtnDrawable = closeBtnDrawable;
	}


	public void setManualOpen(boolean manualOpen) {
		this.manualOpen = manualOpen;
		stateOpen = !manualOpen;
	}


	public void setAutomaticClose(boolean automaticClose) {
		this.automaticClose = automaticClose;
	}


	public void setListAdapter(ListAdapter listAdapter) {
		this.listAdapter = listAdapter;
	}


	public int getContentImageResource() {
		return contentImageResource;
	}


	public Drawable getContrntImageDrawable() {
		return contentImageDrawable;
	}


	public void setContentImageResource(int contentImageResource) {
		this.contentImageResource = contentImageResource;
	}


	public void setContrntImageDrawable(Drawable contrntImageDrawable) {
		this.contentImageDrawable = contrntImageDrawable;
	}


	public Drawable getContentImageDrawable() {
		return contentImageDrawable;
	}

	public void setContentImageDrawable(Drawable contentImageDrawable) {
		this.contentImageDrawable = contentImageDrawable;
	}

	

	public int getFractionalHeight() {
		return fractionalHeight;
	}


	public void setFractionalHeight(int fractionalHeight) {
		this.fractionalHeight = fractionalHeight;
	}


	public String getHtmlUrl() {
		return htmlUrl;
	}


	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}


	public int getSliderImageResource() {
		return sliderImageResource;
	}


	public Drawable getSliderImageDrawable() {
		return sliderImageDrawable;
	}


	public void setSliderImageResource(int sliderImageResource) {
		this.sliderImageResource = sliderImageResource;
	}


	public void setSliderImageDrawable(Drawable sliderImageDrawable) {
		this.sliderImageDrawable = sliderImageDrawable;
	}
	
	
	
}
