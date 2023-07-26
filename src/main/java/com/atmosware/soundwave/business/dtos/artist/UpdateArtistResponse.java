package com.atmosware.soundwave.business.dtos.artist;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistResponse {
    private UUID id;
    private String name;
}
