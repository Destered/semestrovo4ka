package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.destered.semestr3sem.models.JwtToken;
import ru.destered.semestr3sem.repositories.TokenRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LogoutController {
    private final TokenRepository repository;

    @GetMapping("/logout")
    public String logout(@CookieValue Cookie token, HttpServletResponse response){
        JwtToken jwt = repository.findByValue(token.getValue())
                .orElseThrow(IllegalStateException::new);
        repository.delete(jwt);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/login";
    }
}
