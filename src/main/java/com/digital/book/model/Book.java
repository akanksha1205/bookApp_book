package com.digital.book.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {

	@JsonProperty("bookID")
	private int bookID;
	@JsonProperty("title")
	private String title;
	@JsonProperty("author")
	private String author;
	@JsonProperty("publisher")
	private String publisher;
	@JsonProperty("releasedDate")
	private String releasedDate;
	@JsonProperty("category")
	private String category;
	@JsonProperty("price")
	private String price;
	@JsonProperty("active")
	private String active;
	@JsonProperty("content")
	private String content;

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
