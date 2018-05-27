package com.newsportal.repositories;

import com.newsportal.models.Category;
import com.newsportal.models.UserReadCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.newsportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserReadCategoriesRepository extends JpaRepository<UserReadCategories, Long> {
    List<UserReadCategories> findAll();
}
