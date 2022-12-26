package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInRequest {
	
	@JsonProperty("UserID")
	private Integer userID;
	@JsonProperty("Password")
	private String password;

	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
