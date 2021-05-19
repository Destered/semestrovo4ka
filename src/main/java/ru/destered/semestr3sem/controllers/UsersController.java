package ru.destered.semestr3sem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="User controller", description="User controller API")
public class UsersController {
    private final UsersService viewUserService;

    @Operation(
            summary = "get users",
            description = "get user with id"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/users")
    public Page<UserDto> getUsers(@PathVariable Optional<String> id) {
        return viewUserService.getUsers(Integer.parseInt(id.orElse("0")));
    }

    @Operation(
            summary = "get users",
            description = "get users with id"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(viewUserService.getUser(id), HttpStatus.OK);
    }

    @Operation(
            summary = "create user",
            description = "create new user"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUsers(@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.createUser(form), HttpStatus.OK);
    }

    @Operation(
            summary = "edit user",
            description = "edit user"
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUsers(@PathVariable Long id,@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.updateUser(id, form), HttpStatus.OK);
    }

    @Operation(
            summary = "delete user",
            description = "delete users id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
        viewUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}