package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AlbumService;
import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.abstracts.GenreService;
import com.atmosware.soundwave.business.abstracts.SongService;
import com.atmosware.soundwave.business.dtos.song.*;
import com.atmosware.soundwave.business.rules.SongBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.CloudinaryException;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.core.utilities.cloudinary.CloudinaryService;
import com.atmosware.soundwave.entities.*;
import com.atmosware.soundwave.repository.SongRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class SongManager implements SongService {

    private final SongRepository repository;
    private final ModelMapper mapper;
    private final CloudinaryService cloudinaryService;
    private final SongBusinessRules rules;
    private final GenreService genreService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Override
    public List<GetAllSongsResponse> getAll() {
        var songs = repository.findAll();
        rules.checkIfAnySongExists(songs);
        log.info("Album service getAll method called.");
        return songs.stream().map(song -> mapper.map(song, GetAllSongsResponse.class)).toList();
        //TODO:stream ->  filter flapmap arasındaki fark -- optional
    }



    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        //todo: mapstruct kullan
        Genre genre = mapper.map(genreService.getById(request.getGenreId()), Genre.class);
        Album album = mapper.map(albumService.getById(request.getAlbumId()), Album.class);
        Song songSave = new Song();
        songSave.setId(null);
        songSave.setName(request.getName());
        songSave.setGenre(genre);
        songSave.setAlbum(album);
        var responseSong = repository.save(songSave);
        log.info("{} song added.", songSave.getName());
        return mapper.map(responseSong, CreateSongResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfSongExists(id);
        repository.deleteById(id);
        log.info("{} song deleted.", this.getById(id).getName());
    }

    @Override
    public UpdateSongResponse update(UUID id, UpdateSongRequest request) {
        rules.checkIfSongExists(id);
        Song updateSong = mapper.map(request, Song.class);
        updateSong.setId(id);
        var songResponse = repository.save(updateSong);
        log.info("{} song updated.", updateSong.getName());
        return mapper.map(songResponse, UpdateSongResponse.class);
    }

    @Override
    public GetSongResponse getById(UUID id) {
        var song = repository.findById(id).orElseThrow();
        log.info("Song service: {} getById method called.", this.getById(id).getName());
        return mapper.map(song, GetSongResponse.class);
    }

    @Override
    public UploadSongResponse upload(UUID songId, MultipartFile file) throws CloudinaryException {
        Song song = repository.findById(songId).orElseThrow();
        song = this.uploadFile(song,file);
        repository.save(song);
        log.info("{} file link added to song {}",file.getName(),song.getName());
        return new UploadSongResponse(true);
    }

    private Song uploadFile(Song song, MultipartFile file) throws CloudinaryException {
        try {
            Map<?, ?> uploadSong = cloudinaryService.upload(file).getUploadResult();
             song.setUrl(uploadSong.get("url").toString());
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Cloudinary+": "+ e.getMessage());
            throw new CloudinaryException(e.getMessage());
        }
        return song;
    }
}
