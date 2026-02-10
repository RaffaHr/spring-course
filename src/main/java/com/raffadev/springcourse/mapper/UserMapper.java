package com.raffadev.springcourse.mapper;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO dto);
}