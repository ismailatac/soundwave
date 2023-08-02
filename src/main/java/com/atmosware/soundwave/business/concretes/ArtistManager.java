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
        var artists = repository.findAll();
        rules.checkIfAnyArtistExists(artists);
        log.info("Artist service getAll method called.");
        return artists.stream()
                .map(artist -> mapper.map(artist, GetAllArtistsResponse.class)).toList();
    }

    @Override
    public CreateArtistResponse add(CreateArtistRequest request) {
        Artist artistSave = artistMapper.convertCreateArtistRequestToArtist(request);
        artistSave.setId(null);
        var responseArtist = repository.save(artistSave);
        log.info("{} artist added.", artistSave.getName());
        return artistMapper.convertArtistToCreateArtistResponse(responseArtist);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfArtistExists(id);
        repository.deleteById(id);
        log.info("{} artist deleted.", this.getById(id).getName());
    }

    @Override
    public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
        rules.checkIfArtistExists(id);
        var updateArtist = artistMapper.convertUpdateArtistRequestToArtist(request);
        updateArtist.setId(id);
        var artistResponse = repository.save(updateArtist);
        log.info("{} artist updated.", updateArtist.getName());
        return artistMapper.convertArtistToUpdateArtistResponse(artistResponse);
    }

    @Override
    public GetArtistResponse getById(UUID id) {
        var artist = repository.findById(id).orElseThrow();
        var response = artistMapper.convertArtistToGetArtistResponse(artist);
        log.info("Artist service: {} getById method called.", this.getById(id).getName());
        return response;
    }

}
