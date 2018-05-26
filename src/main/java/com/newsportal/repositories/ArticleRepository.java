package com.newsportal.repositories;

import com.newsportal.models.Article;
import com.newsportal.models.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByInMainGroupTrueOrderByCreationDateDesc(Pageable pageable);

    @Query("SELECT article FROM Article article " +
            "WHERE :group MEMBER OF article.groups " +
            "ORDER BY article.creationDate DESC")
    Page<Article> findGroupArticles(@Param("group")Group group, Pageable pageable);

}
