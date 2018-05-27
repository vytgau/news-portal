package com.newsportal.services;

import com.newsportal.models.Article;
import com.newsportal.models.Comment;
import com.newsportal.models.Group;
import com.newsportal.models.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    Article findById(long articleId);

    Page<Article> findArticlesHomePage(int pageNumber);

    Page<Article> findArticlesGroup(int pageNumber, Group group);

    Page<Article> searchArticles(int pageNumber, String searchText);

    Page<Article> findByAuthor(int pageNumber, User author);

    void createArticle(MultipartFile articlePicture, String articleTitle, String publishTime, String[] groups, String articleText, User author);

    void save(Article article);

    void delete(Article article);

    void createReport(Article article, String reportText);

    List<Comment> findArticleComments(Long articleId);
}
