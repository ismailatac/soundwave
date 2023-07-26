package com.atmosware.soundwave.business.dtos.favorite;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFavoriteResponse {
    private UUID id;
    private UUID songId;
    private UUID userId;
}
