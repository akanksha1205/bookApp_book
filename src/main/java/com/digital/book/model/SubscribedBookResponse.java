package com.digital.book.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribedBookResponse {

	private List<Book> bookList;
	
	private ErrorInfo errorInfo;
	
	@JsonProperty("UserId")
	private Integer userId;


	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
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
	
	
	
}
