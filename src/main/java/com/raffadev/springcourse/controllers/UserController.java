package com.raffadev.springcourse.controllers;

import com.raffadev.springcourse.model.UserModel;
import com.raffadev.springcourse.services.UserService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("")
    public List<UserModel> findAll() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public String getUsername(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserModel::getUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
    @PutMapping("/{id}")
    public String UpdateUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        try {
            if (userService.save(dto, id)) {
                return "Updated";
            }  else {
                return "Not Updated";
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String DeleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return "Deleted";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
