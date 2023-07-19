package com.turkcell.soundwave.business.rules;

import com.turkcell.soundwave.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FollowBusinessRules {
    private final AlbumRepository repository;

    public void checkIfAlbumExists(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException("Albüm bulunamadı");
    }

}
