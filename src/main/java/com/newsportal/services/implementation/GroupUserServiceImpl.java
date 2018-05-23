package com.newsportal.services.implementation;

import com.newsportal.models.GroupUser;
import com.newsportal.repositories.GroupUserRepository;
import com.newsportal.services.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Override
    public List<GroupUser> findByGroupId(long id) {
        return groupUserRepository.findByGroupId(id);
    }

    @Override
    public List<GroupUser> findAllByGroupId(long id) {
        return groupUserRepository.findByGroupId(id);
    }

    @Override
    public GroupUser findFirstByGroupIdAndUserId(long groupId, long userId) {
        return groupUserRepository.findFirstByGroupIdAndUserId(groupId, userId);
    }

    @Override
    public List<GroupUser> search(String groupId, String searchTerm) {
        return groupUserRepository.search(Long.valueOf(groupId), searchTerm);
    }
}
