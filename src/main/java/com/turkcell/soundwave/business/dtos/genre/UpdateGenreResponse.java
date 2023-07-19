package com.turkcell.soundwave.business.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGenreResponse {
    private UUID id;
    private String name;
}
