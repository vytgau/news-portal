package com.newsportal.controllers;

import com.newsportal.models.User;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/entry")
    public String login(User user, Model model) {
        model.addAttribute(user);
        return "entry";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute @Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            //passing attribute 'registrationError' so that registration form would be opened upon entering '/entry' page
            model.addAttribute("registrationError", "registration error");
            return "entry";
        }

        userService.registerNewUserAccount(user);
        return "entry";
    }
}
