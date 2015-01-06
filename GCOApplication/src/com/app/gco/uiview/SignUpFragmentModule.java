package com.app.gco.uiview;

import com.app.gco.HomeBaseActivity;
import com.app.gco.InstructionBoardActivity;
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

public class SignUpFragmentModule extends Fragment implements OnClickListener{

	private Button signUpButton,signInButton,signInsignUpButton;
	private EditText emailTxt,userNameTxt,passwordTxt,confirmPassword;
	private OnClickListener parentClickListener,forgotPasswordListener;
	private TextView forgotPasswordTxt;
	
	public static SignUpFragmentModule newInstance(OnClickListener clickListener,OnClickListener forgotPassword){
		
		SignUpFragmentModule mSignUpFragmentModule = new SignUpFragmentModule();
		mSignUpFragmentModule.parentClickListener = clickListener;
		mSignUpFragmentModule.forgotPasswordListener = forgotPassword;
		return mSignUpFragmentModule;
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
	    confirmPassword = (EditText) rootView.findViewById(R.id.confirmPassword);
	    signInsignUpButton.setText("Sign Up");
		
	    signInButton.setTag(1);
		signInButton.setOnClickListener(parentClickListener);
		signUpButton.setTag(2);
		signUpButton.setOnClickListener(parentClickListener);
		signInsignUpButton.setOnClickListener(this);
		forgotPasswordTxt.setOnClickListener(forgotPasswordListener);
	}

	@Override
	public void onClick(View v) {
	
		if(v == signInsignUpButton){
			
			Intent instructionBoard = new Intent(getActivity(),InstructionBoardActivity.class);
			startActivity(instructionBoard);
			//web service
		}
	}
}
