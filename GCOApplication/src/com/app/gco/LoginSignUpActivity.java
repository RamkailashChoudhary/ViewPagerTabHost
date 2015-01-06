package com.app.gco;

import com.app.gco.delegates.ServerAPI;
import com.app.gco.uiview.ForgotPasswordFragmentModule;
import com.app.gco.uiview.SignInFragmentModule;
import com.app.gco.uiview.SignUpFragmentModule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;

public class LoginSignUpActivity extends FragmentActivity implements OnClickListener {

	private RelativeLayout backLayout;
	private SignInFragmentModule mSignInFragmentModule;
	private SignUpFragmentModule mSignUpFragmentModule;
	private ForgotPasswordFragmentModule mForgotPasswordFragmentModule;
	private FragmentManager mFragmentManager;
	private Fragment attachedModule;
	private FragmentTransaction mFragmentTransaction;

	@Override
	protected void onCreate(Bundle arg0) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(arg0);
		setContentView(R.layout.signin_signup_view);

		initializeGUI();
	}

	private void initializeGUI() {

		backLayout = (RelativeLayout) findViewById(R.id.backButtonLayout);
		backLayout.setOnClickListener(this);
		mFragmentManager = getSupportFragmentManager();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {

			selectedModule(bundle.getInt(ServerAPI.BOARD_CLICK_MODULE));
		}
	}

	private void selectedModule(int tag) {

		mFragmentTransaction = mFragmentManager.beginTransaction();
		attachedModule = mFragmentManager.findFragmentById(R.id.loginViewController);
		if(attachedModule != null)
			mFragmentTransaction.detach(attachedModule);
		
		if (tag == ServerAPI.SIGNIN_MODULE) {

			if (mSignInFragmentModule != null) {

				mFragmentTransaction.detach(attachedModule);
				mFragmentTransaction.attach(mSignInFragmentModule);
			} else {

				mSignInFragmentModule = SignInFragmentModule.newInstance(loginModuleListener,forgotPasswordView);
				mFragmentTransaction.add(R.id.loginViewController, mSignInFragmentModule, "SignInFragmentModule");
			}

		} else if (tag == ServerAPI.SIGNUP_MODULE) {

			if (mSignUpFragmentModule != null) {

				mFragmentTransaction.detach(attachedModule);
				mFragmentTransaction.attach(mSignUpFragmentModule);
			} else {

				mSignUpFragmentModule = SignUpFragmentModule.newInstance(loginModuleListener,forgotPasswordView);
				mFragmentTransaction.add(R.id.loginViewController, mSignUpFragmentModule, "SignUpFragmentModule");
			}
		}
		mFragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {

		if (v == backLayout) {

			onBackPressed();
		}
	}
	
	@Override
	public void onBackPressed() {
		
		if(getSupportFragmentManager().findFragmentById(R.id.loginViewController) instanceof ForgotPasswordFragmentModule){
			
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.detach(mForgotPasswordFragmentModule);
			mFragmentTransaction.attach(attachedModule);
			mFragmentTransaction.commit();
		}else
		  super.onBackPressed();
		
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

	private OnClickListener loginModuleListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			int tag = (Integer) v.getTag();
			selectedModule(tag);
		}
	};
	
	private OnClickListener forgotPasswordView = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			attachedModule  = mFragmentManager.findFragmentById(R.id.loginViewController);
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.detach(attachedModule);

			if(mForgotPasswordFragmentModule != null){
				
				mFragmentTransaction.attach(mForgotPasswordFragmentModule);
			}else{
				
				mForgotPasswordFragmentModule = new ForgotPasswordFragmentModule();
				mFragmentTransaction.add(R.id.loginViewController, mForgotPasswordFragmentModule,"ForgotPasswordFragmentModule");
			}
			mFragmentTransaction.commit();
		}
	};
}
