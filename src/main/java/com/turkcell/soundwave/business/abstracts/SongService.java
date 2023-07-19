package com.turkcell.soundwave.business.abstracts;

import com.turkcell.soundwave.business.dtos.song.*;

import java.util.List;
import java.util.UUID;

public interface SongService {
    List<GetAllSongsResponse> getAll();

    CreateSongResponse add(CreateSongRequest request);

    void delete(UUID id);

    UpdateSongResponse update(UUID id, UpdateSongRequest request);

    GetSongResponse getById(UUID id);
}
