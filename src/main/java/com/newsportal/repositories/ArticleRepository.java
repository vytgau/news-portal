package com.newsportal.repositories;

import com.newsportal.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByInMainGroupTrueOrderByCreationDateDesc(Pageable pageable);

}
