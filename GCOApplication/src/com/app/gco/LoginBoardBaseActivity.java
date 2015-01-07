package com.app.gco;

import java.util.Arrays;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.gco.dao.LoginData;
import com.app.gco.delegates.ResponseDelegate;
import com.app.gco.delegates.ServerAPI;
import com.app.gco.webrequest.RequestDataModule;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.loopj.android.http.RequestParams;

public abstract class LoginBoardBaseActivity extends FragmentActivity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener, ResponseDelegate {

	protected Button signUpButton, signInButton;
	protected ImageView twitterLoginBtn;

	//GOOGLE PLUS CONFIGURATION
	protected SignInButton googlePlusSignIn;
	private GoogleApiClient mGoogleApiClient;
	private ConnectionResult mConnectionResult;
	
	//FACEBOOK CONFIGURATION
	private LoginButton fbloginBtn;
	private UiLifecycleHelper uiHelper;
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

	private boolean mSignInClicked;
	private static final int RC_SIGN_IN = 0;
	private boolean mIntentInProgress;
	
	
	//TWITTER CONFIGURATION
	protected Twitter twitter;
    protected RequestToken requestToken = null;
    protected AccessToken accessToken;
    protected Dialog auth_dialog;
    protected WebView web;
    protected String oauth_url,oauth_verifier,profile_url;
    protected ProgressDialog progress;

    protected RequestDataModule mRequestDataModule;
    
	@Override
	protected void onCreate(Bundle arg0) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(arg0);

		setContentView(getResourceLayout());
		initializeGUI(arg0);
	}

	protected abstract int getResourceLayout();

	private void initializeGUI(Bundle arg0) {

		signInButton = (Button) findViewById(R.id.boradSignInButton);
		signUpButton = (Button) findViewById(R.id.boardSignUpButton);
		googlePlusSignIn = (SignInButton) findViewById(R.id.google_plus_button);
		twitterLoginBtn = (ImageView) findViewById(R.id.twitterLoginBtn);

		signInButton.setOnClickListener(this);
		signUpButton.setOnClickListener(this);
		googlePlusSignIn.setOnClickListener(this);

		mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).build();
		
		uiHelper = new UiLifecycleHelper(LoginBoardBaseActivity.this, statusCallback);
		uiHelper.onCreate(arg0);
		
		fbloginBtn = (LoginButton) findViewById(R.id.fbloginBtn);

		fbloginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					
					isUserFacebookRegistered(user.getId());
					Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
				} else {
					
					Toast.makeText(getApplicationContext(), "You are not logged", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		twitter = new TwitterFactory().getInstance();
	 	twitter.setOAuthConsumer("5dlRDrNUl1JR6SVw23Uk3nPZc", "7puzDhGNNW5GIJbbTuH6TZxSg4PxCBYaUaMt8tn5EZ7D1b03a2");
	 	
	 	twitterLoginBtn.setOnClickListener(this);
	}

	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!result.hasResolution()) {
			
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
			return;
		}

		Toast.makeText(LoginBoardBaseActivity.this, result.toString(), Toast.LENGTH_LONG).show();

		if (!mIntentInProgress) {

			mConnectionResult = result;

			if (mSignInClicked) {

				resolveSignInError();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {

				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {

				mGoogleApiClient.connect();
			}
		}else{
			
			super.onActivityResult(requestCode, responseCode, intent);
			uiHelper.onActivityResult(requestCode, responseCode, intent);
		}
	}

	@Override
	public void onConnected(Bundle arg0) {

		mSignInClicked = false;
		Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

		// Get user's information
		getProfileInformation();
		// Update the UI after signin
		updateUI(true);
	}

	/**
	 * Updating the UI, showing/hiding buttons and profile layout
	 * */
	private void updateUI(boolean isSignedIn) {

		Toast.makeText(LoginBoardBaseActivity.this, "Update UI View :" + isSignedIn, Toast.LENGTH_SHORT).show();
		if (isSignedIn) {

			// btnSignIn.setVisibility(View.GONE);
			// btnSignOut.setVisibility(View.VISIBLE);
		} else {

			// btnSignIn.setVisibility(View.VISIBLE);
			// btnSignOut.setVisibility(View.GONE);
		}
	}

	/**
	 * Fetching user's information name, email, profile pic
	 * */
	private void getProfileInformation() {
		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
				String personName = currentPerson.getDisplayName();
				String personPhotoUrl = currentPerson.getImage().getUrl();
				String personGooglePlusProfile = currentPerson.getUrl();
				String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

				// Log.e(TAG, "Name: " + personName + ", plusProfile: " +
				// personGooglePlusProfile + ", email: " + email + ", Image: " +
				// personPhotoUrl);

				// txtName.setText(personName);
				// txtEmail.setText(email);

				// by default the profile url gives 50x50 px image only
				// we can replace the value with whatever dimension we want by
				// replacing sz=X
				// personPhotoUrl = personPhotoUrl.substring(0,
				// personPhotoUrl.length() - 2) + PROFILE_PIC_SIZE;

				// new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);

			} else {
				Toast.makeText(getApplicationContext(), "Person information is null", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
		updateUI(false);
	}

	/**
	 * Sign-in into google
	 * */
	protected void signInWithGplus() {
		if (!mGoogleApiClient.isConnecting()) {

			mSignInClicked = true;
			resolveSignInError();
		}
	}

	/**
	 * Sign-out from google
	 * */
	private void signOutFromGplus() {

		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();
			mGoogleApiClient.connect();
			updateUI(false);
		}
	}

	/**
	 * Revoking access from google
	 * */
	private void revokeGplusAccess() {
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
				@Override
				public void onResult(Status arg0) {
					// Log.e(TAG, "User access revoked!");
					mGoogleApiClient.connect();
					updateUI(false);
				}

			});
		}
	}
	
	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			if (state.isOpened()) {
				
				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {
				
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};
	
	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}

	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, PERMISSIONS));
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);

		uiHelper.onSaveInstanceState(savedState);
	}
	
	private void isUserFacebookRegistered(String fbId){
		
		mRequestDataModule = new RequestDataModule(LoginBoardBaseActivity.this);
		mRequestDataModule.setMethodType(ServerAPI.POST_METHOD);
		RequestParams params = new RequestParams();
		params.put("facebookid", fbId);
		mRequestDataModule.setParams(params);
		mRequestDataModule.setUrl(ServerAPI.IS_FACEBOOK_USER);
		mRequestDataModule.setRequestTag(ServerAPI.IS_FACEBOOK_USER_TAG);
		mRequestDataModule.setClassType(LoginData.class);
		mRequestDataModule.execute();
	}
	
	private void isUserTwitterRegistered(String twitterId){
		
		mRequestDataModule = new RequestDataModule(LoginBoardBaseActivity.this);
		mRequestDataModule.setMethodType(ServerAPI.POST_METHOD);
		RequestParams params = new RequestParams();
		params.put("twitterid", twitterId);
		mRequestDataModule.setParams(params);
		mRequestDataModule.setUrl(ServerAPI.IS_TWITTER_USER);
		mRequestDataModule.setRequestTag(ServerAPI.IS_TWITTER_USER_TAG);
		mRequestDataModule.setClassType(LoginData.class);
		mRequestDataModule.execute();
	}
	
	private void isUserGooglePlusRegistered(String googleId){
		
		mRequestDataModule = new RequestDataModule(LoginBoardBaseActivity.this);
		mRequestDataModule.setMethodType(ServerAPI.POST_METHOD);
		RequestParams params = new RequestParams();
		params.put("googleplusid", googleId);
		mRequestDataModule.setParams(params);
		mRequestDataModule.setUrl(ServerAPI.IS_GOOGLE_PLUS_USER);
		mRequestDataModule.setRequestTag(ServerAPI.IS_GOOGLE_PLUS_USER_TAG);
		mRequestDataModule.setClassType(LoginData.class);
		mRequestDataModule.execute();
	}
}
