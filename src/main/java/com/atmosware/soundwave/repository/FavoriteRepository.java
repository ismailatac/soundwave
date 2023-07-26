package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Favorite;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
}
