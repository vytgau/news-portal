package com.newsportal.models;

import com.newsportal.models.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "group_users")
public class GroupUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_joined")
    private Date dateJoined;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_usersid")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_groupsid")
    private Group group;

    public GroupUser() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
