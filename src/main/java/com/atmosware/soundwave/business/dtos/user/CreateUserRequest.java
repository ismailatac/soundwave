package com.atmosware.soundwave.business.dtos.user;

import com.atmosware.soundwave.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String password;
    private Role role;
}
