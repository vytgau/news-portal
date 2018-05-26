package com.newsportal.services.implementation;

import com.newsportal.models.*;
import com.newsportal.models.enums.ReportState;
import com.newsportal.repositories.ArticleRepository;
import com.newsportal.repositories.CommentRepository;
import com.newsportal.repositories.NotificationRepository;
import com.newsportal.repositories.ReportRepository;
import com.newsportal.services.ArticleService;
import com.newsportal.services.GroupService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final int ARTICLES_PER_PAGE = 8;
    private static final int ARTICLES_PER_SEARCH_PAGE = 8;

    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private NotificationRepository notificationRepository;
    @Autowired private ReportRepository reportRepository;
    @Autowired private GroupService groupService;

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
    public Page<Article> findArticlesGroup(int pageNumber, Group group) {
        Pageable pageable = getPageable(pageNumber);
        Page<Article> articles = articleRepository.findGroupArticles(group, pageable);
        return articles;
    }

    @Override
    public List<Comment> findArticleComments(Long articleId) {
        return commentRepository.findByArticleIdOrderByCreationDateDesc(articleId);
    }

    @Override
    public Page<Article> searchArticles(int pageNumber, String searchText) {
        Pageable pageable = getPageableSearch(pageNumber);
        return articleRepository.searchArticles(searchText, pageable);
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

        try {
            article.setPicture(articlePicture.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Group> groupSet = new HashSet<>();
        for(String groupId : groups) {
            if (groupId.equals("MAIN")) {
                article.setInMainGroup(true);
            } else {
                Group group = groupService.findById(Long.valueOf(groupId));
                groupSet.add(group);
            }
        }

        article.setGroups(groupSet);

        articleRepository.save(article);
    }

    @Override
    public void createReport(Article article, String reportText) {
        Report report = new Report();
        Notification notification = new Notification();

        report.setDate(new Date());
        report.setState(ReportState.CREATED);
        report.setArticle(article);
        report.setComment(reportText);


        notification.setDate(new Date());
        notification.setArticle(article);
        notification.setUser(article.getAuthor());
        notification.setIsread(false);
        notification.setDescription("Pranešimas apie klaidą");

        reportRepository.save(report);
        notificationRepository.save(notification);
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

    private Pageable getPageableSearch(int pageNumber) {
        return PageRequest.of(pageNumber, ARTICLES_PER_SEARCH_PAGE);
    }
}
