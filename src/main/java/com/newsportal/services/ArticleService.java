package com.newsportal.services;

import com.newsportal.models.Article;
import com.newsportal.models.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {

    Article findById(long articleId);

    Page<Article> findArticlesHomePage(int pageNumber);

    void createArticle(MultipartFile articlePicture, String articleTitle, String publishTime, String[] groups, String articleText, User author);

}
