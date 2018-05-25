package com.newsportal.repositories;

import com.newsportal.models.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    List<GroupUser> findByGroupId(long id);

    List<GroupUser> findByUserId(long userId);

    GroupUser findFirstByGroupIdAndUserId(long groupId, long userId);

    @Query("SELECT groupUser FROM GroupUser groupUser " +
           "WHERE groupUser.group.id = :groupId " +
           "AND groupUser.user.username  LIKE %:searchTerm%")
    List<GroupUser> search(@Param("groupId")Long groupId, @Param("searchTerm")String searchTerm);

}
