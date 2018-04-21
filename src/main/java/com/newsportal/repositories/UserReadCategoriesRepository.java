package com.newsportal.repositories;

import com.newsportal.models.UserReadCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadCategoriesRepository extends JpaRepository<UserReadCategories, Long> {
}
