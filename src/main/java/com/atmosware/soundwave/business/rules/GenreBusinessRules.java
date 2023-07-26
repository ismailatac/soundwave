package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Genre;
import com.atmosware.soundwave.repository.AlbumRepository;

import java.util.List;
import java.util.UUID;

import com.atmosware.soundwave.repository.GenreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class GenreBusinessRules {
    private final GenreRepository repository;

    public void checkIfAnyGenreExists(List<Genre> genres) {
        if (genres.isEmpty()){
            log.error(ExceptionTypes.Exception.Business+": Şarkı türleri bulunamadı!");
            throw new BusinessException("Şarkı türleri bulunamadı!");
        }
    }

    public void checkIfGenreExists(UUID id) {
        if (!repository.existsById(id)){
            log.error(ExceptionTypes.Exception.Business+": Şarkı türü bulunamadı!");
            throw new BusinessException("Şarkı türü bulunamadı!");
        }
    }
}
