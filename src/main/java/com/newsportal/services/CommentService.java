package com.newsportal.services;

import com.newsportal.models.Comment;
import com.newsportal.models.User;

public interface CommentService {
    void create(Long articleId, User user, String commentText);

    Comment findById(Long commentId);

    void update(Comment comment);

    void delete(Comment comment);
}
