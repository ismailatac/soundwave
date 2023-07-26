package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.business.abstracts.UserService;
import com.atmosware.soundwave.business.dtos.follow.CreateFollowRequest;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Follow;
import com.atmosware.soundwave.repository.FollowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class FollowBusinessRules {
    private final FollowRepository repository;
    private final UserService userService;

    public void checkIfUserIdExists(CreateFollowRequest request) {
        List<UUID> users = null;
        users.add(request.getFollowedId());
        users.add(request.getFollowingId());
        if (!userService.checkUserExists(users)){
            log.error(ExceptionTypes.Exception.Business+": User bulunamadı!");
            throw new BusinessException("User bulunamadı!");
        }
    }

    public void checkIfFollowExists(UUID id) {
        if (!repository.existsById(id)){
            log.error(ExceptionTypes.Exception.Business+": Follow bulunamadı!");
            throw new BusinessException("Follow bulunamadı!");
        }

    }

    public void checkIfAnyFollowExists(List<Follow> follows) {
        if (follows.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business+": Follow bulunamadı!");
            throw new BusinessException("Follow bulunamadı!");
        }
    }
}
