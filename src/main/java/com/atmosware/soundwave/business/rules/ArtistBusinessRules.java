package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class ArtistBusinessRules {
    private final ArtistRepository repository;
    public void checkIfAnyArtistExists(List<Artist> artists) {
        if (artists.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business+": Sanatçılar bulunamadı!");
            throw new BusinessException("Sanatçılar bulunamadı!");
        }
    }
    public void checkIfArtistExists(UUID id) {
        if(!repository.existsById(id)){
            log.error(ExceptionTypes.Exception.Business+": Sanatçı bulunamadı!");
            throw new BusinessException("Sanatçı bulunamadı!");
        }
    }
}
