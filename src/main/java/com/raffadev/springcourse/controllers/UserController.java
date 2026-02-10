package com.raffadev.springcourse.controllers;

import com.raffadev.springcourse.model.User;
import com.raffadev.springcourse.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Users", tags = "Users", description = "Lista todos os usuarios do banco")
    @GetMapping
    public List<User> findAll() {
        return userService.listUsers();
    }

    @Operation(summary = "Users", tags = "Users", description = "Retorna todos os dados de um usuário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Users", tags = "Users", description = "Atualiza os dados de um usuário")
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

    @Operation(summary = "Users", tags = "Users", description = "Cria um usuário e salva no banco de dados")
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

    @Operation(summary = "Users", tags = "Users", description = "Deleta um usuário no banco de dados")
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
