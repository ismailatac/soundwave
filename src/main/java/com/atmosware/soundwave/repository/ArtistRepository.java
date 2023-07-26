package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Artist;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
}
