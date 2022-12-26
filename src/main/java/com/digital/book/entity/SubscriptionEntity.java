package com.digital.book.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscription_table")
public class SubscriptionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subscription_id")
	private Integer subscriptionID;
	@Column(name = "user_id")
	private Integer userID;
	@Column(name = "book_id")
	private Integer bookID;
	@Column(name = "subscription_date")
	private LocalDateTime subscriptionDate;
	
	
	public LocalDateTime getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(LocalDateTime subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public Integer getSubscriptionID() {
		return subscriptionID;
	}
	public void setSubscriptionID(Integer subscriptionID) {
		this.subscriptionID = subscriptionID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getBookID() {
		return bookID;
	}
	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}
	
	

}
