package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Favorite;


import java.util.List;
import java.util.UUID;

import com.atmosware.soundwave.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class FavoriteBusinessRules {
    private final FavoriteRepository repository;


    public void checkIfAnyFavoriteExists(List<Favorite> albums) {
        if (albums.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business+": Favoriler bulunamadı!");
            throw new BusinessException("Favoriler bulunamadı");
        }
    }

    public void checkIfFavoriteExists(UUID id) {
        if (!repository.existsById(id)) {
            log.error(ExceptionTypes.Exception.Business+": Favori bulunamadı!");
            throw new BusinessException("Favori bulunamadı!");
        }
    }
}
