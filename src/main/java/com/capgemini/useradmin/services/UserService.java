package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        repository = userRepository;
    }
}
