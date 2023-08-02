package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthRequest;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthResponse;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateRegisterRequest;

public interface AuthService {
    CreateAuthResponse register(CreateRegisterRequest request);
    CreateAuthResponse authenticate(CreateAuthRequest request);
}
