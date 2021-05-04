package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.services.interfaces.UsersService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService viewUserService;

    @PreAuthorize("permitAll()")
    @GetMapping("/users")
    public Page<UserDto> getUsers(@PathVariable Optional<String> id) {
        return viewUserService.getUsers(Integer.parseInt(id.orElse("0")));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(viewUserService.getUser(id), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUsers(@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.createUser(form), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUsers(@PathVariable Long id,@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.updateUser(id, form), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
        viewUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}