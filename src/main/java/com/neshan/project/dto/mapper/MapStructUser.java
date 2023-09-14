package com.neshan.project.dto.mapper;

import com.neshan.project.domain.User;
import com.neshan.project.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapStructUser {
    User userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}
