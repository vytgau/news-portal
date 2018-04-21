package com.newsportal.models;

import javax.persistence.*;

@Entity
@Table(name = "user_read_categories")
public class UserReadCategories {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_Usersid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_Categoriesid")
    private Category category;

    private Integer count;

    public UserReadCategories() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
