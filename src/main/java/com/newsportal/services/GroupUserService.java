package com.newsportal.services;

import com.newsportal.models.GroupUser;

import java.util.List;

public interface GroupUserService {

    List<GroupUser> findByGroupId(long id);

    List<GroupUser> findAllByGroupId(long id);

    GroupUser findFirstByGroupIdAndUserId(long groupId, long userId);

    List<GroupUser> search(String groupId, String searchTerm);

}
