package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByName(String name);
}
