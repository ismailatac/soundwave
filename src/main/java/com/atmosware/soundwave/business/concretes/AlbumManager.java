package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AlbumService;
import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.dtos.album.*;
import com.atmosware.soundwave.business.rules.AlbumBusinessRules;
import com.atmosware.soundwave.entities.Album;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumManager implements AlbumService {

    private final AlbumRepository repository;
    private final ModelMapper mapper;
    private final ArtistService artistService;
    private final AlbumBusinessRules rules;

    @Override
    public List<GetAllAlbumsResponse> getAll() {
        var albums = repository.findAll();
        rules.checkIfAnyAlbumExists(albums);
        log.info("Album service getAll method called.");
        return albums.stream().map(album -> mapper.map(album, GetAllAlbumsResponse.class)).toList();
    }

    @Override
    public CreateAlbumResponse add(CreateAlbumRequest request) {
        var artistResponse = artistService.getById(request.getArtistId());
        var artist = mapper.map(artistResponse, Artist.class);
        Album albumSave = mapper.map(request, Album.class);
        rules.checkIfAlbumSameNameExists(artist, request.getName());
        albumSave.setId(null);
        albumSave.setArtist(artist);
        albumSave.setReleaseDate(LocalDate.now());
        albumSave = repository.save(albumSave);
        log.info("{} album added.", albumSave.getName());
        return mapper.map(albumSave, CreateAlbumResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfAlbumExists(id);
        repository.deleteById(id);
        log.info("{} album deleted.", this.getById(id).getName());
    }

    @Override
    public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
        rules.checkIfAlbumExists(id);
        Album updateAlbum = mapper.map(request, Album.class);
        updateAlbum.setId(id);
        updateAlbum = repository.save(updateAlbum);
        log.info("{} album updated.", updateAlbum.getName());
        return mapper.map(updateAlbum, UpdateAlbumResponse.class);
    }

    @Override
    public GetAlbumResponse getById(UUID id) {
        rules.checkIfAlbumExists(id);
        var album = repository.findById(id).orElseThrow();
        log.info("Album service: {} getById method called.", this.getById(id).getName());
        return mapper.map(album, GetAlbumResponse.class);
    }


}
