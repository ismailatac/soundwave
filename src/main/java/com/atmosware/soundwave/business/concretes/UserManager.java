package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AuthService;
import com.atmosware.soundwave.business.abstracts.UserService;
import com.atmosware.soundwave.business.dtos.user.*;
import com.atmosware.soundwave.business.rules.UserBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateRegisterRequest;
import com.atmosware.soundwave.core.utilities.mapstruct.UserMapper;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserBusinessRules rules;
    private final AuthService authService;
    private final UserMapper userMapper;

    @Override
    public List<GetAllUsersResponse> getAll() {
        var users = repository.findAll();
        rules.checkIfAnyUserExists(users);
        log.info("User service getAll method called.");
        return users.stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class)).toList();
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        rules.checkIfSameUserExists(request);
        User userSave = mapper.map(request, User.class);
        userSave.setId(null);
        CreateUserResponse responseUser;
        try {
            var authResponse = authService.register(mapper.map(request, CreateRegisterRequest.class));
            userSave = userMapper.convertToCreateAuthResponseToUser(authResponse);
            var repoUser = repository.save(userSave);
            authResponse.setUser(repoUser);
            responseUser = userMapper.convertCreateAuthResponseToCreateUserResponse(authResponse);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        log.info("{} user added.", userSave.getName());
        return responseUser;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfUserExists(id);
        repository.deleteById(id);
        log.info("{} user deleted.", this.getById(id).getName());
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        rules.checkIfUserExists(id);
        User updateUser = mapper.map(request, User.class);
        updateUser.setId(id);
        var userResponse = repository.save(updateUser);
        log.info("{} user updated.", updateUser.getName());
        return mapper.map(userResponse, UpdateUserResponse.class);
    }

    @Override
    public GetUserResponse getById(UUID id) {
        var user = repository.findById(id).orElseThrow();
        log.info("User service: {} getById method called.", this.getById(id).getName());
        return mapper.map(user, GetUserResponse.class);
    }

    @Override
    public boolean checkUserExists(List<UUID> uuids) {
        for (UUID user : uuids) {
            if (!repository.existsById(user)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CreateLoginResponse login(CreateLoginRequest request) {
        return rules.checkPassword(request.getUsername(), request.getPassword());
    }

}
