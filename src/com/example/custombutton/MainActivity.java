package com.example.custombutton;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bbi.Button.OverlayNotificationView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button) findViewById(R.id.btn1);
		
		
		final OverlayNotificationView view = (OverlayNotificationView) findViewById(R.id.overlayView);
		//view.setTitle("Title");
		view.setTitleColor(Color.MAGENTA);
		view.setType(OverlayNotificationView.IMAGE);
		view.setContentImageResource(R.drawable.ic_launcher);
		view.setFractionalHeight(50);
		view.setSliderImageResource(R.drawable.tab_frwrd_grey);
		//view.setAnimationType(OverlayNotificationView.ANIMATION_RIGHT_TO_LEFT);
		view.setAnimationType(OverlayNotificationView.ANIMATION_LEFT_TO_RIGHT);
		view.setManualOpen(false);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				view.triggerState();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
