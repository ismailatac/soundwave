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
    Artist convertCreateArtistRequestToArtist(CreateArtistRequest artistRequest);
    CreateArtistResponse convertArtistToCreateArtistResponse(Artist artist);
    Artist convertUpdateArtistRequestToArtist(UpdateArtistRequest artistRequest);
    UpdateArtistResponse convertArtistToUpdateArtistResponse(Artist artist);
    GetArtistResponse convertArtistToGetArtistResponse(Artist artist);
}

