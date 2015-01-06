package com.app.gco.webrequest;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;

import com.app.gco.delegates.BaseResponse;
import com.app.gco.delegates.ServerAPI;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GCOServerRequest {

	private AsyncHttpClient asyncHttpClient;
	private RequestDataModule requestDataModule;
	private ProgressDialog mProgressDialog;
	
	public GCOServerRequest(RequestDataModule requestDataModule){
		
		this.requestDataModule = requestDataModule;
	}
	
	public void execute(){
		
		asyncHttpClient = new AsyncHttpClient();
		
		if(requestDataModule.getMethodType() == ServerAPI.POST_METHOD){
		
			if(requestDataModule.getParams() != null)
				 asyncHttpClient.post(requestDataModule.getUrl(), requestDataModule.getParams(),responseHandler);
			else
				asyncHttpClient.post(requestDataModule.getUrl(),responseHandler);
		}else{
			
			if(requestDataModule.getParams() != null)
				 asyncHttpClient.get(requestDataModule.getUrl(), requestDataModule.getParams(),responseHandler);
			else
				asyncHttpClient.get(requestDataModule.getUrl(),responseHandler);
		}
	    	
	}
	
	private AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
		
		private BaseResponse mBaseresponae;
		public void onStart() {
		
			if(requestDataModule.isProgressShow())
			 showDialog();
		}
		
		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			
			try{
				
				if(mProgressDialog != null && mProgressDialog.isShowing()){
					
					mProgressDialog.dismiss();
				}
				String response = new String( arg2,0 , arg2.length );
				System.out.println("Print the Final Response :"+response);
				mBaseresponae = (BaseResponse)JsonParser.parseJson(response, requestDataModule.getClassType());
//				mBaseresponae = (BaseResponse)gson.fromJson(response, requestDataModule.getClassType());
				requestDataModule.getResponseDelegate().onSuccess(mBaseresponae, requestDataModule.getRequestTag());
			}catch(Exception ex){
				
				ex.printStackTrace();
			}
		}
		
		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
			
			if(mProgressDialog != null && mProgressDialog.isShowing()){
			
				mProgressDialog.dismiss();
				requestDataModule.getResponseDelegate().onFailure(new String(arg2, 0, arg2.length));
			}
		}
	};
	
	private void showDialog(){
		
		mProgressDialog = new ProgressDialog(requestDataModule.getContext());
		mProgressDialog.setMessage("Loading...");
//		mProgressDialog.setIndeterminateDrawable(requestDataModule.getContext().getResources().getDrawable(R.anim.loading_layout));
		mProgressDialog.setCancelable(false);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.show();
	}
}
