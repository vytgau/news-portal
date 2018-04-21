package com.newsportal.services;

import com.newsportal.models.User;

public interface UserService {

    User registerNewUserAccount(User user);

    User save(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
