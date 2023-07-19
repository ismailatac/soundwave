package com.turkcell.soundwave.business.concretes;

import com.turkcell.soundwave.business.abstracts.GenreService;
import com.turkcell.soundwave.business.dtos.genre.*;
import com.turkcell.soundwave.entities.Genre;
import com.turkcell.soundwave.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreManager implements GenreService {

    private final GenreRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllGenresResponse> getAll() {

        var genres = repository.findAll();
        return genres.stream()
                .map(genre -> mapper.map(genre, GetAllGenresResponse.class)).toList();
    }

    @Override
    public CreateGenreResponse add(CreateGenreRequest request) {
        Genre genreSave = mapper.map(request, Genre.class);
        genreSave.setId(null);
        Genre responseGenre = repository.save(genreSave);
        return mapper.map(responseGenre, CreateGenreResponse.class);
    }

    @Override
    public void delete(UUID id) {
//        rules.checkIfGenreExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateGenreResponse update(UUID id, UpdateGenreRequest request) {
//        rules.checkIfGenreExists(id);
        Genre updateGenre = mapper.map(request, Genre.class);
        updateGenre.setId(id);
        Genre genreResponse = repository.save(updateGenre);
        return mapper.map(genreResponse, UpdateGenreResponse.class);
    }

    @Override
    public GetGenreResponse getById(UUID id) {
        Genre genre = repository.findById(id).orElseThrow();
        GetGenreResponse response = mapper.map(genre, GetGenreResponse.class);
        return response;
    }

}
