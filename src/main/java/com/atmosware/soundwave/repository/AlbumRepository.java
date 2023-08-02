package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.Album;
import com.atmosware.soundwave.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    List<Album> findByArtist(Artist artist);
}
