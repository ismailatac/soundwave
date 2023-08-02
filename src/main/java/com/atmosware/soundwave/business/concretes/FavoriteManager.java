package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.FavoriteService;
import com.atmosware.soundwave.business.abstracts.SongService;
import com.atmosware.soundwave.business.abstracts.UserService;
import com.atmosware.soundwave.business.dtos.favorite.*;
import com.atmosware.soundwave.business.rules.FavoriteBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.entities.Favorite;
import com.atmosware.soundwave.entities.Song;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class FavoriteManager implements FavoriteService {

    private final FavoriteRepository repository;
    private final ModelMapper mapper;
    private final FavoriteBusinessRules rules;
    private final SongService songService;
    private final UserService userService;

    @Override
    public List<GetAllFavoritesResponse> getAll() {
        List<Favorite> favorites;
        try {
            favorites = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyFavoriteExists(favorites);
        return favorites.stream()
                .map(favorite -> mapper.map(favorite, GetAllFavoritesResponse.class)).toList();
    }

    @Override
    public CreateFavoriteResponse add(CreateFavoriteRequest request) {
        Favorite favoriteSave = new Favorite();
        favoriteSave.setId(null);
        favoriteSave.setSong(mapper.map(songService.getById(request.getSongId()), Song.class));
        favoriteSave.setUser(mapper.map(userService.getById(request.getUserId()), User.class));
        favoriteSave.setId(null);
        Favorite responseFavorite;
        try {
             responseFavorite = repository.save(favoriteSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database  +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(responseFavorite, CreateFavoriteResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfFavoriteExists(id);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public UpdateFavoriteResponse update(UUID id, UpdateFavoriteRequest request) {
        rules.checkIfFavoriteExists(id);
        Favorite updateFavorite = mapper.map(request, Favorite.class);
        updateFavorite.setId(id);
        Favorite favoriteResponse;
        try {
             favoriteResponse = repository.save(updateFavorite);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(favoriteResponse, UpdateFavoriteResponse.class);
    }

    @Override
    public GetFavoriteResponse getById(UUID id) {
        Favorite favorite;
        try {
             favorite = repository.findById(id).orElseThrow();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(favorite, GetFavoriteResponse.class);
    }

}
