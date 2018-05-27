package com.newsportal.controllers;

import com.newsportal.models.Group;
import com.newsportal.models.GroupUser;
import com.newsportal.models.User;
import com.newsportal.models.enums.Role;
import com.newsportal.services.GroupService;
import com.newsportal.services.GroupUserService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class GroupUsersController {

    @Autowired private GroupService groupService;
    @Autowired private UserService userService;
    @Autowired private GroupUserService groupUserService;

    /**
     * Opens group member information page
     */
    @GetMapping("/group/member")
    public String groupMember(@RequestParam String groupId,
                              @RequestParam String groupMemberId,
                              Model model,
                              Principal principal) {

        String username = principal.getName();
        User user = userService.findByUsername(username);
        GroupUser groupUser = groupUserService.findFirstByGroupIdAndUserId(Long.valueOf(groupId), user.getId());

        Group group = groupService.findById(Long.valueOf(groupId));
        GroupUser groupUserLookup = groupUserService.findById(Long.valueOf(groupMemberId));

        model.addAttribute("group", group);
        model.addAttribute("groupUser", groupUser);
        model.addAttribute("groupUserLookup", groupUserLookup);

        return "group-member";
    }

    @PostMapping("/update/group_member")
    public RedirectView updateGroupMember(@RequestParam String role,
                                          @RequestParam String groupId,
                                          @RequestParam String groupUserId,
                                          RedirectAttributes attributes) {

        GroupUser groupUser = groupUserService.findById(Long.valueOf(groupUserId));

        switch (role) {
            case "REGULAR": {
                groupUser.setRole(Role.REGULAR);
                break;
            }
            case "WRITER":{
                groupUser.setRole(Role.WRITER);
                break;
            }
            case "ADMIN":{
                groupUser.setRole(Role.ADMIN);
                break;
            }
        }

        groupUserService.save(groupUser);

        attributes.addAttribute("groupid", groupId);
        return new RedirectView("/group/members");
    }

}
