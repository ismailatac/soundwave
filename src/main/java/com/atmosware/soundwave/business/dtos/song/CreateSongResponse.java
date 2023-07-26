package com.atmosware.soundwave.business.dtos.song;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSongResponse {
    private UUID id;
    private String name;
    private UUID genreId;
    private UUID albumId;
    private String url;
}
