package com.atmosware.soundwave.core.utilities.mapstruct;

import com.atmosware.soundwave.business.dtos.user.CreateUserResponse;
import com.atmosware.soundwave.core.utilities.dtos.security.CreateAuthResponse;
import com.atmosware.soundwave.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    User convertToCreateAuthResponseToUser(CreateAuthResponse response);
    CreateUserResponse convertCreateAuthResponseToCreateUserResponse(CreateAuthResponse authResponse);
}
