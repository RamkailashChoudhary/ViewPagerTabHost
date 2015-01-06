package com.app.gco.webrequest;

import android.content.Context;

import com.app.gco.delegates.ResponseDelegate;
import com.loopj.android.http.RequestParams;

public class RequestDataModule {

	private RequestParams params;
	private int requestTag;
	private String url;
	private int methodType;
	private Class classType;
	private ResponseDelegate responseDelegate;
	private Context context;
	private boolean isProgressShow;

	public RequestDataModule(Context context) {

		setResponseDelegate((ResponseDelegate) context);
		setContext(context);
		setProgressShow(true);
	}

	public void execute() {

		GCOServerRequest mITTGroupService = new GCOServerRequest(this);
		mITTGroupService.execute();
	}

	public String getUrl() {
		
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRequestTag() {
		return requestTag;
	}

	public void setRequestTag(int requestTag) {
		this.requestTag = requestTag;
	}

	public RequestParams getParams() {
		return params;
	}

	public void setParams(RequestParams params) {
		this.params = params;
	}

	public int getMethodType() {
		return methodType;
	}

	public void setMethodType(int methodType) {
		this.methodType = methodType;
	}

	public Class getClassType() {
		return classType;
	}

	public void setClassType(Class classType) {
		this.classType = classType;
	}

	public ResponseDelegate getResponseDelegate() {
		return responseDelegate;
	}

	public void setResponseDelegate(ResponseDelegate responseDelegate) {
		this.responseDelegate = responseDelegate;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public boolean isProgressShow() {
		return isProgressShow;
	}

	public void setProgressShow(boolean isProgressShow) {
		this.isProgressShow = isProgressShow;
	}

}
