package com.atmosware.soundwave.business.dtos.user;

import com.atmosware.soundwave.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String name;
    private String password;
    private Role role;
}
