package com.newsportal.services.implementation;

import com.newsportal.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl {

    public Page<Article> findArticlesForGuest() {
        return null;
    }

    public Page<Article> findArticlesForAuthenticatedUser() {
        return null;
    }
}
