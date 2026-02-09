package com.raffadev.springcourse.mapper;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toEntity(UserDTO dto);
}