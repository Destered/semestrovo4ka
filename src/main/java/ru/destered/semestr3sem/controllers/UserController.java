package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
//@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UsersRepository usersRepository;

    @Value("${unauth.user.redirect.url}")
    private String unAuthUserRedirectUrl;

    @GetMapping
    public String loadProfile(RedirectAttributes redirectAttributes, @CookieValue(value = "AuthCookie", required = false) String cookieValue,
                              HttpServletRequest request, Model model) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> user = usersRepository.findByEmail(username);
        if(user.isPresent()){
            model.addAttribute("username", user.get().getUsername());
            model.addAttribute("email",user.get().getEmail());
        } else{
            throw new UsernameNotFoundException("User not found");
        }

        model.addAttribute("isLogged","true");


        return "profile";
    }
}
