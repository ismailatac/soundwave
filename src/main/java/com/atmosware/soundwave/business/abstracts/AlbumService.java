package com.atmosware.soundwave.business.abstracts;

import com.atmosware.soundwave.business.dtos.album.*;
import java.util.List;
import java.util.UUID;

public interface AlbumService {
    List<GetAllAlbumsResponse> getAll();

    CreateAlbumResponse add(CreateAlbumRequest request);

    void delete(UUID id);

    UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request);

    GetAlbumResponse getById(UUID id);
}
