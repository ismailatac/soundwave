package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.User;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);


}
