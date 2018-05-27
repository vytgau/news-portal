package com.newsportal.services;

import com.newsportal.models.Notification;
import com.newsportal.viewmodels.NotificationSearchItem;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface NotificationService {

    List<Notification> findAll();

    Notification findById(long id);
}
