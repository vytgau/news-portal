package com.newsportal.controllers;

import com.newsportal.models.Notification;
import com.newsportal.models.User;
import com.newsportal.models.enums.Gender;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.UserRepository;
import com.newsportal.services.NotificationService;
import com.newsportal.services.UserService;
import com.newsportal.viewmodels.UsersSearchItem;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

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

    @GetMapping(value = "/manage")
    public String manageUsers(User user, Model model, int changes)
    {
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers",allUsers);
        model.addAttribute("deleted", changes);
        return "manage";
    }

    @RequestMapping(value = "/edituser", method=RequestMethod.GET)
    public String editUser(User user, Model model, int id)
    {
        model.addAttribute("id",id);
        User userToEdit = userService.findById(id);
        model.addAttribute("user",userToEdit);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUserPost(User user, Model model, User userFromPost, int id)
    {
        model.addAttribute("users",userService.findById(id));
        userFromPost.setPassword(userService.findById(id).getPassword());
        userService.save(userFromPost);
        //got user now to validate
        model.addAttribute("changes",3);


        return "redirect:/manage?changes=3";
    }

    @RequestMapping(value = "/deleteuser", method=RequestMethod.GET)
    public String deleteUser(User user, Model model, int id)
    {
        userService.deleteById(id);
        return "redirect:/manage?changes=1";
    }

    @RequestMapping(value = "/blockuser", method=RequestMethod.GET)
    public String blockUser(User user, Model model, int id)
    {
        User userToBlock = userService.findById(id);
        userToBlock.changeBan();
        userService.save(userToBlock);
        if (userToBlock.isBanned())
        return "redirect:/manage?changes=2";
        else
            return "redirect:/manage?changes=4";
    }

    @RequestMapping(value = "/notifications", method=RequestMethod.GET)
    public String viewNotifications(User user, Model model)
    {
        List<Notification> allNotifications = notificationService.findAll();
        model.addAttribute("allNotifications",allNotifications);
        //userService.save(userToBlock);
        return "notifications";
    }

    @RequestMapping(value = "/notification", method=RequestMethod.GET)
    public String viewNotification(User user, Model model, int id)
    {
        Notification notification = notificationService.findById(id);
        notification.setIsread(true);
        model.addAttribute("notification",notification);
        //userService.save(userToBlock);
        return "notification";
    }




}
