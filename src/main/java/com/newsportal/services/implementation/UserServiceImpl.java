package com.newsportal.services.implementation;

import com.newsportal.models.User;
import com.newsportal.models.enums.Gender;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.UserRepository;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User registerNewUserAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //By default a new user has regular user's rights
        user.setRole(Role.REGULAR);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
