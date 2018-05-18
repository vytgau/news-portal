package com.newsportal.services.implementation;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import com.newsportal.repositories.GroupInvitationRepository;
import com.newsportal.repositories.GroupRepository;
import com.newsportal.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupInvitationRepository groupInvitationRepository;

    @Override
    public List<Group> findGroupsUserBelongsToByUsername(String username) {
        return groupRepository.findGroupsUserBelongsToByUsername(username);
    }

    @Override
    public List<GroupInvitation> findNewInvitations(String username) {
        return groupInvitationRepository.findNewInvitations(username);
    }
}
