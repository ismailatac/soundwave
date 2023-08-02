package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Song;
import com.atmosware.soundwave.repository.SongRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SongBusinessRules {
    private final SongRepository repository;

    public void checkIfSongExists(UUID id) {

        if (!repository.existsById(id)){
            log.error(ExceptionTypes.Exception.Business + ": Şarkı bulunamadı!");
            throw new BusinessException("Şarkı bulunamadı!");
        }
    }

    public void checkIfAnySongExists(List<Song> songs) {

        if (songs.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business + ": Şarkılar bulunamadı!");
            throw new BusinessException("Şarkılar bulunamadı!");
        }
    }

}
