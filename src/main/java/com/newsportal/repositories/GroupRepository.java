package com.newsportal.repositories;

import com.newsportal.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    /**
     * Finds groups that the user with the specified username belongs to
     */
    @Query("SELECT group FROM Group group WHERE group.id IN ("     +
           "SELECT groupUser.group.id FROM GroupUser groupUser "   +
           "INNER JOIN User user ON groupUser.user.id = user.id "  +
           "AND user.username = :username)")
    List<Group> findGroupsUserBelongsToByUsername(@Param("username") String username);
}