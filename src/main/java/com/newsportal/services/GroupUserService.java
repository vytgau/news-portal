package com.newsportal.services;

import com.newsportal.models.GroupUser;
import com.newsportal.viewmodels.GroupUsersListItem;

import java.util.List;

public interface GroupUserService {

    List<GroupUser> findByGroupId(long id);

    List<GroupUser> findByUserId(long userId);

    GroupUser findFirstByGroupIdAndUserId(long groupId, long userId);

    List<GroupUser> search(String groupId, String searchTerm);

    List<GroupUsersListItem> findGroupUsersWithPublishRights(List<GroupUser> groupUsers);

}
