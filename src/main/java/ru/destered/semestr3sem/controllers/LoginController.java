package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.services.interfaces.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService login;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public RedirectView login(UserAuthForm form, HttpServletResponse response, @CookieValue(required = false) Cookie token) {
       if (token != null) token.setMaxAge(-1);
        TokenDto jwt = login.login(form);
        Cookie cookie = new Cookie("token", jwt.getToken());
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        return new RedirectView("/profile");
    }
}

