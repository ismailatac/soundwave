package com.turkcell.soundwave.business.dtos.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlbumRequest {
    private String name;
    private int numberOfSong;
    //private LocalDate releaseDate;
    // TODO: managerda tanımla
    private UUID artistId;
}
