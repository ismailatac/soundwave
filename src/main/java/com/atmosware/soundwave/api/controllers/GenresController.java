package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.GenreService;
import com.atmosware.soundwave.business.dtos.genre.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@AllArgsConstructor
public class GenresController {
    private final GenreService service;


    @GetMapping
    public List<GetAllGenresResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetGenreResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateGenreResponse update(@PathVariable UUID id, @RequestBody UpdateGenreRequest genre) {
        return service.update(id, genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateGenreResponse add(@Valid @RequestBody CreateGenreRequest genre) {
        return service.add(genre);
    }


}
