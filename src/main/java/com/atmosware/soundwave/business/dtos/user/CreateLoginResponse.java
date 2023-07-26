package com.atmosware.soundwave.business.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoginResponse {
    private boolean isLogin;
    private String role;
}
