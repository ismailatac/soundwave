package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.FollowService;
import com.turkcell.soundwave.business.dtos.follow.*;
import com.turkcell.soundwave.entities.Follow;
import com.turkcell.soundwave.repository.FollowRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FollowManager implements FollowService {

    private final FollowRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllFollowsResponse> getAll() {

        var follows = repository.findAll();
        return follows.stream()
                .map(follow -> mapper.map(follow, GetAllFollowsResponse.class)).toList();
    }

    @Override
    public CreateFollowResponse add(CreateFollowRequest request) {
        Follow followSave = mapper.map(request, Follow.class);
        followSave.setId(null);
        Follow responseFollow = repository.save(followSave);
        return mapper.map(responseFollow, CreateFollowResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfFollowExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateFollowResponse update(UUID id, UpdateFollowRequest request) {
//        rules.checkIfFollowExists(id);
        Follow updateFollow = mapper.map(request, Follow.class);
        updateFollow.setId(id);
        Follow followResponse = repository.save(updateFollow);
        return mapper.map(followResponse, UpdateFollowResponse.class);
    }

    @Override
    public GetFollowResponse getById(UUID id) {
        Follow follow = repository.findById(id).orElseThrow();
        GetFollowResponse response = mapper.map(follow, GetFollowResponse.class);
        return response;
    }

}
