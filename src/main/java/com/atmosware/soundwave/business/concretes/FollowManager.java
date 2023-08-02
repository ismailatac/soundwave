package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.FollowService;
import com.atmosware.soundwave.business.abstracts.UserService;
import com.atmosware.soundwave.business.dtos.follow.*;
import com.atmosware.soundwave.business.rules.FollowBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.core.utilities.mapstruct.FollowMapper;
import com.atmosware.soundwave.entities.Favorite;
import com.atmosware.soundwave.entities.Follow;
import com.atmosware.soundwave.repository.FollowRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class FollowManager implements FollowService {

    private final FollowRepository repository;
    private final ModelMapper mapper;
    private final FollowMapper followMapper;
    private final FollowBusinessRules rules;

    @Override
    public List<GetAllFollowsResponse> getAll() {
        var follows = repository.findAll();
        rules.checkIfAnyFollowExists(follows);
        log.info("Follow service getAll method called.");
        return follows.stream()
                .map(follow -> mapper.map(follow, GetAllFollowsResponse.class)).toList();
    }

    @Override
    public CreateFollowResponse add(CreateFollowRequest request) {
        rules.checkIfUserIdExists(request);
        Follow followSave = followMapper.convertCreateFollowRequestToFollow(request);
        var responseFollow = repository.save(followSave);
        log.info("{} followed to {}",request.getFollowedId(),request.getFollowingId());
        return followMapper.convertFollowToCreateFollowResponse(responseFollow);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfFollowExists(id);
        repository.deleteById(id);
        log.info("{} follow deleted.", id);
    }

    @Override
    public UpdateFollowResponse update(UUID id, UpdateFollowRequest request) {
        Follow updateFollow = followMapper.convertUpdateFollowRequestToFollow(request);
        updateFollow.setId(id);
        var followResponse = repository.save(updateFollow);
        log.info("{} follow updated.", updateFollow.getId());
        return mapper.map(followResponse, UpdateFollowResponse.class);
    }

    @Override
    public GetFollowResponse getById(UUID id) {
        var follow = repository.findById(id).orElseThrow();
        log.info("Follow service: {} getById method called.", id);
        return mapper.map(follow, GetFollowResponse.class);
    }
}
