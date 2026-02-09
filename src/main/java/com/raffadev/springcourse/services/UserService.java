package com.raffadev.springcourse.services;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.mapper.UserMapper;
import com.raffadev.springcourse.model.UserModel;
import com.raffadev.springcourse.repository.UserRepository;
import org.hibernate.mapping.Any;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserModel> listUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean save(UserDTO user, Long id) {
        try {
            user.setId(id);
            userRepository.save(this.userMapper.toEntity(user));
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }


}
