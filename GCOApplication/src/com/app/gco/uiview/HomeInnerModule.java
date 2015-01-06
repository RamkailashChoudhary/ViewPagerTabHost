package com.app.gco.uiview;

import com.app.gco.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeInnerModule extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_tab_module, container, false);
		initializeGUI(rootView);
		return rootView;
	}
	
	private void initializeGUI(View rootView){
		
		
	}
}
