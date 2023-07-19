package com.turkcell.soundwave.repository;

import com.turkcell.soundwave.entities.Song;
import com.turkcell.soundwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
