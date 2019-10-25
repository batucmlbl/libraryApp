package com.batuhan.dao;


import com.batuhan.model.Book;
import com.batuhan.model.User;
import com.batuhan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    public User save(User u) {
        return userRepository.save(u);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(Long bid) {
        return userRepository.findOne(bid);
    }

    public void delete(User u) {
        userRepository.delete(u);
    }
}

