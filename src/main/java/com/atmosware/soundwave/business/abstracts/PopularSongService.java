package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.genre.*;
import com.atmosware.soundwave.business.dtos.popularSong.*;

import java.util.List;
import java.util.UUID;

public interface PopularSongService {
    List<GetAllPopularSongsResponse> getAll() throws InterruptedException;

    CreatePopularSongResponse add(CreatePopularSongRequest request);

    void delete(UUID id);

    UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request);

    GetPopularSongResponse getById(UUID id);
}
