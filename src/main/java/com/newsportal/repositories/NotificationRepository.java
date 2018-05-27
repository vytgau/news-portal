package com.newsportal.repositories;

import com.newsportal.models.Category;
import com.newsportal.models.Notification;
import com.newsportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAll();
}
