package com.newsportal.services.implementation;

import com.newsportal.models.Article;
import com.newsportal.models.Comment;
import com.newsportal.models.User;
import com.newsportal.repositories.CommentRepository;
import com.newsportal.services.ArticleService;
import com.newsportal.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleService articleService;

    @Override
    public void create(Long articleId, User user, String commentText) {
        Comment comment = new Comment();
        Article article = articleService.findById(articleId);

        comment.setArticle(article);
        comment.setUser(user);
        comment.setCreationDate(new Date(System.currentTimeMillis()));
        comment.setText(commentText);

        commentRepository.save(comment);
    }
}
