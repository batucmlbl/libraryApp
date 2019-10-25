package com.batuhan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.batuhan.dao.BookDAO;
import com.batuhan.model.Book;

@RestController
@RequestMapping("/library")
@ComponentScan
public class BookController {

    @Autowired
    BookDAO bookDAO;


    @PostMapping("/books")
    @CrossOrigin(origins ="http://localhost:3000")
    public Book createBook(@Valid @RequestBody Book b) {
        return bookDAO.save(b);
    }


    @GetMapping("/books")
    @CrossOrigin(origins ="http://localhost:3000")
    public List<Book> getAllBooks(){
        return bookDAO.findAll();
    }

    @GetMapping("/books/{id}")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<Book> getBookById(@PathVariable(value="id") Long bid){

        Book b=bookDAO.findOne(bid);

        if(b==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(b);

    }


    @PutMapping("/books/{id}")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<Book> updateBooks(@PathVariable(value="id") Long bid,@Valid @RequestBody Book bDetails){

        Book b=bookDAO.findOne(bid);
        if(b==null) {
            return ResponseEntity.notFound().build();
        }

        b.setName(bDetails.getName());
        b.setAuthorName(bDetails.getAuthorName());
        b.setReleaseYear(bDetails.getReleaseYear());

        Book updateBook=bookDAO.save(b);
        return ResponseEntity.ok().body(updateBook);



    }

    @DeleteMapping("/books/{id}")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<Book> deleteBook(@PathVariable(value="id") Long bid){

        Book b=bookDAO.findOne(bid);
        if(b==null) {
            return ResponseEntity.notFound().build();
        }
        bookDAO.delete(b);

        return ResponseEntity.ok().build();


    }

}



