package com.newsportal.viewmodels;

import com.newsportal.models.User;
import com.newsportal.viewmodels.enums.UsersSearchItemState;

public class UsersSearchItem {

    private User user;
    private UsersSearchItemState state;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UsersSearchItemState getState() {
        return state;
    }

    public void setState(UsersSearchItemState state) {
        this.state = state;
    }
}

