package com.app.gco;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class SplashActivity extends FragmentActivity {

	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		mHandler = new Handler();
		mHandler.postDelayed(loginBoard, 3000);
	}
	
	private Runnable loginBoard = new Runnable() {
		
		@Override
		public void run() {

			Intent loginBoard = new Intent(SplashActivity.this,LoginBoard.class);
			startActivity(loginBoard);
			finish();
		}
	};
}
