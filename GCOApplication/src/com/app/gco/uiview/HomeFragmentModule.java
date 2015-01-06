package com.app.gco.uiview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gco.R;

public class HomeFragmentModule extends BaseContainerFragment{

	private boolean mIsViewInited;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.container_fragment, container, false);
		return rootView;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!mIsViewInited) {
        
        	mIsViewInited = true;
            initView();
        }
    }
	
	private void initView() {

        replaceFragment(new HomeInnerModule(), false);
    }
}
