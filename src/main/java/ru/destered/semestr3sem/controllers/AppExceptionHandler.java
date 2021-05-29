package ru.destered.semestr3sem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import ru.destered.semestr3sem.exceptions.EmailNotConfirmException;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;
import ru.destered.semestr3sem.exceptions.StorageFileNotFoundException;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({ LoginProcessErrorException.class })
    public String loginException(RedirectAttributes redirectAttributes, Exception ex){
        redirectAttributes.addAttribute("error", "Login incorrect");
        return "redirect:/signIn";
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    public String usernameNotFoundException(RedirectAttributes redirectAttributes, Exception ex){
        redirectAttributes.addAttribute("error", "Login incorrect");
        return "redirect:/signIn";
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmailNotConfirmException.class)
    public String handleEmailNotProved(EmailNotConfirmException e){
        return "redirect:/logout";
    }

    @GetMapping("/error")
    public String getErrorPage(@RequestParam(value = "error", required = false) String error, Model model, ServletRequest servletRequest){
        Cookie token = WebUtils.getCookie((HttpServletRequest)servletRequest,"token");
        model.addAttribute("isLogged", token != null);
        if(error != null && !error.equals(""))model.addAttribute("error",error);
        else model.addAttribute("error","Unknown error");
        return "error";
    }
}
