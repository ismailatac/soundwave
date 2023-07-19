package com.turkcell.soundwave.business.dtos.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArtistResponse {
    private UUID id;
    private String name;
}
