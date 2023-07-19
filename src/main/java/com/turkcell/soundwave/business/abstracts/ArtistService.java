package com.turkcell.soundwave.business.abstracts;

import com.turkcell.soundwave.business.dtos.album.*;
import com.turkcell.soundwave.business.dtos.artist.*;

import java.util.List;
import java.util.UUID;

public interface ArtistService {
    List<GetAllArtistsResponse> getAll();

    CreateArtistResponse add(CreateArtistRequest request);

    void delete(UUID id);

    UpdateArtistResponse update(UUID id, UpdateArtistRequest request);

    GetArtistResponse getById(UUID id);
}
