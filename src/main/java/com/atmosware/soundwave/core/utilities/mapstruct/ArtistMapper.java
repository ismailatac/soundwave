package com.atmosware.soundwave.core.utilities.mapstruct;

import com.atmosware.soundwave.business.dtos.artist.*;
import com.atmosware.soundwave.entities.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;


@Mapper(componentModel = "spring", imports = UUID.class)
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Artist map_create(CreateArtistRequest artistRequest);
    CreateArtistResponse map_create(Artist artist);
    Artist map_update(UpdateArtistRequest artistRequest);
    UpdateArtistResponse map_update(Artist artist);
    GetArtistResponse map_getbyid(Artist artist);
}

