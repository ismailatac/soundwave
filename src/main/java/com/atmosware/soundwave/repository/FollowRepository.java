package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Follow;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
}
