package com.turkcell.soundwave.business.rules;

import com.turkcell.soundwave.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistBusinessRules {
    private final ArtistRepository repository;

    public void checkIfArtistExists(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException("Albüm bulunamadı");
    }

}
