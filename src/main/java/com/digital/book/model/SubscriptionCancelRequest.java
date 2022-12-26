package com.digital.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionCancelRequest {

	@JsonProperty("subscriptionId")
	private Integer subscriptionId;
	@JsonProperty("userID")
	private Integer userID;
	public Integer getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	
}
