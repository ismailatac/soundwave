package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.GenreService;
import com.atmosware.soundwave.business.dtos.genre.*;
import com.atmosware.soundwave.business.rules.GenreBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.entities.Genre;
import com.atmosware.soundwave.repository.GenreRepository;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GenreManager implements GenreService {

    private final GenreRepository repository;
    private final ModelMapper mapper;
    private final GenreBusinessRules rules;

    @Override
    public List<GetAllGenresResponse> getAll() {
        var genres = repository.findAll();
        rules.checkIfAnyGenreExists(genres);
        log.info("Genre service getAll method called.");
        return genres.stream()
                .map(genre -> mapper.map(genre, GetAllGenresResponse.class)).toList();
    }

    @Override
    public CreateGenreResponse add(CreateGenreRequest request) {
        Genre genreSave = mapper.map(request, Genre.class);
        genreSave.setId(null);
        var responseGenre = repository.save(genreSave);
        log.info("{} genre added.", genreSave.getName());
        return mapper.map(responseGenre, CreateGenreResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfGenreExists(id);
        repository.deleteById(id);
        log.info("{} genre deleted.", this.getById(id).getName());
    }

    @Override
    public UpdateGenreResponse update(UUID id, UpdateGenreRequest request) {
        rules.checkIfGenreExists(id);
        Genre updateGenre = mapper.map(request, Genre.class);
        updateGenre.setId(id);
        var genreResponse = repository.save(updateGenre);
        log.info("{} genre updated.", updateGenre.getName());
        return mapper.map(genreResponse, UpdateGenreResponse.class);
    }

    @Override
    public GetGenreResponse getById(UUID id) {
        var genre = repository.findById(id).orElseThrow();
        log.info("Genre service: {} getById method called.", this.getById(id).getName());
        return mapper.map(genre, GetGenreResponse.class);
    }

}
