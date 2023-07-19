package com.turkcell.soundwave.repository;

import com.turkcell.soundwave.entities.Album;
import com.turkcell.soundwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
