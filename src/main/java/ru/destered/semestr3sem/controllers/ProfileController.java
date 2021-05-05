package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
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
public class ProfileController {
    private final MailService mailService;

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

    @PreAuthorize("@additionalUserDetails.updateUserDetails(principal)")
    @GetMapping("/sendUserDetails")
    public void sendUserDetailsToEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl)((TokenAuthentication)authentication).getUserDetails()).getUser();
        mailService.sendMail(user);
    }
}
