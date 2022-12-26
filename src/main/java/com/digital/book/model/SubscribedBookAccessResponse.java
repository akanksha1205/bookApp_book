package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribedBookAccessResponse {
	
	@JsonProperty("Book")
	private Book book;
	
	@JsonProperty("ErrorInfo")
	private ErrorInfo errorInfo;
	
	@JsonProperty("UserId")
	private Integer userId;
	
	@JsonProperty("SubscriptionId")
	private Integer subscriptionId;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	

}
