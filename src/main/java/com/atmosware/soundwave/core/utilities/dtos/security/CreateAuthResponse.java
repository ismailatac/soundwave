package com.atmosware.soundwave.core.utilities.dtos.security;

import com.atmosware.soundwave.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthResponse {
    private String token;
    private User user;
}
