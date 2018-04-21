package com.newsportal.services.implementation;

import com.newsportal.models.Group;
import com.newsportal.repositories.GroupRepository;
import com.newsportal.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findGroupsUserBelongsToByUsername(String username) {
        return groupRepository.findGroupsUserBelongsToByUsername(username);
    }
}
