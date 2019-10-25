package com.batuhan.controller;

import com.batuhan.dao.BookDAO;
import com.batuhan.dao.UserDAO;
import com.batuhan.model.Book;
import com.batuhan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
public class UserController {
    @Autowired
    BookDAO bookDAO;

    @Autowired
    UserDAO userDAO;

    @GetMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks(){
        return bookDAO.findAll();
    }

    @GetMapping("/user")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<User> getAllUser(){
        return userDAO.findAll();
    }

    @PostMapping("/user/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public User createUser(@Valid @RequestBody User u) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(u.getPassword());
        u.setPassword(encodedPassword);

        if(u.getRole() == null){
            u.setRole("ROLE_USER");
            u.setActive(1);
        }

        return userDAO.save(u);
    }

    @DeleteMapping("/user/delete/{id}")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<User> deleteUser(@PathVariable(value="id") Long bid){

        User u= userDAO.findOne(bid);
        if(u==null) {
            return ResponseEntity.notFound().build();
        }
        userDAO.delete(u);

        return ResponseEntity.ok().build();


    }

    @PutMapping("/user/{id}")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") Long bid,@Valid @RequestBody User bDetails){

        User u=userDAO.findOne(bid);
        if(u==null) {
            return ResponseEntity.notFound().build();
        }

        u.setName(bDetails.getName());
        u.setLastName(bDetails.getLastName());
        u.setEmail(bDetails.getEmail());
        u.setPassword(bDetails.getPassword());


        User updateUser=userDAO.save(u);
        return ResponseEntity.ok().body(updateUser);



    }
}

