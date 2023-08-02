package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AuthService;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.configuration.security.JwtService;
import com.atmosware.soundwave.core.exceptions.AuthException;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthRequest;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthResponse;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateRegisterRequest;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public CreateAuthResponse register(CreateRegisterRequest request) {
        var user = new User();
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setId(null);
        var jwtToken = jwtService.generateToken(user);
        return new CreateAuthResponse(jwtToken,user);
    }

    @Override
    public CreateAuthResponse authenticate(CreateAuthRequest request) {
        User user;
        String jwtToken;
        HashMap<String, Object> claims = new HashMap<>();
        try {
            user = repository.findByName(request.getName()).orElseThrow();
            for (var role : user.getAuthorities()) {
                claims.put("roles", role.toString());
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
            jwtToken = jwtService.generateToken(claims, user);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Auth+" "+e.getMessage());
            throw new AuthException("Kullanıcı doğrulanamadı!");
        }
        return new CreateAuthResponse(jwtToken,user);
    }
}
