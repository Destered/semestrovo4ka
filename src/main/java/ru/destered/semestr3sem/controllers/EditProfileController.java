package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.services.interfaces.EditProfileService;

import javax.servlet.http.Cookie;

@RestController
@RequiredArgsConstructor
public class EditProfileController {
    private final EditProfileService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editProfileHandler")
    public RedirectView updateProfile(SignUpForm form, @CookieValue Cookie token) {
        DecodedJWT jwt = JWT.decode(token.getValue());
        service.updateProfile(Long.parseLong(jwt.getSubject()), form);
        return new RedirectView("/customLogout");
    }
}
