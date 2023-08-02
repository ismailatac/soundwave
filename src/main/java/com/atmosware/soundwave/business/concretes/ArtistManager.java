package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.dtos.artist.*;
import com.atmosware.soundwave.business.rules.ArtistBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.core.utilities.mapstruct.ArtistMapper;
import com.atmosware.soundwave.entities.Album;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.repository.ArtistRepository;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ArtistManager implements ArtistService {

    private final ArtistRepository repository;
    private final ModelMapper mapper;
    private final ArtistMapper artistMapper;
    private final ArtistBusinessRules rules;

    @Override
    public List<GetAllArtistsResponse> getAll() {
        List<Artist> artists;
        try {
            artists = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyArtistExists(artists);
        return artists.stream()
                .map(artist -> mapper.map(artist, GetAllArtistsResponse.class)).toList();
    }

    @Override
    public CreateArtistResponse add(CreateArtistRequest request) {
        Artist artistSave = artistMapper.convertToCreateArtistRequest(request);
        artistSave.setId(null);
        Artist responseArtist;
        try {
            responseArtist = repository.save(artistSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database  +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return artistMapper.map_create(responseArtist);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfArtistExists(id);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
        rules.checkIfArtistExists(id);
        var updateArtist = artistMapper.map_update(request);
        updateArtist.setId(id);
        Artist artistResponse;
        try {
             artistResponse = repository.save(updateArtist);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

        return artistMapper.map_update(artistResponse);
    }

    @Override
    public GetArtistResponse getById(UUID id) {
        Artist artist;
        try {
             artist = repository.findById(id).orElseThrow();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database +": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        var response = artistMapper.map_getbyid(artist);
        return response;
    }

}
