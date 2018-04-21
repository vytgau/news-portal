package com.newsportal.models;

import com.newsportal.models.enums.InvitationState;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "group_invitations")
public class GroupInvitation {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private InvitationState state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_usersid")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_groupsid")
    private Group group;

    public GroupInvitation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InvitationState getState() {
        return state;
    }

    public void setState(InvitationState state) {
        this.state = state;
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
