package com.newsportal.repositories;

import com.newsportal.models.GroupInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInvitationRepository extends JpaRepository<GroupInvitation, Long> {
}
