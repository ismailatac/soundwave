package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.UserService;
import com.turkcell.soundwave.business.dtos.user.*;
import com.turkcell.soundwave.entities.User;
import com.turkcell.soundwave.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllUsersResponse> getAll() {

        var users = repository.findAll();
        return users.stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class)).toList();
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        User userSave = mapper.map(request, User.class);
        userSave.setId(null);
        User responseUser = repository.save(userSave);
        return mapper.map(responseUser, CreateUserResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfUserExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
//        rules.checkIfUserExists(id);
        User updateUser = mapper.map(request, User.class);
        updateUser.setId(id);
        User userResponse = repository.save(updateUser);
        return mapper.map(userResponse, UpdateUserResponse.class);
    }

    @Override
    public GetUserResponse getById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        GetUserResponse response = mapper.map(user, GetUserResponse.class);
        return response;
    }

}
