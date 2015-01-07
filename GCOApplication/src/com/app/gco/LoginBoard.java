package com.app.gco;

import twitter4j.TwitterException;
import twitter4j.User;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.gco.delegates.ServerAPI;

public class LoginBoard extends LoginBoardBaseActivity {

	@Override
	protected int getResourceLayout() {

		return R.layout.login_board;
	}

	@Override
	public void onClick(View v) {

		if (v == signInButton) {

			Intent signInsignUp = new Intent(LoginBoard.this, LoginSignUpActivity.class);
			signInsignUp.putExtra(ServerAPI.BOARD_CLICK_MODULE, ServerAPI.SIGNIN_MODULE);
			startActivity(signInsignUp);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

		} else if (v == signUpButton) {

			Intent signInsignUp = new Intent(LoginBoard.this, LoginSignUpActivity.class);
			signInsignUp.putExtra(ServerAPI.BOARD_CLICK_MODULE, ServerAPI.SIGNUP_MODULE);
			startActivity(signInsignUp);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		} else if (v == googlePlusSignIn) {

			signInWithGplus();
		} else if (v == twitterLoginBtn) {

			new TokenGet().execute();
		}
	}

	private class TokenGet extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			try {
				requestToken = twitter.getOAuthRequestToken();
				oauth_url = requestToken.getAuthorizationURL();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return oauth_url;
		}

		@Override
		protected void onPostExecute(String oauth_url) {
			if (oauth_url != null) {
				Log.e("URL", oauth_url);
				auth_dialog = new Dialog(LoginBoard.this);
				auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

				auth_dialog.setContentView(R.layout.auth_dialog);
				web = (WebView) auth_dialog.findViewById(R.id.webv);
				web.getSettings().setJavaScriptEnabled(true);
				web.loadUrl(oauth_url);
				web.setWebViewClient(new WebViewClient() {
					boolean authComplete = false;

					@Override
					public void onPageStarted(WebView view, String url, Bitmap favicon) {
						super.onPageStarted(view, url, favicon);
					}

					@Override
					public void onPageFinished(WebView view, String url) {
						super.onPageFinished(view, url);
						if (url.contains("oauth_verifier") && authComplete == false) {
							authComplete = true;
							Log.e("Url", url);
							Uri uri = Uri.parse(url);
							oauth_verifier = uri.getQueryParameter("oauth_verifier");

							auth_dialog.dismiss();
							new AccessTokenGet().execute();
						} else if (url.contains("denied")) {
							auth_dialog.dismiss();

							Toast.makeText(LoginBoard.this, "Sorry !, Permission Denied", Toast.LENGTH_SHORT).show();

						}
					}
				});
				auth_dialog.show();
				auth_dialog.setCancelable(true);

			} else {

				Toast.makeText(LoginBoard.this, "Sorry !, Network Error or Invalid Credentials", Toast.LENGTH_SHORT).show();

			}
		}
	}

	private class AccessTokenGet extends AsyncTask<String, String, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = new ProgressDialog(LoginBoard.this);
			progress.setMessage("Fetching Data ...");
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIndeterminate(true);
			progress.show();

		}

		@Override
		protected Boolean doInBackground(String... args) {

			try {

				accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
				// SharedPreferences.Editor edit = pref.edit();
				// edit.putString("ACCESS_TOKEN", accessToken.getToken());
				// edit.putString("ACCESS_TOKEN_SECRET",
				// accessToken.getTokenSecret());
				User user = twitter.showUser(accessToken.getUserId());
				profile_url = user.getOriginalProfileImageURL();
				System.out.println("Print the User Name :"+user.getName());
				// edit.putString("NAME", user.getName());
				// edit.putString("IMAGE_URL",
				// user.getOriginalProfileImageURL());
				// edit.commit();

			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean response) {
			if (response) {
				progress.hide();

//				Toast.makeText(LoginBoard.this, "Logined User Name :", Toast.LENGTH_SHORT).show();
				/*
				 * Fragment profile = new ProfileFragment(); FragmentTransaction
				 * ft = getActivity().getFragmentManager().beginTransaction();
				 * ft.replace(R.id.content_frame, profile);
				 * ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				 * ft.addToBackStack(null); ft.commit();
				 */

			}
		}

	}
}
