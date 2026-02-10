package com.raffadev.springcourse.services;

import com.raffadev.springcourse.dto.UserDTO;
import com.raffadev.springcourse.mapper.UserMapper;
import com.raffadev.springcourse.model.UserEntity;
import com.raffadev.springcourse.repository.UserRepository;
import com.raffadev.springcourse.exceptions.ResourceNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(String username, String password, String email) {
        String passwordHash = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, passwordHash, email);
        userRepository.save(user);
        return user;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("UserEntity with id " + id + " not found"));
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
                throw new ResourceNotFound("UserEntity  with id " + id + " not found");
            }
            userRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }


}
