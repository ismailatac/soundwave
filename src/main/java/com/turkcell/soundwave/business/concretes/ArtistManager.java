package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.ArtistService;
import com.turkcell.soundwave.business.dtos.artist.*;
import com.turkcell.soundwave.entities.Artist;
import com.turkcell.soundwave.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistManager implements ArtistService {

    private final ArtistRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllArtistsResponse> getAll() {

        var artists = repository.findAll();
        return artists.stream()
                .map(artist -> mapper.map(artist, GetAllArtistsResponse.class)).toList();
    }

    @Override
    public CreateArtistResponse add(CreateArtistRequest request) {
        Artist artistSave = mapper.map(request, Artist.class);
        artistSave.setId(null);
        Artist responseArtist = repository.save(artistSave);
        return mapper.map(responseArtist, CreateArtistResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfArtistExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
//        rules.checkIfArtistExists(id);
        Artist updateArtist = mapper.map(request, Artist.class);
        updateArtist.setId(id);
        Artist artistResponse = repository.save(updateArtist);
        return mapper.map(artistResponse, UpdateArtistResponse.class);
    }

    @Override
    public GetArtistResponse getById(UUID id) {
        Artist artist = repository.findById(id).orElseThrow();
        GetArtistResponse response = mapper.map(artist, GetArtistResponse.class);
        return response;
    }

}
