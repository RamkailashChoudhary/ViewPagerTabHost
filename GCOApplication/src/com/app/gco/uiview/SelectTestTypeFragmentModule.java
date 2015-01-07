package com.app.gco.uiview;

import com.app.gco.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SelectTestTypeFragmentModule extends Fragment{

	private ImageView generalHealthTestBtn,physicalHealthTestBtn,nutritionHealthTestBtn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.select_testtype_module, container,false);
		initializeGUI(rootView);
		return rootView;
	}
	
	private void initializeGUI(View rootView){
		
		generalHealthTestBtn = (ImageView) rootView.findViewById(R.id.generalHealthBtn);
		physicalHealthTestBtn = (ImageView) rootView.findViewById(R.id.physicalHealthBtn);
		nutritionHealthTestBtn = (ImageView) rootView.findViewById(R.id.nutritionHealthBtn);
	}
}
