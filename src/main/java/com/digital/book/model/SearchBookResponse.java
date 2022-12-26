package com.digital.book.model;

import java.util.List;

public class SearchBookResponse {
	
	private List<Book> bookList;
	
	private ErrorInfo errorInfo;

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
	
	

}
