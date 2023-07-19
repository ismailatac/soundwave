package com.turkcell.soundwave.api.controllers;

import com.turkcell.soundwave.business.abstracts.UserService;
import com.turkcell.soundwave.business.dtos.user.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService service;


    @GetMapping
    public List<GetAllUsersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse update(@PathVariable UUID id, @RequestBody UpdateUserRequest user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@Valid @RequestBody CreateUserRequest user) {
        return service.add(user);
    }


}
