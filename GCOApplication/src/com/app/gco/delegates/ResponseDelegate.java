package com.app.gco.delegates;

public interface ResponseDelegate {

	public void onSuccess(BaseResponse response,int tag);
	public void onFailure(String errorMsg);
}
