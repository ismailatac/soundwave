package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.user.*;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<GetAllUsersResponse> getAll();

    CreateUserResponse add(CreateUserRequest request);

    void delete(UUID id);

    UpdateUserResponse update(UUID id, UpdateUserRequest request);

    GetUserResponse getById(UUID id);

    boolean checkUserExists(List<UUID> uuids);

    CreateLoginResponse login(CreateLoginRequest request);
}
