package com.newsportal.services.implementation;

import com.newsportal.models.Notification;
import com.newsportal.models.User;
import com.newsportal.repositories.NotificationRepository;
import com.newsportal.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notification> findAll()
    {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findById(long id) {
        return notificationRepository.findById(id).get();
    }



}
