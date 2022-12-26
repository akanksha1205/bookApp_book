package com.digital.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digital.book.entity.BookEntity;
import com.digital.book.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	@Query(value = "SELECT * FROM digital_book.user_table u where u.user_id = :userID and u.password = :password", nativeQuery = true)
	UserEntity findUser(@Param("userID") Integer userID, @Param("password") String password);

}
