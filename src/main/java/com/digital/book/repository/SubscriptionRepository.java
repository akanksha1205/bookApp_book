package com.digital.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digital.book.entity.BookEntity;
import com.digital.book.entity.SubscriptionEntity;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer>{

	@Query(value = "SELECT * FROM digital_book.subscription_table s where s.user_id = :userId", nativeQuery = true)
	List<SubscriptionEntity> findByUserId(@Param("userId") Integer userId);
	
	@Query(value = "SELECT * FROM digital_book.subscription_table s where s.subscription_id = :subscriptionId and user_id = :userId", nativeQuery = true)
	SubscriptionEntity findSubscribedBook(@Param("userId") Integer userId, @Param("subscriptionId") Integer subscriptionId);
}
