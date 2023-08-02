package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.PopularSongService;
import com.atmosware.soundwave.business.dtos.popularSong.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/popularSongs")
@AllArgsConstructor
public class PopularSongController {
    private final PopularSongService service;


    @GetMapping
    public List<GetAllPopularSongsResponse> getAll() throws InterruptedException {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPopularSongResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatePopularSongResponse update(@PathVariable UUID id, @RequestBody UpdatePopularSongRequest popularSong) {
        return service.update(id, popularSong);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePopularSongResponse add(@RequestBody CreatePopularSongRequest popularSong) {
        return service.add(popularSong);
    }


}