package com.app.gco.uiview;

import com.app.gco.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordFragmentModule extends Fragment{

	private Button forgotBtn;
	private EditText emailxt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.forgot_module, container,false);
		initializeGUI(rootView);
		return rootView;
	}
	
	private void initializeGUI(View rootView){
		
		emailxt = (EditText) rootView.findViewById(R.id.forgotEmail);
		forgotBtn = (Button) rootView.findViewById(R.id.forgotPassword);
	}
}
