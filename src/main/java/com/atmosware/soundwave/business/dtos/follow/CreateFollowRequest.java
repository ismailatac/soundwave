package com.atmosware.soundwave.business.dtos.follow;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFollowRequest {
    private UUID followingId;
    private UUID followedId;
}
