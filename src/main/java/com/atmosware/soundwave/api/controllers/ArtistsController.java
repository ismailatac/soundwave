package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.dtos.artist.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
@AllArgsConstructor
public class ArtistsController {
    private final ArtistService service;


    @GetMapping
    @PreAuthorize("hasAnyRole('USER')")
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
