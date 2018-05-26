package com.newsportal.services.implementation;

import com.newsportal.models.Article;
import com.newsportal.models.User;
import com.newsportal.repositories.ArticleRepository;
import com.newsportal.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final int ARTICLES_PER_PAGE = 8;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article findById(long articleId) {
        return articleRepository.findById(articleId).get();
    }

    @Override
    public Page<Article> findArticlesHomePage(int pageNumber) {
        Pageable pageable = getPageable(pageNumber);
        return articleRepository.findByInMainGroupTrueOrderByCreationDateDesc(pageable);
    }

    @Override
    public void createArticle(MultipartFile articlePicture,
                              String articleTitle,
                              String publishTime,
                              String[] groups,
                              String articleText,
                              User author) {
        Article article = new Article();

        article.setText(articleText);
        article.setTitle(articleTitle);
        article.setCreationDate(new Date());
        article.setRating(0);
        article.setViews(0);
        article.setPublicationTime(parsePublishTimeString(publishTime));
        article.setAuthor(author);
        article.setInMainGroup(true);

        try {
            article.setPicture(articlePicture.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        articleRepository.save(article);
    }

    /**
     * publishTime String format example: '05/25/2018 5:37 PM'
     */
    private Date parsePublishTimeString(String publishTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date result = null;
        try {
            result = formatter.parse(publishTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Pageable getPageable(int pageNumber) {
        return PageRequest.of(pageNumber, ARTICLES_PER_PAGE);
    }
}
