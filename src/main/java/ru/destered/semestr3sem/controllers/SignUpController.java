package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.services.interfaces.CookieService;
import ru.destered.semestr3sem.services.interfaces.MailService;
import ru.destered.semestr3sem.services.interfaces.SignUpService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
    private final CookieService cookieService;
    private final SignUpService signUpService;
    private final MailService mailService;
    private static final String ERROR = "error";

    @Value("${auth.user.redirect.url}")
    private String authUserRedirectUrl;

    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String getSignUpPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                @RequestParam(value = "error", required = false) String error,
                                Model model) {

        model.addAttribute(ERROR, error);
        model.addAttribute("isLogged",false);
        return "sign_up_page";
    }

    @PostMapping
    public String signUp(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                         @Valid SignUpForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            List<String> errorsList = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectAttributes.addAttribute(ERROR, errorsList.toString());
            return "redirect:/signUp";
        }
        UserDto userDto = null;
        if ((userDto = signUpService.signUp(form)) != null) {
            redirectAttributes.addAttribute("info", "Please confirm your email before continue");
            mailService.sendMail(userDto);
            return "redirect:/login";
        } else {
            redirectAttributes.addAttribute(ERROR, "Account with this email already exists");
            return "redirect:/signUp";
        }
    }
}