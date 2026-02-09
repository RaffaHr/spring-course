package com.raffadev.springcourse.controllers;

import com.raffadev.springcourse.model.UserModel;
import com.raffadev.springcourse.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import com.raffadev.springcourse.dto.UserDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> findAll() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        try {
            if (userService.update(dto, id)) {
                return "Updated";
            }  else {
                return "Not Updated";
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping
    public String createUser(@Valid @RequestBody UserDTO dto) {
        try {
            if (userService.save(dto)) {
                return "Created";
            }  else {
                return "Not Updated";
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            if (userService.delete(id)) {

                return "Deleted";
            } else  {
                return "Not Deleted";
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
