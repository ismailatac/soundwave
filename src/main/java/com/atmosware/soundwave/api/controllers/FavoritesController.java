package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.FavoriteService;
import com.atmosware.soundwave.business.dtos.favorite.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor
public class FavoritesController {
    private final FavoriteService service;


    @GetMapping
    public List<GetAllFavoritesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFavoriteResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateFavoriteResponse update(@PathVariable UUID id, @RequestBody UpdateFavoriteRequest favorite) {
        return service.update(id, favorite);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFavoriteResponse add(@Valid @RequestBody CreateFavoriteRequest favorite) {
        return service.add(favorite);
    }


}
