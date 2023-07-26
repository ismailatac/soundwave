package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Genre;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
