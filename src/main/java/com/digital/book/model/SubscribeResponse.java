package com.digital.book.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeResponse {
	

	@JsonProperty("SubscriptionID")
	private Integer subscriptionID;
	
	@JsonProperty("SubscribedBook")
	private Book book;
	
	@JsonProperty("errorInfo")
	private ErrorInfo errorInfo;
	

	public Integer getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(Integer subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

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
	
	
}
