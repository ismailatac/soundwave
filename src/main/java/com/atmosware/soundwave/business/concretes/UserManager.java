package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.UserService;
import com.atmosware.soundwave.business.dtos.user.*;
import com.atmosware.soundwave.business.rules.UserBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
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

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users;
        try {
            users = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyUserExists(users);
        return users.stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class)).toList();
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        User userSave = mapper.map(request, User.class);
        userSave.setId(null);
        User responseUser;
        try {
            responseUser = repository.save(userSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(responseUser, CreateUserResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfUserExists(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        rules.checkIfUserExists(id);
        User updateUser = mapper.map(request, User.class);
        updateUser.setId(id);
        User userResponse;
        try {
            userResponse = repository.save(updateUser);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(userResponse, UpdateUserResponse.class);
    }

    @Override
    public GetUserResponse getById(UUID id) {
        User user;
        try {
            user = repository.findById(id).orElseThrow();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database + ": " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        GetUserResponse response = mapper.map(user, GetUserResponse.class);
        return response;
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
        var login = rules.checkPassword(request.getUsername(), request.getPassword());
        return new CreateLoginResponse(login,"admin"); //TODO: role düzenle
    }

}
