package com.atmosware.soundwave.core.utilities.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthRequest {
    private String name;
    private String password;
}
