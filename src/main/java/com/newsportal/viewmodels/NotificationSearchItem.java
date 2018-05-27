package com.newsportal.viewmodels;

import com.newsportal.models.Notification;
import com.newsportal.viewmodels.enums.UsersSearchItemState;

public class NotificationSearchItem {

    private Notification notification;
    private UsersSearchItemState state;

    public Notification getUser() {
        return notification;
    }

    public void setNotification(Notification user) {
        this.notification = notification;
    }

}