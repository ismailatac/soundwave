package com.turkcell.soundwave.business.dtos.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFollowRequest {
    private UUID followingId;
    private UUID followedId;
}
