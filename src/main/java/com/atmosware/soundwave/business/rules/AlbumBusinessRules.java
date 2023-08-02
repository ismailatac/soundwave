package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.entities.Album;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumBusinessRules {
    private final AlbumRepository repository;

    public void checkIfAnyAlbumExists(List<Album> albums) {
        if (albums.isEmpty()) {
            log.error(ExceptionTypes.Exception.Business + ": Albümler bulunamadı");
            throw new BusinessException("Albümler bulunamadı");
        }
    }

    public void checkIfAlbumExists(UUID id) {
        if (!repository.existsById(id)) {
            log.error(ExceptionTypes.Exception.Business + ": Albüm bulunamadı");
            throw new BusinessException("Albüm bulunamadı!");
        }
    }

    public void checkIfAlbumSameNameExists(Artist artist, String name) {
        List<Album> albums = repository.findByArtist(artist);
        for (Album album : albums) {
            if (album.getName().equals(name)) {
                log.error(ExceptionTypes.Exception.Business + ": Aynı isimde albüm mevcut");
                throw new BusinessException("Aynı isimde albüm mevcut");
            }
        }
    }
}
