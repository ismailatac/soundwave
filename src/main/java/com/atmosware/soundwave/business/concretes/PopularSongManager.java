package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.PopularSongService;
import com.atmosware.soundwave.business.dtos.popularSong.*;
import com.atmosware.soundwave.business.rules.PopularSongBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.entities.PopularSong;
import com.atmosware.soundwave.repository.PopularSongRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PopularSongManager implements PopularSongService {

    private final PopularSongRepository repository;
    private final ModelMapper mapper;
    private final PopularSongBusinessRules rules;

    @Override
    public List<GetAllPopularSongsResponse> getAll() {

        List<PopularSong> popularSongs;
        try {
            popularSongs = (List<PopularSong>) repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyPopularSongExists(popularSongs);

        return popularSongs.stream()
                .map(popularSong -> mapper.map(popularSong, GetAllPopularSongsResponse.class)).toList();
    }


    @Override
    public CreatePopularSongResponse add(CreatePopularSongRequest request) {
        PopularSong popularSongSave = mapper.map(request, PopularSong.class);
        popularSongSave.setId(null);
        PopularSong responsePopularSong;
        try {
            responsePopularSong = repository.save(popularSongSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(responsePopularSong, CreatePopularSongResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPopularSongExists(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request) {
        rules.checkIfPopularSongExists(id);
        PopularSong updatePopularSong = mapper.map(request, PopularSong.class);
        updatePopularSong.setId(id);
        PopularSong popularSongResponse;
        try {
            popularSongResponse = repository.save(updatePopularSong);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(popularSongResponse, UpdatePopularSongResponse.class);
    }

    @Override
    public GetPopularSongResponse getById(UUID id) {
        PopularSong popularSong;
        try {
            popularSong = repository.findById(id).orElseThrow();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(popularSong, GetPopularSongResponse.class);
    }
}
