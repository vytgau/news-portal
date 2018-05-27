package com.newsportal.services;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import com.newsportal.models.User;

import java.util.List;

public interface GroupService {

    Group findById(long id);

    List<Group> findGroupsUserBelongsToByUsername(String username);

    List<GroupInvitation> findNewInvitations(String username);

    void declineInvitation(int id);

    void acceptInvitation(int id);

    void createInvitation(long userId, long groupId);

    void removeGroupUser(long groupUserId);

    void createGroup(String groupTitle, String groupDescription, User author);

}
