package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.song.*;
import java.util.List;
import java.util.UUID;

import com.atmosware.soundwave.core.exceptions.CloudinaryException;
import org.springframework.web.multipart.MultipartFile;

public interface SongService {
    List<GetAllSongsResponse> getAll();

    CreateSongResponse add(CreateSongRequest request);

    void delete(UUID id);

    UpdateSongResponse update(UUID id, UpdateSongRequest request);

    GetSongResponse getById(UUID id);
    UploadSongResponse upload(UUID songId,MultipartFile file) throws CloudinaryException;
}
