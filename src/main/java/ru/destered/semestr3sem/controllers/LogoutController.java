package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.models.JwtToken;
import ru.destered.semestr3sem.repositories.TokenRepository;
import ru.destered.semestr3sem.services.interfaces.CookieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LogoutController {
    private final TokenRepository repository;

    @GetMapping("/customLogout")
    public String logout(@CookieValue Cookie token, HttpServletResponse response){
        JwtToken jwt = repository.findByValue(token.getValue())
                .orElseThrow(IllegalStateException::new);
        repository.delete(jwt);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/signIn";
    }
}
