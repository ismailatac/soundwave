package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Album;

import java.util.List;
import java.util.UUID;

import com.atmosware.soundwave.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    List<Album> findByArtist(Artist artist);
}
