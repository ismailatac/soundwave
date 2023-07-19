package com.turkcell.soundwave.business.abstracts;

import com.turkcell.soundwave.business.dtos.favorite.*;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    List<GetAllFavoritesResponse> getAll();

    CreateFavoriteResponse add(CreateFavoriteRequest request);

    void delete(UUID id);

    UpdateFavoriteResponse update(UUID id, UpdateFavoriteRequest request);

    GetFavoriteResponse getById(UUID id);
}
