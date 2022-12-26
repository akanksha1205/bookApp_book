package com.digital.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.digital.book.model.Book;
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
import com.digital.book.repository.BookRepository;

@Service
public interface BookService {
	
	public List<Book> searchBook(String category, String title, String author, String price, String publisher) throws Exception;
		
	public SignUpResponse signUp(SignUpRequest signUpRequest);
	
	//public SignInResponse signIn(SignInRequest signInRequest);
	
	public SubscribeResponse bookSubscription(SubscribeRequest subscribeRequest);
	
	public SubscribedBookResponse subscribedBook(Integer userId);
	
	public SubscribedBookAccessResponse subscribedBookResult(Integer userId, Integer subscriptionId);
	
	public SubscribedBookAccessResponse readContent(Integer userId, Integer subscriptionId);
	
	public SubscriptionCancelResponse cancelSubscription(SubscriptionCancelRequest subscriptionCancelRequest);
}
