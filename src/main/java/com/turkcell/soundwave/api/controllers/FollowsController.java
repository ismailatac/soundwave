package com.turkcell.soundwave.api.controllers;

import com.turkcell.soundwave.business.abstracts.FollowService;
import com.turkcell.soundwave.business.dtos.follow.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/follows")
@AllArgsConstructor
public class FollowsController {
    private final FollowService service;


    @GetMapping
    public List<GetAllFollowsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFollowResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateFollowResponse update(@PathVariable UUID id, @RequestBody UpdateFollowRequest follow) {
        return service.update(id, follow);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFollowResponse add(@Valid @RequestBody CreateFollowRequest follow) {
        return service.add(follow);
    }


}
