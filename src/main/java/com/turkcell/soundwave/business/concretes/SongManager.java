package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.SongService;
import com.turkcell.soundwave.business.dtos.song.*;
import com.turkcell.soundwave.entities.Song;
import com.turkcell.soundwave.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SongManager implements SongService {

    private final SongRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllSongsResponse> getAll() {

        var songs = repository.findAll();
        return songs.stream()
                .map(song -> mapper.map(song, GetAllSongsResponse.class)).toList();
    }

    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        Song songSave = mapper.map(request, Song.class);
        songSave.setId(null);
        Song responseSong = repository.save(songSave);
        return mapper.map(responseSong, CreateSongResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfSongExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateSongResponse update(UUID id, UpdateSongRequest request) {
//        rules.checkIfSongExists(id);
        Song updateSong = mapper.map(request, Song.class);
        updateSong.setId(id);
        Song songResponse = repository.save(updateSong);
        return mapper.map(songResponse, UpdateSongResponse.class);
    }

    @Override
    public GetSongResponse getById(UUID id) {
        Song song = repository.findById(id).orElseThrow();
        GetSongResponse response = mapper.map(song, GetSongResponse.class);
        return response;
    }

}
