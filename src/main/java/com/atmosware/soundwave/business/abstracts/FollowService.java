package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.follow.*;
import java.util.List;
import java.util.UUID;

public interface FollowService {
    List<GetAllFollowsResponse> getAll();

    CreateFollowResponse add(CreateFollowRequest request);

    void delete(UUID id);

    UpdateFollowResponse update(UUID id, UpdateFollowRequest request);

    GetFollowResponse getById(UUID id);
}
