package com.newsportal.services;

import com.newsportal.models.Group;

import java.util.List;

public interface GroupService {

    List<Group> findGroupsUserBelongsToByUsername(String username);
}
