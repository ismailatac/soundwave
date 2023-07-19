package com.turkcell.soundwave.api.controllers;

import com.turkcell.soundwave.business.abstracts.SongService;
import com.turkcell.soundwave.business.dtos.song.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
@AllArgsConstructor
public class SongsController {
    private final SongService service;


    @GetMapping
    public List<GetAllSongsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSongResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateSongResponse update(@PathVariable UUID id, @RequestBody UpdateSongRequest song) {
        return service.update(id, song);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSongResponse add(@Valid @RequestBody CreateSongRequest song) {
        return service.add(song);
    }


}
