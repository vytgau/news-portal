package com.newsportal.services;

import com.newsportal.models.User;
import com.newsportal.viewmodels.UsersSearchItem;

import java.util.List;

public interface UserService {

    User findById(long id);

    User findByUsername(String username);

    List<User> findByUsernameContaining(String searchTerm);

    List<UsersSearchItem> searchUsers(String searchTerm, long groupId, String currentUserUsername);

    User registerNewUserAccount(User user);

    User save(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
