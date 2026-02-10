package com.raffadev.springcourse.services;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.mapper.UserMapper;
import com.raffadev.springcourse.model.User;
import com.raffadev.springcourse.repository.UserRepository;
import com.raffadev.springcourse.exceptions.ResourceNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User with id " + id + " not found"));
    }

    public Boolean save(UserDTO user) {
        try {
            userRepository.save(this.userMapper.toEntity(user));
            return true;
        }catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Boolean update(UserDTO user, Long id) {
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
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFound("User  with id " + id + " not found");
            }
            userRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }


}
