package com.atmosware.soundwave.business.dtos.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistRequest {
    private String name;
}
