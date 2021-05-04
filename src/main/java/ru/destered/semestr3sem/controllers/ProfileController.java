package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.destered.semestr3sem.dto.UserDto;

import javax.servlet.http.Cookie;

@RestController
public class ProfileController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getProfile")
    public UserDto getProfile(@CookieValue Cookie token) {
        DecodedJWT jwt = JWT.decode(token.getValue());

        return UserDto.builder()
                .id(Long.parseLong(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .email(jwt.getClaim("email").asString())
                .role(jwt.getClaim("role").asString())
                .state(jwt.getClaim("state").asString())
                .phone(jwt.getClaim("phone").asString())
                .build();
    }
}
