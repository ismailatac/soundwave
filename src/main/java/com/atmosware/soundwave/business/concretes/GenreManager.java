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
        List<Genre> genres;
        try {
             genres = repository.findAll();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnyGenreExists(genres);
        return genres.stream()
                .map(genre -> mapper.map(genre, GetAllGenresResponse.class)).toList();
    }

    @Override
    public CreateGenreResponse add(CreateGenreRequest request) {
        Genre genreSave = mapper.map(request, Genre.class);
        genreSave.setId(null);
        Genre responseGenre;
        try{
             responseGenre = repository.save(genreSave);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

        return mapper.map(responseGenre, CreateGenreResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfGenreExists(id);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public UpdateGenreResponse update(UUID id, UpdateGenreRequest request) {
        rules.checkIfGenreExists(id);
        Genre updateGenre = mapper.map(request, Genre.class);
        updateGenre.setId(id);
        Genre genreResponse;
        try{
             genreResponse = repository.save(updateGenre);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(genreResponse, UpdateGenreResponse.class);
    }

    @Override
    public GetGenreResponse getById(UUID id) {
        Genre genre;
        try {
            genre = repository.findById(id).orElseThrow();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        GetGenreResponse response = mapper.map(genre, GetGenreResponse.class);
        return response;
    }

}
