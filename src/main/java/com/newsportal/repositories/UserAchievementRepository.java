package com.newsportal.repositories;

import com.newsportal.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
}
