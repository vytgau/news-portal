package com.newsportal.controllers;

import com.newsportal.models.User;
import com.newsportal.models.UserReadCategories;
import com.newsportal.models.enums.Gender;
import com.newsportal.services.UserReadCategoriesService;
import com.newsportal.services.NotificationService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class StatisticsController {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/statistics")
    public String statistics(User user, Model model)
    {
        List<User> allUsers = userService.findAll();
        int count = allUsers.size();
        //genders
        int maleCount = 0;
        int femaleCount = 0;
        int blocked = 0;
        int admin = 0;
        int regular = 0;
        int writer = 0;
        for (User member : allUsers) {
            if(member.getGender() != null) {
                if (member.getGender() == Gender.MALE)
                    maleCount++;
                else
                    femaleCount++;
            }
            if (member.isBanned())
                blocked++;
            switch(member.getRole()) {
                case ADMIN:
                    admin++;
                    break;
                case WRITER:
                    writer++;
                    break;
                case REGULAR:
                    regular++;
                    break;
                    default:
                        continue;
            }
        }
        model.addAttribute("total",count);
        model.addAttribute("maleCount",maleCount);
        model.addAttribute("blocked",blocked);
        model.addAttribute("femaleCount",femaleCount);
        model.addAttribute("admin",admin);
        model.addAttribute("regular",regular);
        model.addAttribute("writer",writer);
        return "statistics";
    }









}
