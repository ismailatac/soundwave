package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.FavoriteService;
import com.turkcell.soundwave.business.dtos.favorite.*;
import com.turkcell.soundwave.entities.Favorite;
import com.turkcell.soundwave.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FavoriteManager implements FavoriteService {

    private final FavoriteRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllFavoritesResponse> getAll() {

        var favorites = repository.findAll();
        return favorites.stream()
                .map(favorite -> mapper.map(favorite, GetAllFavoritesResponse.class)).toList();
    }

    @Override
    public CreateFavoriteResponse add(CreateFavoriteRequest request) {
        Favorite favoriteSave = mapper.map(request, Favorite.class);
        favoriteSave.setId(null);
        Favorite responseFavorite = repository.save(favoriteSave);
        return mapper.map(responseFavorite, CreateFavoriteResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfFavoriteExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateFavoriteResponse update(UUID id, UpdateFavoriteRequest request) {
//        rules.checkIfFavoriteExists(id);
        Favorite updateFavorite = mapper.map(request, Favorite.class);
        updateFavorite.setId(id);
        Favorite favoriteResponse = repository.save(updateFavorite);
        return mapper.map(favoriteResponse, UpdateFavoriteResponse.class);
    }

    @Override
    public GetFavoriteResponse getById(UUID id) {
        Favorite favorite = repository.findById(id).orElseThrow();
        GetFavoriteResponse response = mapper.map(favorite, GetFavoriteResponse.class);
        return response;
    }

}
