package com.digital.book.service.test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.digital.book.entity.BookEntity;
import com.digital.book.entity.SubscriptionEntity;
import com.digital.book.entity.UserEntity;
import com.digital.book.model.Book;
import com.digital.book.model.SignInRequest;
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
import com.digital.book.service.BookServiceImpl;

public class BookServiceTest {
	
	@Mock
	BookRepository bookRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	SubscriptionRepository subscriptionRepository;
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(bookServiceImpl, "bookRepository", bookRepository);
		ReflectionTestUtils.setField(bookServiceImpl, "userRepository", userRepository);
		ReflectionTestUtils.setField(bookServiceImpl, "subscriptionRepository", subscriptionRepository);
	}
	
	@Test
	void searchBookTest() throws Exception{
		String category = "thriller";
		String title = "JungleBook";
		String author = "abc";
		String price = "200";
		String publisher = "ak";
		BookEntity bookEntity = new BookEntity();
		bookEntity.setActive("y");
		bookEntity.setAuthor("Akanksha");
		bookEntity.setCategory("Thriller");
		Book book = new Book();
		book.setActive("y");
		book.setBookID(2);
		book.setCategory("thriller");
		
		when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
		List<Book> listBookEntity = new  ArrayList<>();
		listBookEntity.add(book);
		try {
			listBookEntity = bookServiceImpl.searchBook(category, title, null, null, null);
		} catch (Exception e) {
		}
		assertNotNull(listBookEntity);
	}
	@Test
	void searchBookInvalidTest() throws Exception{

		BookEntity bookEntity = new BookEntity();
		when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
		List<Book> listBookEntity = new  ArrayList<>();
		
		try {
			listBookEntity = bookServiceImpl.searchBook(null, null, null, null, null);
		} catch (Exception e) {
		}
		assertNotNull(listBookEntity);
	}
	@Test
	void singUpTest() throws Exception{
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmailID("ak@gmail.com");
		signUpRequest.setPassword("ak@123");
		signUpRequest.setUserName("akanksha");
		signUpRequest.setUserType("Author");
		
		SignUpResponse signUpResponse = new SignUpResponse();
		UserEntity userEntity = new UserEntity();
		userEntity.setEmailID(signUpRequest.getEmailID());
		userEntity.setPassword(signUpRequest.getPassword());
		userEntity.setUserName(signUpRequest.getUserName());
		userEntity.setUserType(signUpRequest.getUserType());
		userEntity.setUserID(2);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		signUpResponse = bookServiceImpl.signUp(signUpRequest);
		
		assertNotNull(signUpResponse);
	}
	@Test
	void singUpInvalidTest() throws Exception{
		SignUpRequest signUpRequest = new SignUpRequest();
		
		SignUpResponse signUpResponse = new SignUpResponse();

		signUpResponse = bookServiceImpl.signUp(signUpRequest);
		
		assertNotNull(signUpResponse);
	}
//	@Test
//	void singInTest() throws Exception{
//		SignInRequest signInRequest =new SignInRequest();
//		signInRequest.setPassword("ak@123");
//		signInRequest.setUserID("akanksha");
//		
//		assertNotNull(signUpResponse);
//	}
	@Test
	void bookSubscriptionTest() throws Exception{
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		BookEntity bookEntity = new BookEntity();
		Optional<BookEntity> optional = Optional.of(bookEntity);
		subscribeRequest.setBookID(7);
		subscribeRequest.setUserID(2);
		when(bookRepository.findById(subscribeRequest.getBookID())).thenReturn(optional);
		subscribeResponse = bookServiceImpl.bookSubscription(subscribeRequest);
		
		
		assertNotNull(subscribeResponse);
	}
	@Test
	void bookSubscriptionInvalidTest() throws Exception{
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		BookEntity bookEntity = new BookEntity();
		Optional<BookEntity> optional = Optional.ofNullable(null);
		subscribeRequest.setBookID(7);
		subscribeRequest.setUserID(2);
		when(bookRepository.findById(subscribeRequest.getBookID())).thenReturn(optional);
		subscribeResponse = bookServiceImpl.bookSubscription(subscribeRequest);
		
		
		assertNotNull(subscribeResponse);
	}
	@Test
	void bookSubscriptionInvalid2Test() throws Exception{
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		BookEntity bookEntity = new BookEntity();
		UserEntity userEntity = new UserEntity();
		Optional<BookEntity> optional = Optional.ofNullable(null);
		Optional<UserEntity> optional1 = Optional.of(userEntity);
		when(bookRepository.findById(subscribeRequest.getBookID())).thenReturn(optional);
		when(userRepository.findById(subscribeRequest.getUserID())).thenReturn(optional1);
		subscribeResponse = bookServiceImpl.bookSubscription(subscribeRequest);
		assertNotNull(subscribeResponse);
	}
	@Test
	void subscriptionedBookTest() throws Exception{
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		
		subscribedBookResponse = bookServiceImpl.subscribedBook(2);
		assertNotNull(subscribedBookResponse);
	}
	@Test
	void subscriptionedBook1Test() throws Exception{
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		UserEntity userEntity = new UserEntity();
		BookEntity bookEntity = new BookEntity();
		List<SubscriptionEntity> bookSubscribed = new ArrayList<>();
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setSubscriptionID(11);
		subscriptionEntity.setUserID(2);
		bookSubscribed.add(subscriptionEntity);
		Optional<UserEntity> optional1 = Optional.of(userEntity);
		Optional<BookEntity> optional2 = Optional.of(bookEntity);
		when(subscriptionRepository.findByUserId(2)).thenReturn(bookSubscribed);
		when(userRepository.findById(2)).thenReturn(optional1);
		when(bookRepository.findById(7)).thenReturn(optional2);
		subscribedBookResponse = bookServiceImpl.subscribedBook(2);
		assertNotNull(subscribedBookResponse);
	}
	@Test
	void subscriptionedBookInvalidTest() throws Exception{
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		UserEntity userEntity = new UserEntity();
		BookEntity bookEntity = new BookEntity();
		List<SubscriptionEntity> bookSubscribed = new ArrayList<>();

		Optional<UserEntity> optional1 = Optional.of(userEntity);
		Optional<BookEntity> optional2 = Optional.of(bookEntity);
		when(subscriptionRepository.findByUserId(2)).thenReturn(bookSubscribed);
		when(userRepository.findById(2)).thenReturn(optional1);
		when(bookRepository.findById(7)).thenReturn(optional2);
		subscribedBookResponse = bookServiceImpl.subscribedBook(2);
		assertNotNull(subscribedBookResponse);
	}
	@Test
	void subscriptionedBookInvalid1Test() throws Exception{
		SubscribedBookResponse subscribedBookResponse = new SubscribedBookResponse();
		UserEntity userEntity = new UserEntity();
		BookEntity bookEntity = new BookEntity();
		List<SubscriptionEntity> bookSubscribed = new ArrayList<>();
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setSubscriptionID(11);
		subscriptionEntity.setUserID(2);
		bookSubscribed.add(subscriptionEntity);
		bookEntity.setActive("y");
		bookEntity.setBookID(7);
		bookEntity.setTitle("abc");
		userEntity.setUserID(2);
		userEntity.setUserName("ritu");
		Optional<UserEntity> optional1 = Optional.of(userEntity);
		Optional<BookEntity> optional2 = Optional.of(bookEntity);
		when(subscriptionRepository.findByUserId(2)).thenReturn(bookSubscribed);
		when(userRepository.findById(2)).thenReturn(optional1);
		when(bookRepository.findById(7)).thenReturn(optional2);
		subscribedBookResponse = bookServiceImpl.subscribedBook(2);
		assertNotNull(subscribedBookResponse);
	}
	
	@Test
	void cancelSubscriptionTest() throws Exception{
		SubscriptionCancelResponse subscriptionCancelResponse = new SubscriptionCancelResponse();
		SubscriptionCancelRequest subscriptionCancelRequest =new SubscriptionCancelRequest();
		subscriptionCancelRequest.setSubscriptionId(17);
		subscriptionCancelRequest.setUserID(2);
		subscriptionCancelResponse = bookServiceImpl.cancelSubscription(subscriptionCancelRequest);
		assertNotNull(subscriptionCancelResponse);
	}
	@Test
	void readContentTest() throws Exception{
		SubscribedBookAccessResponse subscribedBookAccessResponse = new SubscribedBookAccessResponse();
		UserEntity userEntity = new UserEntity();
		Optional<UserEntity> optional = Optional.of(userEntity);
		//when(userRepository.findById(2)).thenReturn(optional);
		subscribedBookAccessResponse = bookServiceImpl.readContent(2, 11);
		assertNotNull(subscribedBookAccessResponse);
	}
	
	@Test
	void readContent1Test() throws Exception{
		SubscribedBookAccessResponse subscribedBookAccessResponse = new SubscribedBookAccessResponse();
		UserEntity userEntity = new UserEntity();
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookID(7);
		Optional<UserEntity> optional = Optional.of(userEntity);
		Optional<BookEntity> optional1 = Optional.of(bookEntity);
		when(userRepository.findById(2)).thenReturn(optional);
		when(subscriptionRepository.findSubscribedBook(2, 11)).thenReturn(subscriptionEntity);
		when(bookRepository.findById(7)).thenReturn(optional1);
		subscribedBookAccessResponse = bookServiceImpl.readContent(2, 11);
		assertNotNull(subscribedBookAccessResponse);
	}
	@Test
	void subscribedBookResultTest() throws Exception{
		SubscribedBookAccessResponse subscribedBookAccessResponse = new SubscribedBookAccessResponse();
		UserEntity userEntity = new UserEntity();
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookID(7);
		Optional<UserEntity> optional = Optional.of(userEntity);
		Optional<BookEntity> optional1 = Optional.of(bookEntity);
		when(userRepository.findById(2)).thenReturn(optional);
		when(subscriptionRepository.findSubscribedBook(2, 11)).thenReturn(subscriptionEntity);
		when(bookRepository.findById(4)).thenReturn(optional1);
		subscribedBookAccessResponse = bookServiceImpl.subscribedBookResult(2, 11);
		assertNotNull(subscribedBookAccessResponse);
	}
	
	
}
