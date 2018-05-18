package com.newsportal.repositories;

import com.newsportal.models.GroupInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupInvitationRepository extends JpaRepository<GroupInvitation, Long> {

    @Query("SELECT groupInvitation FROM GroupInvitation groupInvitation " +
           "WHERE groupInvitation.user.id IN (SELECT user.id FROM User user " +
           "WHERE user.username = :username) " +
           "AND groupInvitation.state = 'NEW'")
    List<GroupInvitation> findNewInvitations(@Param("username") String username);

}
