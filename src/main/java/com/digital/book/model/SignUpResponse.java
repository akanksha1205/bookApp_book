package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpResponse {
	
	@JsonProperty(value = "UserID")
	private int userId;
	@JsonProperty(value = "SuccessMessage")
	private String successMessage;
	@JsonProperty(value = "errorInfo")
	private ErrorInfo errorInfo;
	
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	

	
}
