package com.newsportal.services.implementation;

import com.newsportal.models.*;
import com.newsportal.models.enums.InvitationState;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.GroupInvitationRepository;
import com.newsportal.repositories.GroupRepository;
import com.newsportal.repositories.GroupUserRepository;
import com.newsportal.services.GroupService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupInvitationRepository groupInvitationRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private UserService userService;

    @Override
    public Group findById(long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public List<Group> findGroupsUserBelongsToByUsername(String username) {
        return groupRepository.findGroupsUserBelongsToByUsername(username);
    }

    @Override
    public List<GroupInvitation> findNewInvitations(String username) {
        return groupInvitationRepository.findNewInvitations(username);
    }

    @Override
    public void declineInvitation(int id) {
        GroupInvitation invitation = groupInvitationRepository.findById((long)id).get();
        invitation.setState(InvitationState.DECLINED);
        groupInvitationRepository.save(invitation);
    }

    @Override
    public void acceptInvitation(int id) {
        // change invitation state to accepted
        GroupInvitation invitation = groupInvitationRepository.findById((long)id).get();
        invitation.setState(InvitationState.ACCEPTED);
        groupInvitationRepository.save(invitation);

        // create new group user
        GroupUser groupUser = new GroupUser();
        groupUser.setDateJoined(new Date());
        groupUser.setRole(Role.REGULAR);
        groupUser.setUser(invitation.getUser());
        groupUser.setGroup(invitation.getGroup());
        groupUserRepository.save(groupUser);
    }

    @Override
    public void createInvitation(long userId, long groupId) {
        GroupInvitation invitation = new GroupInvitation();

        invitation.setUser(userService.findById(userId));
        invitation.setGroup(groupRepository.findById(groupId).get());
        invitation.setDate(new Date());
        invitation.setState(InvitationState.NEW);

        groupInvitationRepository.save(invitation);
    }

    @Override
    public void removeGroupUser(long groupUserId) {
        groupUserRepository.deleteById(groupUserId);
    }

    @Override
    public void createGroup(String groupTitle, String groupDescription, User admin) {
        Group group = new Group();
        group.setTitle(groupTitle);
        group.setDescription(groupDescription);
        group.setCreationDate(new Date());

        groupRepository.save(group);

        GroupUser groupAdmin = new GroupUser();
        groupAdmin.setRole(Role.ADMIN);
        groupAdmin.setDateJoined(new Date());
        groupAdmin.setUser(admin);
        groupAdmin.setGroup(group);

        groupUserRepository.save(groupAdmin);
    }

    @Override
    public void deleteGroup(int id) {
        groupInvitationRepository.deleteAll(groupInvitationRepository.findByGroupId((long) id));
        groupUserRepository.deleteAll(groupUserRepository.findByGroupId((long) id));
        groupRepository.deleteById((long) id);

    }

}
