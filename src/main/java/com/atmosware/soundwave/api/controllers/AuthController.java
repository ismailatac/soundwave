package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.AuthService;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthRequest;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthResponse;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @PostMapping("/register")
    public CreateAuthResponse register(@RequestBody CreateRegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/authenticate")
    public CreateAuthResponse authenticate(@RequestBody CreateAuthRequest request) {
        return service.authenticate(request);
    }
}
