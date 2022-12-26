package com.digital.book.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeRequest {
	
	@JsonProperty("bookID")
	private int bookID;
	@JsonProperty("userID")
	private Integer userID;
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	

}
