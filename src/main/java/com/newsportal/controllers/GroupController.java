package com.newsportal.controllers;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import com.newsportal.models.GroupUser;
import com.newsportal.models.User;
import com.newsportal.repositories.GroupUserRepository;
import com.newsportal.repositories.UserRepository;
import com.newsportal.services.GroupService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @GetMapping("group/members")
    public String groupMembers(@RequestParam String groupid, Model model, Principal principal) {

        String username = principal.getName();

        Group group = groupService.findById(Long.valueOf(groupid));
        User user = userService.findByUsername(username);
        GroupUser groupUser = groupUserRepository.findFirstByGroupIdAndUserId(group.getId(), user.getId());
        List<GroupUser> groupMembers = groupUserRepository.findByGroupId(Long.valueOf(groupid));


        model.addAttribute("group", group);
        model.addAttribute("groupUser", groupUser);
        model.addAttribute("groupMembers", groupMembers);

        return "group-members";
    }

    /**
     * Opens group main page
     * @param id group id
     */
    @GetMapping("/group")
    public String group(@RequestParam String id, Model model, Principal principal) {
        String username = principal.getName();

        Group group = groupService.findById(Long.valueOf(id));
        User user = userService.findByUsername(username);
        GroupUser groupUser = groupUserRepository.findFirstByGroupIdAndUserId(group.getId(), user.getId());

        model.addAttribute("group", group);
        model.addAttribute("groupUser", groupUser);

        return "group";
    }

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

    @PostMapping("/accept_invitation")
    @ResponseStatus(value = HttpStatus.OK)
    public void acceptInvitation(@RequestParam("id") String id) {
        groupService.acceptInvitation(Integer.valueOf(id));
    }

    @RequestMapping(value = "/decline_invitation", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void declineInvitation(@RequestParam("id") String id) {
        groupService.declineInvitation(Integer.valueOf(id));
    }
}
