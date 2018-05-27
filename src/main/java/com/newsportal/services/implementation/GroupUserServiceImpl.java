package com.newsportal.services.implementation;

import com.newsportal.models.GroupUser;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.GroupUserRepository;
import com.newsportal.services.GroupUserService;
import com.newsportal.viewmodels.GroupUsersListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Override
    public GroupUser findById(long id) {
        return groupUserRepository.findById(id).get();
    }

    @Override
    public void save(GroupUser groupUser) {
        groupUserRepository.save(groupUser);
    }

    @Override
    public List<GroupUser> findByGroupId(long id) {
        return groupUserRepository.findByGroupId(id);
    }

    @Override
    public List<GroupUser> findByUserId(long userId) {
        return groupUserRepository.findByUserId(userId);
    }

    @Override
    public List<GroupUsersListItem> findGroupUsersWithPublishRights(List<GroupUser> groupUsers) {
        List<GroupUsersListItem> result = new ArrayList<>();

        List<GroupUser> groupUsersWithPublishRights = groupUsers.stream()
                .filter(groupUser -> groupUser.getRole() != Role.REGULAR)
                .collect(Collectors.toList());

        groupUsersWithPublishRights
                .forEach(groupUser -> {
                    result.add(new GroupUsersListItem(groupUser.getGroup().getId(), groupUser.getGroup().getTitle()));
                });

        return result;
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
