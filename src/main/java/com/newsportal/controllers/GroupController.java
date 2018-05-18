package com.newsportal.controllers;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
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

    /**
     * Restful endpoint for getting a list of groups that the authenticated user belongs to
     */
    @GetMapping("/get/groups")
    @ResponseBody
    public List<Group> getGroups(Principal principal) {
        String username = principal.getName();
        return groupService.findGroupsUserBelongsToByUsername(username);
    }

    /**
     * Restful endpoint for getting a list of new group invitations that belongs to
     * the currently logged in user
     */
    @GetMapping("/get/new_invitations")
    @ResponseBody
    public List<GroupInvitation> getNewInvitations(Principal principal) {
        String username = principal.getName();
        return groupService.findNewInvitations(username);
    }
}
