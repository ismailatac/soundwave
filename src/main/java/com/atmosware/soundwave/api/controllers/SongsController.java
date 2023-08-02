package com.atmosware.soundwave.api.controllers;

import com.atmosware.soundwave.business.abstracts.SongService;
import com.atmosware.soundwave.business.dtos.song.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/songs")
@AllArgsConstructor
public class SongsController {
    private final SongService service;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<GetAllSongsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSongResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateSongResponse update(@PathVariable UUID id, @RequestBody UpdateSongRequest song) {
        return service.update(id, song);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PostMapping(path = "/upload",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public UploadSongResponse upload(@RequestParam UUID songId, @RequestPart MultipartFile file) {
        return service.upload(songId,file);
    }
    @PostMapping
    public CreateSongResponse add(@RequestBody CreateSongRequest song) {
        return service.add(song);
    }



}
