package com.atmosware.soundwave.business.dtos.popularSong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePopularSongRequest {
    private UUID songId;
}
