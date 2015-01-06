package com.app.gco.instructions;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class InstructionBoardAdapter extends FragmentStatePagerAdapter {

	protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", };
	
	private int mCount = CONTENT.length;
	 
	public InstructionBoardAdapter(FragmentManager fm) {
		super(fm);
     
	}

	@Override
    public Fragment getItem(int position) {

    	return new InstructionView();//.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
    	
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	
      return InstructionBoardAdapter.CONTENT[position % CONTENT.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
 }
