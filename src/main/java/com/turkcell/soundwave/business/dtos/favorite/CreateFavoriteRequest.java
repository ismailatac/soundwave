package com.turkcell.soundwave.business.dtos.favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteRequest {
    private UUID songId;
    private UUID userId;
}
