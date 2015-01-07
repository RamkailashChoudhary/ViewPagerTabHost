package com.app.gco.dao;

import com.app.gco.delegates.BaseResponse;

public class LoginData implements BaseResponse{

	private String message;
	private String userId;
	private String name;
	private String email;
	private String dob;
	private String facebookd;
	private String twitterId;
	private String googleplusId;
	private String gender;
	private String like;
	private String loveseating;
	private String rolemodel;
	private String lang;
	private String userimage;
	private String userstatus;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getFacebookd() {
		return facebookd;
	}
	public void setFacebookd(String facebookd) {
		this.facebookd = facebookd;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getGoogleplusId() {
		return googleplusId;
	}
	public void setGoogleplusId(String googleplusId) {
		this.googleplusId = googleplusId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getLoveseating() {
		return loveseating;
	}
	public void setLoveseating(String loveseating) {
		this.loveseating = loveseating;
	}
	public String getRolemodel() {
		return rolemodel;
	}
	public void setRolemodel(String rolemodel) {
		this.rolemodel = rolemodel;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
}
