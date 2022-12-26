package com.digital.book.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpRequest {
	
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("EmailID")
	private String emailID;
	@JsonProperty("Password")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
