package com.turkcell.soundwave.business.dtos.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFollowsResponse {
    private UUID id;
    private UUID followingId;
    private UUID followedId;
}
