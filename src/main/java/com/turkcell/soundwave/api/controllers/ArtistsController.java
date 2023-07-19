package com.turkcell.soundwave.api.controllers;

import com.turkcell.soundwave.business.abstracts.ArtistService;
import com.turkcell.soundwave.business.dtos.artist.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artists")
@AllArgsConstructor
public class ArtistsController {
    private final ArtistService service;


    @GetMapping
    public List<GetAllArtistsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetArtistResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateArtistResponse update(@PathVariable UUID id, @RequestBody UpdateArtistRequest artist) {
        return service.update(id, artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateArtistResponse add(@Valid @RequestBody CreateArtistRequest artist) {
        return service.add(artist);
    }


}
