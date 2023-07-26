package com.atmosware.soundwave.business.dtos.genre;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGenreResponse {
    private UUID id;
    private String name;
}
