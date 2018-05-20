package com.newsportal.services;

import com.newsportal.models.GroupUser;

import java.util.List;

public interface GroupUserService {

    List<GroupUser> findAllByGroupId(long id);

    GroupUser findFirstByGroupIdAndUserId(long groupId, long userId);

}
