package com.atmosware.soundwave.core.utilities.dtos.security;

import com.atmosware.soundwave.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegisterRequest {
    private String name;
    private String password;
    private Role role;
}
