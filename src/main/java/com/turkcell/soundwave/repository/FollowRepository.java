package com.turkcell.soundwave.repository;

import com.turkcell.soundwave.entities.Follow;
import com.turkcell.soundwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
}
