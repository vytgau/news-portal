package com.newsportal.repositories;

import com.newsportal.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleIdOrderByCreationDateDesc(Long articleId);

}
