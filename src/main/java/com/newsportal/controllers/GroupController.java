package com.newsportal.controllers;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import com.newsportal.models.GroupUser;
import com.newsportal.models.User;
import com.newsportal.models.enums.Role;
import com.newsportal.services.GroupService;
import com.newsportal.services.GroupUserService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GroupController {

    @Autowired private GroupService groupService;
    @Autowired private UserService userService;
    @Autowired private GroupUserService groupUserService;

    /**
     * Opens group member information page
     */
    @GetMapping("/group/member")
    public String groupMember(@RequestParam String groupId, @RequestParam String groupMemberId, Model model, Principal principal) {

        String username = principal.getName();

        Group group = groupService.findById(Long.valueOf(groupId));
        User user = userService.findByUsername(username);
        GroupUser groupUser = groupUserService.findFirstByGroupIdAndUserId(group.getId(), user.getId());
        List<GroupUser> groupMembers = groupUserService.findByGroupId(Long.valueOf(groupId));


        model.addAttribute("group", group);
        model.addAttribute("groupUser", groupUser);
        model.addAttribute("groupMembers", groupMembers);

        return "group-member";
    }

    /**
     * Opens group members management page
     */
    @GetMapping("group/members")
    public String groupMembersManagement(@RequestParam String groupid, Model model, Principal principal) {

        String username = principal.getName();

        Group group = groupService.findById(Long.valueOf(groupid));
        User user = userService.findByUsername(username);
        GroupUser groupUser = groupUserService.findFirstByGroupIdAndUserId(group.getId(), user.getId());
        List<GroupUser> groupMembers = groupUserService.findByGroupId(Long.valueOf(groupid));


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
        GroupUser groupUser = groupUserService.findFirstByGroupIdAndUserId(group.getId(), user.getId());

        model.addAttribute("group", group);
        model.addAttribute("groupUser", groupUser);

        return "group";
    }


    /*********************************************************
     * =====================    REST API =====================
     *********************************************************/

    @GetMapping("/search/group/members")
    @ResponseBody
    public List<GroupUser> searchGroupMembers(@RequestParam String groupId, @RequestParam String searchTerm) {
        List<GroupUser> temp = groupUserService.search(groupId, searchTerm);
        return groupUserService.search(groupId, searchTerm);
    }

    /**
     * Get group titles and id's that the user who is currently logged in has publish rights to
     */
    @GetMapping("/get/groups/publish_rights")
    @ResponseBody
    public List<GroupUser> getGroupsUserCanPublishArticlesTo(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<GroupUser> groupUserRecords = groupUserService.findByUserId(user.getId());
        return groupUserService.findGroupUsersWithPublishRights(groupUserRecords);
    }

    /**
     * Check if currently logged in user has publish rights to the main group
     */
    @GetMapping("/publish_to_main_group")
    @ResponseBody
    public boolean canPublishToMainGroup(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user.getRole() != Role.REGULAR ? true : false;
    }

    /**
     * Finds a list of groups that the authenticated user belongs to
     */
    @GetMapping("/get/groups")
    @ResponseBody
    public List<Group> getGroups(Principal principal) {
        String username = principal.getName();
        return groupService.findGroupsUserBelongsToByUsername(username);
    }


    @GetMapping("/current_group-user")
    @ResponseBody
    public GroupUser getCurrentGroupUser(@RequestParam String groupId, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Group group = groupService.findById(Long.valueOf(groupId));

        return groupUserService.findFirstByGroupIdAndUserId(group.getId(), user.getId());
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

    @RequestMapping(value = "/create_invitation", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createInvitation(@RequestParam("userId") String userId, @RequestParam("groupId") String groupId) {
        groupService.createInvitation(Long.valueOf(userId), Long.valueOf(groupId));
    }

    @RequestMapping(value = "/remove_group_user", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeGroupUser(@RequestParam("groupUserId") String groupUserId) {
        groupService.removeGroupUser(Long.valueOf(groupUserId));
    }
}
