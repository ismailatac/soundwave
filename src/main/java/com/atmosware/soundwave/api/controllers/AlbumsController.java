package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.AlbumService;
import com.atmosware.soundwave.business.dtos.album.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/albums")
public class AlbumsController {
    private final AlbumService service;


    @GetMapping
    public List<GetAllAlbumsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAlbumResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateAlbumResponse update(@PathVariable UUID id, @RequestBody UpdateAlbumRequest album) {
        return service.update(id, album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAlbumResponse add( @RequestBody CreateAlbumRequest album) {
        return service.add(album);
    }


}
