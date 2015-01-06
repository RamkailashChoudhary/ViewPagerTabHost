package com.app.gco;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.TabHost;

import com.app.gco.tabbar.HomeTabAdapter;
import com.app.gco.uiview.HomeFragmentModule;
import com.app.gco.uiview.InfoFragmentModule;

public class HomeBaseActivity extends FragmentActivity {

	private TabHost mTabHost;
	private ViewPager mViewPager;
	private HomeTabAdapter mHomeTabAdapter;
//	After uploaded Github data

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mHomeTabAdapter = new HomeTabAdapter(this, mTabHost, mViewPager);

		mHomeTabAdapter.addTab(mTabHost.newTabSpec("one").setIndicator("One"), HomeFragmentModule.class, savedInstanceState);
		mHomeTabAdapter.addTab(mTabHost.newTabSpec("two").setIndicator("Two"), InfoFragmentModule.class, savedInstanceState);
		mHomeTabAdapter.addTab(mTabHost.newTabSpec("Three").setIndicator("Three"), InfoFragmentModule.class, savedInstanceState);

		if (savedInstanceState != null) {
			
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

	}
}
