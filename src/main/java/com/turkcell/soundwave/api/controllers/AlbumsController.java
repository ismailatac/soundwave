package com.turkcell.soundwave.api.controllers;

import com.turkcell.soundwave.business.abstracts.AlbumService;
import com.turkcell.soundwave.business.dtos.album.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
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
    public CreateAlbumResponse add(@Valid @RequestBody CreateAlbumRequest album) {
        return service.add(album);
    }


}
