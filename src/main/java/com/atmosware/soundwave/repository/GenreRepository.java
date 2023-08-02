package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Genre;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
