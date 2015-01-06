package com.app.gco.uiview;

import com.app.gco.HomeBaseActivity;
import com.app.gco.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInFragmentModule extends Fragment implements OnClickListener{

	private Button signUpButton,signInButton,signInsignUpButton;
	private EditText emailTxt,userNameTxt,passwordTxt;
	private OnClickListener parentLayoutListener,forgotPasswordListener;
	private TextView forgotPasswordTxt;
	
	public static SignInFragmentModule newInstance(OnClickListener clickListener,OnClickListener forgotpasswordListener){
		
		SignInFragmentModule mSignInFragmentModule = new SignInFragmentModule();
		mSignInFragmentModule.parentLayoutListener = clickListener;
		mSignInFragmentModule.forgotPasswordListener = forgotpasswordListener;
		return mSignInFragmentModule;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		View rootView = inflater.inflate(R.layout.signin_layout, container,false);
		initializeGUI(rootView);
		return rootView;
	}
	private void initializeGUI(View rootView){
		
		signUpButton = (Button) rootView.findViewById(R.id.signUpButton);
		signInButton = (Button) rootView.findViewById(R.id.signinButton);
	    emailTxt  = (EditText) rootView.findViewById(R.id.emailAddress);
	    userNameTxt  = (EditText) rootView.findViewById(R.id.userName);
	    passwordTxt  = (EditText) rootView.findViewById(R.id.password);
	    signInsignUpButton = (Button) rootView.findViewById(R.id.signInOrsignUp);
	    forgotPasswordTxt = (TextView) rootView.findViewById(R.id.forgotPasswordTxt);
	    signInsignUpButton.setText("Sign In");
	    userNameTxt.setVisibility(View.GONE);
		
	    signInButton.setTag(1);
		signInButton.setOnClickListener(parentLayoutListener);
		signUpButton.setTag(2);
		signUpButton.setOnClickListener(parentLayoutListener);
		signInsignUpButton.setOnClickListener(this);
		forgotPasswordTxt.setOnClickListener(forgotPasswordListener);
	}
	@Override
	public void onClick(View v) {

		if(v == signInsignUpButton){
			
		  //web service signin	
			Intent menuBoard = new Intent(getActivity(),HomeBaseActivity.class);
			startActivity(menuBoard);
		}
	}
}
