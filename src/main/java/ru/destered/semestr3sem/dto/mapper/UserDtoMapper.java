package ru.destered.semestr3sem.dto.mapper;


import org.mapstruct.*;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.models.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Named("userToDto")
    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "role", source = "user.role"),
            @Mapping(target = "state", source = "user.state"),
            @Mapping(target = "code", source = "user.currentConfirmationCode")
    })
    UserDto userToDto(User user);

    @InheritInverseConfiguration
    User dtoToUser(UserDto userDto);
}