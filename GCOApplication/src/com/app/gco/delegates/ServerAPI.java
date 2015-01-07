package com.app.gco.delegates;

public interface ServerAPI {
	
	public static final String BASE_URL = "http://dev.aninteractive.ae/user/";
	
	public static final String LOGIN = BASE_URL + "LoginUser?";
	
	public static final String REGISTRATION = BASE_URL + "RegisterUser?";
	
	public static final String IS_FACEBOOK_USER = BASE_URL + "IsUserRegisterFromFacebook?";
	
	public static final String IS_TWITTER_USER = BASE_URL + "IsUserRegisterFromTwitter?";
	
	public static final String IS_GOOGLE_PLUS_USER = "IsUserRegisterFromGooglePlus?";
	
	public static final int LOGIN_TAG = 10001;
	
	public static final int REGISTRATION_TAG = 10002;
	
	public static final int IS_FACEBOOK_USER_TAG = 10003;
	
	public static final int IS_TWITTER_USER_TAG = 10004;
	
	public static final int IS_GOOGLE_PLUS_USER_TAG = 10005;

	public static final int SIGNIN_MODULE = 1;
	
	public static final int SIGNUP_MODULE = 2;
	
	public static final int POST_METHOD = 11111;
	
	public static final int GET_METHOD = 22222;
	
	public static final String BOARD_CLICK_MODULE = "BoardClickModule";
}
