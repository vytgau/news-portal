package com.newsportal.services.implementation;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import com.newsportal.models.GroupUser;
import com.newsportal.models.enums.InvitationState;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.GroupInvitationRepository;
import com.newsportal.repositories.GroupRepository;
import com.newsportal.repositories.GroupUserRepository;
import com.newsportal.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupInvitationRepository groupInvitationRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

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
        // change invotation state to accepted
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
}
