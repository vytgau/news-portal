package com.newsportal.services;

import com.newsportal.models.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Page<Article> findArticlesForGuest();

    Page<Article> findArticlesForAuthenticatedUser();

}
