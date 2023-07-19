package com.turkcell.soundwave.business.abstracts;

import com.turkcell.soundwave.business.dtos.user.*;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<GetAllUsersResponse> getAll();

    CreateUserResponse add(CreateUserRequest request);

    void delete(UUID id);

    UpdateUserResponse update(UUID id, UpdateUserRequest request);

    GetUserResponse getById(UUID id);
}
