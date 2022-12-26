package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInResponse {

	@JsonProperty(value = "SuccessMessage")
	private String successMessage;
	@JsonProperty(value = "errorInfo")
	private ErrorInfo errorInfo;
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
	
}
