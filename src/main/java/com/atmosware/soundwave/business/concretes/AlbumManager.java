package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AlbumService;
import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.dtos.album.*;
import com.atmosware.soundwave.business.dtos.artist.GetArtistResponse;
import com.atmosware.soundwave.business.rules.AlbumBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
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
        List<Album> albums;
        try {
            albums = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyAlbumExists(albums);

        return albums.stream()
                .map(album -> mapper.map(album, GetAllAlbumsResponse.class)).toList();
    }

    @Override
    public CreateAlbumResponse add(CreateAlbumRequest request) {
        GetArtistResponse artistResponse;

        try {
            artistResponse = artistService.getById(request.getArtistId());
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        var artist = mapper.map(artistResponse, Artist.class);
        Album albumSave = mapper.map(request, Album.class);
        rules.checkIfAlbumSameNameExists(artist, request.getName());
        albumSave.setId(null);
        albumSave.setArtist(artist);
        albumSave.setReleaseDate(LocalDate.now());

        try {
            albumSave = repository.save(albumSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

        return mapper.map(albumSave, CreateAlbumResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfAlbumExists(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        log.info("DELETE ALBUM");
    }

    @Override
    public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
        rules.checkIfAlbumExists(id);
        Album updateAlbum = mapper.map(request, Album.class);
        updateAlbum.setId(id);
        try {
            updateAlbum = repository.save(updateAlbum);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

        return mapper.map(updateAlbum, UpdateAlbumResponse.class);
    }

    @Override
    public GetAlbumResponse getById(UUID id) {
        Album album;
        rules.checkIfAlbumExists(id);
        try {
            album = repository.findById(id).orElseThrow();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        log.info("GETBYID ALBUM");
        return mapper.map(album, GetAlbumResponse.class);
    }

}
