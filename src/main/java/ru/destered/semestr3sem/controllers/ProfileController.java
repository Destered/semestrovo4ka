package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.security.details.UserDetailsImpl;
import ru.destered.semestr3sem.security.token.TokenAuthentication;
import ru.destered.semestr3sem.services.interfaces.MailService;

import javax.servlet.http.Cookie;

@RestController
@RequiredArgsConstructor
@Tag(name="Profile controller", description="Profile controller API")
public class ProfileController {
    private final MailService mailService;

    @Operation(
            summary = "get profile",
            description = "return user profile from token"
    )
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getProfile")
    public UserDto getProfile(@CookieValue Cookie token) {
        DecodedJWT jwt = JWT.decode(token.getValue());
        return UserDto.builder()
                .id(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .role(jwt.getClaim("role").asString())
                .state(jwt.getClaim("state").asString())
                .build();
    }

    @Operation(
            summary = "send user details",
            description = "send user details to mail"
    )
    @PreAuthorize("@additionalUserDetails.updateUserDetails(principal)")
    @GetMapping("/sendUserDetails")
    public void sendUserDetailsToEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl)((TokenAuthentication)authentication).getUserDetails()).getUser();
        mailService.sendMail(user);
    }
}
