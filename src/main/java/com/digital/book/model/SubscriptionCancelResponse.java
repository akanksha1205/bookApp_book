package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionCancelResponse {

	@JsonProperty("successMsg")
	private String successMsg;
	@JsonProperty("errorInfo")
	private ErrorInfo errorInfo;
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
	
}
