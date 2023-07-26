package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.genre.*;
import java.util.List;
import java.util.UUID;

public interface GenreService {
    List<GetAllGenresResponse> getAll();

    CreateGenreResponse add(CreateGenreRequest request);

    void delete(UUID id);

    UpdateGenreResponse update(UUID id, UpdateGenreRequest request);

    GetGenreResponse getById(UUID id);
}
