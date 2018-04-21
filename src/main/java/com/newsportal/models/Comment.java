package com.newsportal.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @NotEmpty
    private String text;

    @ManyToOne
    @JoinColumn(name = "fk_Usersid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_Articlesid")
    private Article article;

    public Comment() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
