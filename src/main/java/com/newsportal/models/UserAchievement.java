package com.newsportal.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_achievements")
public class UserAchievement {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Usersid")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Achievementsid")
    private Achievement achievement;

    public UserAchievement() {}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }
}
