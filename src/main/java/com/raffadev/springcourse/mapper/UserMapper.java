package com.raffadev.springcourse.mapper;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
}