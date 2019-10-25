package com.batuhan.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.batuhan.model.Book;
import com.batuhan.repository.BookRepository;


@Service
@ComponentScan
public class BookDAO {

    @Autowired
    BookRepository bookRepository;

    /*to save/add an Book*/

    public Book save(Book b) {
        return bookRepository.save(b);
    }


    /* search all books*/

    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    /*get an employee by id*/
    public Book findOne(Long bid) {
        return bookRepository.findOne(bid);
    }


    /*delete an employee*/

    public void delete(Book b) {
        bookRepository.delete(b);
    }
}

