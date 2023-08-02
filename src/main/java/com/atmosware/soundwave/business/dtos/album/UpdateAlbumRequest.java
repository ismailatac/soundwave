package com.atmosware.soundwave.business.dtos.album;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAlbumRequest {
    private String name;
//    private int numberOfSong;
//    private LocalDate releaseDate;
}
