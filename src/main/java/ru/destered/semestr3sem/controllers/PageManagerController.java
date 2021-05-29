package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class PageManagerController {
    
    private static final String ISLOGGED = "isLogged";

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@CookieValue Cookie token, Model model) {
        DecodedJWT jwt = JWT.decode(token.getValue());
        model.addAttribute("avatarImageName",jwt.getClaim("avatarImageName").asString());
        model.addAttribute("phoneNumber",jwt.getClaim("phone").asString());
        model.addAttribute(ISLOGGED,true);
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editProfile")
    public String getEditProfilePage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "edit_profile_page";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute(ISLOGGED,false);
        return "sign_in_page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/support")
    public String getSupportPage(Model model, ServletRequest servletRequest){
        Cookie token = WebUtils.getCookie((HttpServletRequest)servletRequest,"token");
        checkToken(token,model);
        return "support";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/main")
    public String getMainPage(Model model, ServletRequest servletRequest) {
        Cookie token = WebUtils.getCookie((HttpServletRequest)servletRequest,"token");
        checkToken(token,model);
        return "main";
    }
    
    private void checkToken(Cookie token,Model model){
            model.addAttribute(ISLOGGED,token != null);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/createPost")
    public String getCreatePostPage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "create_post_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/allPosts")
    public String getAllPostsPage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "list_of_posts";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post")
    public String getPostPage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "post_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editPost")
    public String getEditPostPage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "edit_post_page";
    }
}