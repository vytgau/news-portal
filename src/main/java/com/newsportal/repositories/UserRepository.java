package com.newsportal.repositories;

import com.newsportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Modifying
    @Transactional
    void deleteById(long id);


    List<User> findByUsernameContaining(String searchTerm);

    User save(User user);

    List<User> findAll();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
