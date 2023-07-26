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
        List<Follow> follows;
        try {
             follows = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyFollowExists(follows);
        return follows.stream()
                .map(follow -> mapper.map(follow, GetAllFollowsResponse.class)).toList();
    }

    @Override
    public CreateFollowResponse add(CreateFollowRequest request) {
        rules.checkIfUserIdExists(request);
        Follow followSave = followMapper.map_create(request);
        Follow responseFollow;
        try {
             responseFollow = repository.save(followSave);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return followMapper.map_create(responseFollow);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfFollowExists(id);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public UpdateFollowResponse update(UUID id, UpdateFollowRequest request) {
        Follow updateFollow = followMapper.map_update(request);
        updateFollow.setId(id);
        Follow followResponse;
        try {
             followResponse = repository.save(updateFollow);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(followResponse, UpdateFollowResponse.class);
    }

    @Override
    public GetFollowResponse getById(UUID id) {
        Follow follow;
        try {
             follow = repository.findById(id).orElseThrow();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(follow, GetFollowResponse.class);
    }
}
