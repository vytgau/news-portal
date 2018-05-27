package com.newsportal.services;

import com.newsportal.models.User;

public interface CommentService {
    void create(Long articleId, User user, String commentText);
}
