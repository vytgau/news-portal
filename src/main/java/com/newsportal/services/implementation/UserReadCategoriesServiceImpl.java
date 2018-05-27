package com.newsportal.services.implementation;

import com.newsportal.models.UserReadCategories;
import com.newsportal.repositories.UserReadCategoriesRepository;
import com.newsportal.services.UserReadCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserReadCategoriesServiceImpl implements UserReadCategoriesService {

    @Autowired
    UserReadCategoriesRepository userReadCategoriesRepository;
    @Override
    public List<UserReadCategories> findAll()
    {
        return userReadCategoriesRepository.findAll();
    }
}
