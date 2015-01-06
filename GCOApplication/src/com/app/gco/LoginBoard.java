package com.app.gco;

import android.content.Intent;
import android.view.View;
import com.app.gco.delegates.ServerAPI;

public class LoginBoard extends LoginBoardBaseActivity{

	@Override
	protected int getResourceLayout() {

		return R.layout.login_board;
	}

	@Override
	public void onClick(View v) {
		
		if(v == signInButton){
			
			Intent signInsignUp = new Intent(LoginBoard.this,LoginSignUpActivity.class);
			signInsignUp.putExtra(ServerAPI.BOARD_CLICK_MODULE, ServerAPI.SIGNIN_MODULE);
			startActivity(signInsignUp);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			
		}else if(v == signUpButton){
			
			Intent signInsignUp = new Intent(LoginBoard.this,LoginSignUpActivity.class);
			signInsignUp.putExtra(ServerAPI.BOARD_CLICK_MODULE, ServerAPI.SIGNUP_MODULE);
			startActivity(signInsignUp);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		}else if(v == googlePlusSignIn){
			
			signInWithGplus();
		}
	}
}
