package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.entities.PopularSong;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PopularSongBusinessRules {
    public void checkIfAnyPopularSongExists(List<PopularSong> popularSongs) {
    }

    public void checkIfPopularSongExists(UUID id) {
    }
}
