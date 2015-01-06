package com.app.gco.instructions;

import com.app.gco.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InstructionView  extends Fragment{

	private TextView viewTxt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View viewPager = inflater.inflate(R.layout.instruction_view, container,false);
		return viewPager;
	}
	
	private void initializeGUI(View rootView){
		
	    	
	}
}
