package com.atmosware.soundwave.business.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGenreRequest {
    private String name;
}
