package com.newsportal.repositories;

import com.newsportal.models.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    List<GroupUser> findByGroupId(long id);

    GroupUser findFirstByGroupIdAndUserId(long groupId, long userId);

}
