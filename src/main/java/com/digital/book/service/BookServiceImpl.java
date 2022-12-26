package com.digital.book.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.digital.book.dao.SearchBookDAO;
import com.digital.book.entity.BookEntity;
import com.digital.book.entity.SubscriptionEntity;
import com.digital.book.entity.UserEntity;
import com.digital.book.model.Book;
import com.digital.book.model.ErrorInfo;
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
import com.digital.book.repository.SubscriptionRepository;
import com.digital.book.repository.UserRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private SearchBookDAO serachBookDAO;

	@Override
	public List<Book> searchBook(String category, String title, String author, String price, String publisher) throws Exception {
		String g = "g";
		List<BookEntity> listBookEntity = bookRepository.searchAllBooks(category, title, author, price, publisher);
		//List<Book> result = serachBookDAO.convertDtoToVo(listBook);
		if(listBookEntity.isEmpty()) {
		}
		List<Book> bookList = new ArrayList<>();
		
		for(BookEntity e : listBookEntity) {
			Book book = new Book();
			book.setActive(e.getActive());
			book.setAuthor(e.getAuthor());
			book.setBookID(e.getBookID());
			book.setCategory(e.getCategory());
			book.setContent(e.getContent());
			book.setPrice(e.getPrice());
			book.setPublisher(e.getPublisher());
			book.setReleasedDate(e.getReleasedDate());
			book.setTitle(e.getTitle());
			
			bookList.add(book);
		}
		
		return bookList;
		
	}

	@Override
	public SignUpResponse signUp(SignUpRequest signUpRequest) {
		SignUpResponse signUpResponse = new SignUpResponse();
		UserEntity userEntity = new UserEntity();
		userEntity.setEmailID(signUpRequest.getEmailID());
		userEntity.setPassword(signUpRequest.getPassword());
		userEntity.setUserName(signUpRequest.getUserName());
		userEntity.setUserType(signUpRequest.getUserType());
	
		
		try {
			//userEntity = userRepository.save(signUpRequest);
		userEntity = userRepository.save(userEntity);
		} catch (Exception e) {
			return signUpResponse;
		}
		if(userEntity!=null) {
			signUpResponse.setUserId(userEntity.getUserID());
		}
		signUpResponse.setSuccessMessage("Account has been successfully created");
		
		return signUpResponse;
	}

//	@Override
//	public SignInResponse signIn(SignInRequest signInRequest) {
//		
//		UserEntity userEntity = new UserEntity();
//		SignInResponse signInResponse = new SignInResponse();
//		
//		try {
//			userEntity = userRepository.findUser(signInRequest.getUserID(),signInRequest.getPassword());
//			if(userEntity.getUserID()!=null && userEntity.getUserID()==signInRequest.getUserID() && userEntity.getPassword().equals(signInRequest.getPassword()) ) {
//				signInResponse.setSuccessMessage("Successfully logged in");
//			}
//		} catch (Exception e) {
//			ErrorInfo errorInfo = new ErrorInfo();
//			errorInfo.setErrorMessage("UserId or password is incorrect");
//			signInResponse.setErrorInfo(errorInfo);
//		}
//		
//		return signInResponse;
//	}

	@Override
	public SubscribeResponse bookSubscription(SubscribeRequest subscribeRequest) {
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		BookEntity bookEntity = new BookEntity();
		Optional<BookEntity> optional = bookRepository.findById(subscribeRequest.getBookID());
		if(optional.isPresent()) {
			bookEntity = optional.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("No such book found");
			subscribeResponse.setErrorInfo(errorInfo);
			
			return subscribeResponse;
		}
		
		UserEntity userEntity = new UserEntity();
		Optional<UserEntity> optionalUser = userRepository.findById(subscribeRequest.getUserID());
		if(!optionalUser.isPresent()) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("No such user exist");
			subscribeResponse.setErrorInfo(errorInfo);
			
			return subscribeResponse;
		}
		
		userEntity = optionalUser.get();
		
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setBookID(subscribeRequest.getBookID());
		subscriptionEntity.setUserID(subscribeRequest.getUserID());
		subscriptionEntity.setSubscriptionDate(LocalDateTime.now());
		subscriptionEntity = subscriptionRepository.save(subscriptionEntity);
		
		subscribeResponse.setSubscriptionID(subscriptionEntity.getSubscriptionID());
		Book book  = new Book();
		book.setActive(bookEntity.getActive());
		book.setAuthor(bookEntity.getAuthor());
		book.setBookID(bookEntity.getBookID());
		book.setCategory(bookEntity.getCategory());
		book.setContent(bookEntity.getContent());
		book.setPrice(bookEntity.getPrice());
		book.setPublisher(bookEntity.getPublisher());
		book.setReleasedDate(bookEntity.getReleasedDate());
		book.setTitle(bookEntity.getTitle());
		
		subscribeResponse.setBook(book);
		
		return subscribeResponse;
	}

	@Override
	public SubscribedBookResponse subscribedBook(Integer userId) {
		
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		UserEntity userEntity = new UserEntity();
		
		Optional<UserEntity> optional = userRepository.findById(userId);
		
		if(optional.isPresent()) {
			userEntity = optional.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("User Not Found");
			subscribedBookResponse.setErrorInfo(errorInfo);
			
			return subscribedBookResponse;
		}
		
		List<SubscriptionEntity> bookSubscribed = subscriptionRepository.findByUserId(userId);
		
		if(bookSubscribed.size()==0) {
			
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("Book not subscribed to this User");
			subscribedBookResponse.setErrorInfo(errorInfo);
			
			return subscribedBookResponse;
		}
		
		List<Book> bookList = new ArrayList<>();
		for(SubscriptionEntity s : bookSubscribed) {
			
			BookEntity bookEntity = new BookEntity();
			Integer bookId = s.getBookID();
			Optional<BookEntity> optionalBook = bookRepository.findById(bookId); 
			
			if(optionalBook.isPresent()) {
				bookEntity = optionalBook.get();
				
			}
			else {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setErrorMessage("Book Id not Present");
				subscribedBookResponse.setErrorInfo(errorInfo);
				
				return subscribedBookResponse;
			}
			Book book = new Book();
			book.setActive(bookEntity.getActive());
			book.setAuthor(bookEntity.getAuthor());
			book.setBookID(bookEntity.getBookID());
			book.setCategory(bookEntity.getCategory());
			book.setContent(s.getSubscriptionID().toString());
			book.setPrice(bookEntity.getPrice());
			book.setPublisher(bookEntity.getPublisher());
			book.setReleasedDate(bookEntity.getReleasedDate());
			book.setTitle(bookEntity.getTitle());
			
			bookList.add(book);
			
		}
		
		subscribedBookResponse.setBookList(bookList);
		return subscribedBookResponse;
	}

	@Override
	public SubscribedBookAccessResponse subscribedBookResult(Integer userId, Integer subscriptionId) {
		
		SubscribedBookAccessResponse subscribedBookAccessResponse = new SubscribedBookAccessResponse();
		UserEntity userEntity = new UserEntity();
		
		Optional<UserEntity> optional = userRepository.findById(userId);
		
		if(optional.isPresent()) {
			userEntity = optional.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("User Not Found");
			subscribedBookAccessResponse.setErrorInfo(errorInfo);
			
			return subscribedBookAccessResponse;
		}
		
		SubscriptionEntity bookSubscribed = subscriptionRepository.findSubscribedBook(userId, subscriptionId);
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		BookEntity bookEntity = new BookEntity();
		Integer bookId = bookSubscribed.getBookID();
		
		Optional<BookEntity> optionalBook = bookRepository.findById(bookId); 
		if(optionalBook.isPresent()) {
			bookEntity = optionalBook.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("Book Id not Present");
			subscribedBookAccessResponse.setErrorInfo(errorInfo);
			
			return subscribedBookAccessResponse;
		}
		
//		Optional<SubscriptionEntity> optionalSub = subscriptionRepository.findById(subscriptionId); 
//		if(optionalSub.isPresent()) {
//			subscriptionEntity = optionalSub.get();
//			
//		}
//		else {
//			ErrorInfo errorInfo = new ErrorInfo();
//			errorInfo.setErrorMessage("Subscription Id not Present");
//			subscribedBookAccessResponse.setErrorInfo(errorInfo);
//			
//			return subscribedBookAccessResponse;
//		}
		Book book = new Book();
		book.setActive(bookEntity.getActive());
		book.setAuthor(bookEntity.getAuthor());
		book.setBookID(bookEntity.getBookID());
		book.setCategory(bookEntity.getCategory());
		book.setContent(bookEntity.getContent());
		book.setPrice(bookEntity.getPrice());
		book.setPublisher(bookEntity.getPublisher());
		book.setReleasedDate(bookEntity.getReleasedDate());
		book.setTitle(bookEntity.getTitle());
		
		subscribedBookAccessResponse.setBook(book);
		
		return subscribedBookAccessResponse;
	}

	@Override
	public SubscribedBookAccessResponse readContent(Integer userId, Integer subscriptionId) {
		
		SubscribedBookAccessResponse subscribedBookAccessResponse = new SubscribedBookAccessResponse();
		UserEntity userEntity = new UserEntity();
		
		Optional<UserEntity> optional = userRepository.findById(userId);
		
		if(optional.isPresent()) {
			userEntity = optional.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("User Not Found");
			subscribedBookAccessResponse.setErrorInfo(errorInfo);
			
			return subscribedBookAccessResponse;
		}
		
		SubscriptionEntity bookSubscribed = subscriptionRepository.findSubscribedBook(userId, subscriptionId);
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		BookEntity bookEntity = new BookEntity();
		Integer bookId = bookSubscribed.getBookID();
		
		Optional<BookEntity> optionalBook = bookRepository.findById(bookId); 
		if(optionalBook.isPresent()) {
			bookEntity = optionalBook.get();
			
		}
		else {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("Book Id not Present");
			subscribedBookAccessResponse.setErrorInfo(errorInfo);
			
			return subscribedBookAccessResponse;
		}
		
		Book book = new Book();
		book.setBookID(bookEntity.getBookID());
		book.setContent(bookEntity.getContent());
		book.setTitle(bookEntity.getTitle());
		
		subscribedBookAccessResponse.setBook(book);

		return subscribedBookAccessResponse;
	}

	@Override
	public SubscriptionCancelResponse cancelSubscription(SubscriptionCancelRequest subscriptionCancelRequest) {
		SubscriptionCancelResponse subscriptionCancelResponse = new SubscriptionCancelResponse();		
		Optional<SubscriptionEntity> optional = subscriptionRepository.findById(subscriptionCancelRequest.getSubscriptionId());
		if(optional.isEmpty()) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("Subscription Id not Present");
			subscriptionCancelResponse.setErrorInfo(errorInfo);
			
			return subscriptionCancelResponse;
		}
		
		SubscriptionEntity subscriptionEntity = optional.get();
		if(ChronoUnit.HOURS.between(subscriptionEntity.getSubscriptionDate(), LocalDateTime.now())>24) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage("Subscription cancellation not possible after 24 hours");
			subscriptionCancelResponse.setErrorInfo(errorInfo);
			
			return subscriptionCancelResponse;
		}
		
		subscriptionRepository.deleteById(subscriptionCancelRequest.getSubscriptionId());
		subscriptionCancelResponse.setSuccessMsg("Subscription cancelled");
		return subscriptionCancelResponse;
	}

	
	
}
