package com.app.gco.uiview;

import com.app.gco.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionFragmentModule extends Fragment{

	private ImageView nextButton;
	private TextView questionTxt,questionA,questionB,questionC,questionD,questionTitleTxt;
	private CheckBox checkBoxA,checkBoxB,checkBoxC,checkBoxD;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.question_module, container,false);
		initializeGUI(rootView);
		return rootView;
	}
	
	private void initializeGUI(View rootView){
		
		nextButton = (ImageView) rootView.findViewById(R.id.questionNextBtn);
		questionTitleTxt = (TextView) rootView.findViewById(R.id.questionTitleTxt);
		questionTxt = (TextView)rootView.findViewById(R.id.questionTxt);
		questionA = (TextView) rootView.findViewById(R.id.questionATxt);
		checkBoxA = (CheckBox) rootView.findViewById(R.id.questionACheck);
		questionB = (TextView) rootView.findViewById(R.id.questionBTxt);
		checkBoxB = (CheckBox) rootView.findViewById(R.id.questionBCheck);
		questionC = (TextView) rootView.findViewById(R.id.questionCTxt);
		checkBoxC = (CheckBox) rootView.findViewById(R.id.questionCCheck);
		questionD = (TextView) rootView.findViewById(R.id.questionDTxt);
		checkBoxD = (CheckBox) rootView.findViewById(R.id.questionDCheck);
		
		checkBoxA.setOnCheckedChangeListener(onCheckedChangedListener);
		checkBoxB.setOnCheckedChangeListener(onCheckedChangedListener);
		checkBoxC.setOnCheckedChangeListener(onCheckedChangedListener);
		checkBoxD.setOnCheckedChangeListener(onCheckedChangedListener);
	}
	
	private OnCheckedChangeListener onCheckedChangedListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			
			
		}
	};
}
