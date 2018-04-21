package com.newsportal.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    private String title;
    private String description;

    // a set of articles that this group has
    @ManyToMany(mappedBy = "groups")
    private Set<Article> articles;

    public Group() {}

    public Group(String title, String description, Date creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Article> getArticles() { return articles; }

    public void setArticles(Set<Article> articles) { this.articles = articles; }
}
