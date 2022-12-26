package com.digital.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.book.model.Book;
import com.digital.book.model.ErrorInfo;
import com.digital.book.model.SearchBookResponse;
import com.digital.book.model.SignInRequest;
import com.digital.book.model.SignInResponse;
import com.digital.book.model.SignUpRequest;
import com.digital.book.model.SignUpResponse;
import com.digital.book.model.SubscribeRequest;
import com.digital.book.model.SubscribeResponse;
import com.digital.book.model.SubscribedBookAccessResponse;
import com.digital.book.model.SubscribedBookResponse;
import com.digital.book.model.SubscriptionCancelRequest;
import com.digital.book.model.SubscriptionCancelResponse;
import com.digital.book.service.BookService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/digitalbooks")
public class EndPoint {
	
	@Autowired
	BookService bookService;

	@GetMapping("/search/")
	public ResponseEntity<SearchBookResponse> searchBook(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("price") String price, @RequestParam("publisher") String publisher) {
		SearchBookResponse searchBookResponse = new SearchBookResponse();
		ErrorInfo errorInfo = new ErrorInfo();
		try {
			List<Book> bookList = bookService.searchBook(category, title, author, price, publisher);
			searchBookResponse.setBookList(bookList);
		}catch(Exception e) {
			errorInfo.setErrorMessage(e.getMessage());
			searchBookResponse.setErrorInfo(errorInfo);
			return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<SubscribeResponse> subscribeBook(@RequestBody SubscribeRequest subscribeRequest){
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		subscribeResponse = bookService.bookSubscription(subscribeRequest);
		return new ResponseEntity<SubscribeResponse>(subscribeResponse, HttpStatus.OK);
	}

	@GetMapping("/reader/book/{userId}")
	public ResponseEntity<SubscribedBookResponse> subscribedUser(@PathVariable("userId") Integer userId){
		
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		subscribedBookResponse = bookService.subscribedBook(userId);
		return new ResponseEntity<SubscribedBookResponse>(subscribedBookResponse, HttpStatus.OK);
	}
	
	@GetMapping("/reader/book/{userId}/{subscriptionId}")
	public ResponseEntity<SubscribedBookAccessResponse> subscribedBookDetail(@PathVariable("userId") Integer userId, @PathVariable("subscriptionId") Integer subscriptionId){
		
		SubscribedBookAccessResponse SubscribedBookAccessResponse = new SubscribedBookAccessResponse();
		SubscribedBookAccessResponse = bookService.subscribedBookResult(userId, subscriptionId);
		return new ResponseEntity<SubscribedBookAccessResponse>(SubscribedBookAccessResponse, HttpStatus.OK);
	}
	
	@GetMapping("/reader/book/read/{userId}/{subscriptionId}")
	public ResponseEntity<SubscribedBookAccessResponse> readContent(@PathVariable("userId") Integer userId, @PathVariable("subscriptionId") Integer subscriptionId){
		
		SubscribedBookAccessResponse SubscribedBookAccessResponse = new SubscribedBookAccessResponse();
		SubscribedBookAccessResponse = bookService.readContent(userId, subscriptionId);
		return new ResponseEntity<SubscribedBookAccessResponse>(SubscribedBookAccessResponse, HttpStatus.OK);
	}
	
	@PostMapping("/subscribe/cancel")
	public ResponseEntity<SubscriptionCancelResponse> cancelSubscription(@RequestBody SubscriptionCancelRequest subscriptionCancelRequest){
		
		SubscriptionCancelResponse subscriptionCancelResponse = new SubscriptionCancelResponse();
		subscriptionCancelResponse = bookService.cancelSubscription(subscriptionCancelRequest);
		return new ResponseEntity<SubscriptionCancelResponse>(subscriptionCancelResponse, HttpStatus.OK);
	}
}
