package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.destered.semestr3sem.services.interfaces.CookieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController {
    private final CookieService cookieService;

    @GetMapping
    public String logout(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                         @RequestParam(value = "error", required = false) String error,
                         HttpServletResponse httpServletResponse,
                         Model model){

        return "redirect:/signIn";
    }
}
