package com.newsportal.models;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "fk_Articlesid")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "fk_Usersid")
    private User user;

    private boolean isread;
    private String description;

    public Notification() {}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String dateToString() {
        if (date != null) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(date);
        }
        else
            return "Nenustatyta";
    }
}
