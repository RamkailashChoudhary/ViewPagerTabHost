package com.app.gco;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.app.gco.instructions.CirclePageIndicator;
import com.app.gco.instructions.InstructionBoardAdapter;

public class InstructionBoardActivity extends FragmentActivity {

	private ViewPager viewPager;
	private InstructionBoardAdapter mInstructionBoardAdapter; 
	private CirclePageIndicator mCirclePageIndicator;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
     requestWindowFeature(Window.FEATURE_NO_TITLE);
	 super.onCreate(arg0);
		
		setContentView(R.layout.instruction_board_activity);
		initializeGUI();
	}
	
	private void initializeGUI(){
		
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        
        mInstructionBoardAdapter = new InstructionBoardAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mInstructionBoardAdapter);
        
        mCirclePageIndicator = (CirclePageIndicator)findViewById(R.id.circleIndicator);
        mCirclePageIndicator.setViewPager(viewPager);
        mCirclePageIndicator.setCurrentItem(mInstructionBoardAdapter.getCount() - 1);
	}
}
