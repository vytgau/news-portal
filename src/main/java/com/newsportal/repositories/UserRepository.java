package com.newsportal.repositories;

import com.newsportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByUsernameContaining(String searchTerm);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
