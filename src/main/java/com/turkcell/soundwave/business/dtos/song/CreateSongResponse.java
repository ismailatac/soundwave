package com.turkcell.soundwave.business.dtos.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSongResponse {
    private UUID id;
    private String name;
    private UUID genreId;
    private UUID artistId;
    private UUID albumId;
}
