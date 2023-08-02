package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Song;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SongRepository extends JpaRepository<Song, UUID> {
}
