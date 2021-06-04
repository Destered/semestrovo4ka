package ru.destered.semestr3sem.handlers;

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
    
    private static final String ERROR ="error";

    @ExceptionHandler({ LoginProcessErrorException.class,UsernameNotFoundException.class })
    public String loginException(RedirectAttributes redirectAttributes, Exception ex){
        redirectAttributes.addAttribute(ERROR, "Login incorrect");
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
    public String getErrorPage(@RequestParam(value = ERROR, required = false) String errorMsg, Model model, ServletRequest servletRequest){
        Cookie token = WebUtils.getCookie((HttpServletRequest)servletRequest,"token");
        model.addAttribute("isLogged", token != null);
        if(errorMsg != null && !errorMsg.equals(""))model.addAttribute(ERROR,errorMsg);
        else model.addAttribute(ERROR,"Unknown error");
        return ERROR;
    }
}
