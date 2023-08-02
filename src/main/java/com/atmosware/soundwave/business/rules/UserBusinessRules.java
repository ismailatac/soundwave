package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.business.abstracts.AuthService;
import com.atmosware.soundwave.business.dtos.user.CreateLoginResponse;
import com.atmosware.soundwave.business.dtos.user.CreateUserRequest;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.AuthException;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthRequest;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthResponse;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;
    private final AuthService authService;


    public void checkIfAnyUserExists(List<User> users) {
        if (users.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business + ": Kullanıcılar bulunamadı!");
            throw new BusinessException("Kullanıcılar bulunamadı!");
        }
    }

    public void checkIfUserExists(UUID id) {
        if (!repository.existsById(id)) {
            log.error(ExceptionTypes.Exception.Business + ": Kullanıcı bulunamadı!");
            throw new BusinessException("Kullanıcı bulunamadı!");
        }
    }

    public CreateLoginResponse checkPassword(String username, String password) {
        CreateAuthResponse authResponse;
        try {
            authResponse = authService.authenticate(new CreateAuthRequest(username, password));
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Auth + " " + e.getMessage());
            throw new AuthException("Giriş yapılamadı!");
        }
        log.info("{} logged in.",username);
        return new CreateLoginResponse(true, authResponse.getUser().getRole(), authResponse.getToken());

    }

    public void checkIfSameUserExists(CreateUserRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new BusinessException("Kullanıcı adı mevcut. Farklı bir kullanıcı adı giriniz!");
        }
    }
}
