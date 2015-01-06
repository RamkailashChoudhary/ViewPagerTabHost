package com.app.gco.tabbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TabInfo {

	private String tag;
	private Class<?> clss;
	private Bundle args;
	private Fragment fragment;

	public TabInfo(String tag, Class<?> clazz, Bundle args) {
		
		setTag(clazz.getName());
		setClss(clazz);
		setArgs(args);
	}

	public Fragment getFragment() {
		
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		
		this.fragment = fragment;
	}

	public Class<?> getClss() {
		
		return clss;
	}

	public void setClss(Class<?> clss) {
		
		this.clss = clss;
	}

	public String getTag() {
		
		return tag;
	}

	public void setTag(String tag) {
		
		this.tag = tag;
	}

	public Bundle getArgs() {
		
		return args;
	}

	public void setArgs(Bundle args) {
		
		this.args = args;
	}
}
