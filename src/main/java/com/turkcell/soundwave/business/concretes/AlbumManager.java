package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.AlbumService;
import com.turkcell.soundwave.business.dtos.album.*;
import com.turkcell.soundwave.entities.Album;
import com.turkcell.soundwave.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlbumManager implements AlbumService {

    private final AlbumRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllAlbumsResponse> getAll() {

        var albums = repository.findAll();
        return albums.stream()
                .map(album -> mapper.map(album, GetAllAlbumsResponse.class)).toList();
    }

    @Override
    public CreateAlbumResponse add(CreateAlbumRequest request) {
        Album albumSave = mapper.map(request, Album.class);
        albumSave.setId(null);
        Album responseAlbum = repository.save(albumSave);
        return mapper.map(responseAlbum, CreateAlbumResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfAlbumExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
//        rules.checkIfAlbumExists(id);
        Album updateAlbum = mapper.map(request, Album.class);
        updateAlbum.setId(id);
        Album albumResponse = repository.save(updateAlbum);
        return mapper.map(albumResponse, UpdateAlbumResponse.class);
    }

    @Override
    public GetAlbumResponse getById(UUID id) {
        Album album = repository.findById(id).orElseThrow();
        GetAlbumResponse response = mapper.map(album, GetAlbumResponse.class);
        return response;
    }

}
