package com.newsportal.controllers;

import com.newsportal.models.User;
import com.newsportal.services.UserService;
import com.newsportal.viewmodels.UsersSearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String openUserView(@RequestParam String userId) {
        return "user";
    }

    @GetMapping("/users/current")
    @ResponseBody
    public User getCurrentUserREST(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @GetMapping("/search/users")
    @ResponseBody
    public List<UsersSearchItem> searchUsers(@RequestParam String searchTerm, @RequestParam String groupId, Principal principal) {
        List<UsersSearchItem> searchResult = userService.searchUsers(searchTerm, Long.valueOf(groupId), principal.getName());
        return searchResult;
    }

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
