package com.newsportal.controllers;

import com.newsportal.models.Group;
import com.newsportal.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/get/groups")
    @ResponseBody
    public List<Group> getGroups(Principal principal) {
        String username = principal.getName();
        return groupService.findGroupsUserBelongsToByUsername(username);
    }
}
