package com.atmosware.soundwave.core.utilities.mapstruct;

import com.atmosware.soundwave.business.dtos.artist.CreateArtistRequest;
import com.atmosware.soundwave.business.dtos.follow.CreateFollowRequest;
import com.atmosware.soundwave.business.dtos.follow.CreateFollowResponse;
import com.atmosware.soundwave.business.dtos.follow.UpdateFollowRequest;
import com.atmosware.soundwave.entities.Artist;
import com.atmosware.soundwave.entities.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface FollowMapper {
    FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Follow map_create(CreateFollowRequest followRequest);
    CreateFollowResponse map_create(Follow followRequest);
    Follow map_update(UpdateFollowRequest followRequest);
}
