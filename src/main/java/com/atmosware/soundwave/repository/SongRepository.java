package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Song;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
