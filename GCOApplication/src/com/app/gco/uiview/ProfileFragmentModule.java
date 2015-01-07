package com.app.gco.uiview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gco.R;

public class ProfileFragmentModule extends Fragment{

	private ImageView editImageBtn,userProfileImg;
	private TextView userName,userEmail,userPassword,userDOB,userLikes,userLovesEat,userRoleMode;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.profile_module, container,false);
		initializeGUI(rootView);
		return rootView;
	}
	
	private void initializeGUI(View rootView){
		
		editImageBtn = (ImageView) rootView.findViewById(R.id.profileEditBtn);
		userProfileImg = (ImageView) rootView.findViewById(R.id.userProfileImg);
		userName = (TextView) rootView.findViewById(R.id.profileUserName);
		userEmail = (TextView) rootView.findViewById(R.id.profileUserEmail);
		userPassword = (TextView) rootView.findViewById(R.id.profileUserPassword);
		userDOB = (TextView) rootView.findViewById(R.id.profileUserDOB);
		userLikes = (TextView) rootView.findViewById(R.id.profileUserLikes);
		userLovesEat = (TextView) rootView.findViewById(R.id.profileUserLikes);
		userRoleMode = (TextView) rootView.findViewById(R.id.profileUserRoleMode);
	}
}
