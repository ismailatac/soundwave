package com.atmosware.soundwave.business.concretes;

import com.atmosware.soundwave.business.abstracts.AlbumService;
import com.atmosware.soundwave.business.abstracts.ArtistService;
import com.atmosware.soundwave.business.abstracts.GenreService;
import com.atmosware.soundwave.business.abstracts.SongService;
import com.atmosware.soundwave.business.dtos.song.*;
import com.atmosware.soundwave.business.rules.SongBusinessRules;
import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.core.utilities.cloudinary.CloudinaryService;
import com.atmosware.soundwave.entities.Album;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.entities.Genre;
import com.atmosware.soundwave.entities.Song;
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
        List<Song> songs;
        try {
            songs = repository.findAll();
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        rules.checkIfAnySongExists(songs);
        return songs.stream().map(song -> mapper.map(song, GetAllSongsResponse.class)).toList();
        //TODO:stream ->  filter flapmap arasındaki fark -- optional
    }



    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        Genre genre = mapper.map(genreService.getById(request.getGenreId()), Genre.class);
        Album album = mapper.map(albumService.getById(request.getAlbumId()), Album.class);
        Song songSave = new Song(null,request.getName(),null,genre,album);
        Song responseSong;
        try {
            responseSong = repository.save(songSave);
        } catch (Exception e) {
            log.error(ExceptionTypes.Exception.Database +": "+  e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(responseSong, CreateSongResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfSongExists(id);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public UpdateSongResponse update(UUID id, UpdateSongRequest request) {
        rules.checkIfSongExists(id);
        Song updateSong = mapper.map(request, Song.class);
        updateSong.setId(id);
        Song songResponse;
        try{
             songResponse = repository.save(updateSong);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return mapper.map(songResponse, UpdateSongResponse.class);
        //TODO: SLf4J log nereye yazılacak Log4J hangisini kullanmalıyım
    }

    @Override
    public GetSongResponse getById(UUID id) {
        Song song;
        try {
             song = repository.findById(id).orElseThrow();
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        GetSongResponse response = mapper.map(song, GetSongResponse.class);
        return response;
    }

    @Override
    public UploadSongResponse upload(UUID songId, MultipartFile file) {
        Song song = repository.findById(songId).orElseThrow();
        song = this.uploadFile(song,file);
        System.out.println(song.getUrl());
        repository.save(song);
        return new UploadSongResponse(true);
    }

    private Song uploadFile(Song song, MultipartFile file) {
        try {
            Map<?, ?> uploadSong = cloudinaryService.upload(file).getUploadResult();
             song.setUrl(uploadSong.get("url").toString());
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+ e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return song;
    }
}
