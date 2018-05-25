package com.newsportal.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @Column(name = "publication_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_usersid")
    private User author;

    // a set of categories that this article belongs to
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "categories_articles",
            joinColumns = {@JoinColumn(name = "fk_Articlesid")},
            inverseJoinColumns = {@JoinColumn(name = "fk_Categoriesid")}
    )
    private Set<Category> categories;

    // a set of groups that this article belongs to
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="groups_articles",
            joinColumns = {@JoinColumn(name = "fk_Articlesid")},
            inverseJoinColumns = {@JoinColumn(name = "fk_Groupsid")}
    )
    private Set<Group> groups;

    @Lob
    @Column(columnDefinition="mediumtext")
    @Basic(fetch = FetchType.LAZY)
    private String text;

    private String title;
    private Integer rating;
    private Integer views;

    @Lob
    @Column(columnDefinition="mediumblob")
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    // shows if this article belongs to main group
    @Column(name = "in_main_group")
    private boolean inMainGroup;

    public Article() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Group> getGroups() { return groups; }

    public void setGroups(Set<Group> groups) { this.groups = groups; }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean isInMainGroup() {
        return inMainGroup;
    }

    public void setInMainGroup(boolean inMainGroup) {
        this.inMainGroup = inMainGroup;
    }
}
