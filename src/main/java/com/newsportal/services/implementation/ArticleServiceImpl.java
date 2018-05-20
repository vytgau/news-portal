package com.newsportal.services.implementation;

import com.newsportal.models.Article;
import com.newsportal.services.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Override
    public Page<Article> findArticlesForGuest() {
        return null;
    }

    @Override
    public Page<Article> findArticlesForAuthenticatedUser() {
        return null;
    }
}
