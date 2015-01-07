package com.app.gco;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.Toast;

import com.app.gco.adapters.HomeTabAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class HomeBaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		  FragmentPagerAdapter adapter = new  HomeTabAdapter(getSupportFragmentManager());

	        ViewPager pager = (ViewPager)findViewById(R.id.pager);
	        pager.setAdapter(adapter);

	        Toast.makeText(HomeBaseActivity.this, "Default Tab", Toast.LENGTH_SHORT).show();
	        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
	        indicator.setViewPager(pager);
	}
}
