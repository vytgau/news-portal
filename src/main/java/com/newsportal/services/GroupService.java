package com.newsportal.services;

import com.newsportal.models.Group;
import com.newsportal.models.GroupInvitation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupService {

    Group findById(long id);

    List<Group> findGroupsUserBelongsToByUsername(String username);

    List<GroupInvitation> findNewInvitations(String username);

    void declineInvitation(int id);

    void acceptInvitation(int id);
}
