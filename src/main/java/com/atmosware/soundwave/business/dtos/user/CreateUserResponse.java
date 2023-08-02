package com.atmosware.soundwave.business.dtos.user;

import java.util.UUID;

import com.atmosware.soundwave.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private UUID id;
    private String name;
    private Role role;
    private String token;
}
