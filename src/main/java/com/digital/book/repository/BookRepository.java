package com.digital.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digital.book.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>{
	
	@Query(value = "SELECT * FROM digital_book.book_table b where b.category = :category or b.title = :title or b.author = :author or b.price = :price or b.publisher = :publisher", nativeQuery = true)
	List<BookEntity> searchAllBooks(@Param("category") String category, @Param("title") String title, @Param("author") String author, @Param("price") String price, @Param("publisher") String publisher);

}
