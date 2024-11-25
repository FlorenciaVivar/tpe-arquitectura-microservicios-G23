package com.example.gateways.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.gateways.service.UserService;
import com.example.gateways.service.dto.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usersSecurity")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser( @RequestBody @Valid UserDTO userDTO) {
        final var id = userService.saveUser( userDTO );
        return new ResponseEntity<>( id, HttpStatus.CREATED );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("Usuario eliminado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario no fue encontrado.");
        }
    }
}
