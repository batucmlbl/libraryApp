package com.batuhan.repository;

import com.batuhan.model.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;


@ComponentScan
public interface BookRepository extends JpaRepository<Book, Long> {
}


