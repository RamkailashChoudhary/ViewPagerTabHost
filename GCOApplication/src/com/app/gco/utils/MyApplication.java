package com.app.gco.utils;

import com.app.gco.dao.LoginData;

import android.app.Application;

public class MyApplication extends Application{

	private String facebookId,twitterId,googleId;
	private LoginData mLoginData; 
	
	@Override
	public void onCreate() {
		super.onCreate();
	
		setFacebookId("");
		setGoogleId("");
		setTwitterId("");
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public LoginData getmLoginData() {
		return mLoginData;
	}

	public void setmLoginData(LoginData mLoginData) {
		this.mLoginData = mLoginData;
	}
	
	
}
