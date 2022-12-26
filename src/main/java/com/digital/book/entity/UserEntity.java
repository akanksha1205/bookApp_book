package com.digital.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	//@NotNull
	private Integer userID;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "email_id")
	private String emailID;
	@Column(name = "password")
	private String password;
	
	public UserEntity() {
		
	}

	public UserEntity(String userName, String userType, String emailID, String password) {
		super();
		this.userName = userName;
		this.userType = userType;
		this.emailID = emailID;
		this.password = password;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

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