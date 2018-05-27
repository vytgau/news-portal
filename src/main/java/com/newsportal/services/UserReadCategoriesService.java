package com.newsportal.services;

import com.newsportal.models.Notification;
import com.newsportal.models.UserReadCategories;
import com.newsportal.viewmodels.NotificationSearchItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;import javax.jws.soap.SOAPBinding;
import java.util.List;


public interface UserReadCategoriesService {
    List<UserReadCategories> findAll();
}
