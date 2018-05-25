package com.newsportal.viewmodels;

import org.springframework.web.multipart.MultipartFile;

public class CreateArticle {

    private String articleTitle;
    private String publishTime;
    private String[] groups;
    private String articleText;
    private MultipartFile articlePicture;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public MultipartFile getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(MultipartFile articlePicture) {
        this.articlePicture = articlePicture;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }
}
