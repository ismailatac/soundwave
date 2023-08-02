package com.atmosware.soundwave.repository;

import com.atmosware.soundwave.entities.PopularSong;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PopularSongRepository extends CrudRepository<PopularSong,UUID> {
}
