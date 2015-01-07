package com.app.gco.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeTabAdapter extends FragmentPagerAdapter {
	
	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };
	 
    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
    	
        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	
        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
    	
      return CONTENT.length;
    }
}
