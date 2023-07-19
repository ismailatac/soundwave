package com.turkcell.soundwave.business.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private UUID id;
    private String name;
    private String password;
}
