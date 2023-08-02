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
        var popularSongs = (List<PopularSong>) repository.findAll();
        rules.checkIfAnyPopularSongExists(popularSongs);
        log.info("PopularSong service getAll method called.");
        return popularSongs.stream()
                .map(popularSong -> mapper.map(popularSong, GetAllPopularSongsResponse.class)).toList();
    }


    @Override
    public CreatePopularSongResponse add(CreatePopularSongRequest request) {
        PopularSong popularSongSave = mapper.map(request, PopularSong.class);
        popularSongSave.setId(null);
        var responsePopularSong = repository.save(popularSongSave);
        log.info("{} popularSong added.", popularSongSave.getSongId());
        return mapper.map(responsePopularSong, CreatePopularSongResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPopularSongExists(id);
        repository.deleteById(id);
        log.info("{} popularSong deleted.", this.getById(id).getSongId());
    }

    @Override
    public UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request) {
        rules.checkIfPopularSongExists(id);
        PopularSong updatePopularSong = mapper.map(request, PopularSong.class);
        updatePopularSong.setId(id);
        var popularSongResponse = repository.save(updatePopularSong);
        log.info("{} popularSong updated.", updatePopularSong.getSongId());
        return mapper.map(popularSongResponse, UpdatePopularSongResponse.class);
    }

    @Override
    public GetPopularSongResponse getById(UUID id) {
        var popularSong = repository.findById(id).orElseThrow();
        log.info("PopularSong service: {} getById method called.", this.getById(id).getSongId());
        return mapper.map(popularSong, GetPopularSongResponse.class);
    }
}
