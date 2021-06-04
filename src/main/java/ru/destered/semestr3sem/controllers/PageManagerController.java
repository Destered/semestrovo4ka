package ru.destered.semestr3sem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

@Controller
@RequiredArgsConstructor
public class PageManagerController {
    private final UsersRepository repository;
    
    private static final String ISLOGGED = "isLogged";

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@CookieValue Cookie token, Model model) {
        DecodedJWT jwt = JWT.decode(token.getValue());
        try {
            User user = repository.findById(Long.parseLong(jwt.getSubject()))
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            model.addAttribute("username",user.getUsername());
            model.addAttribute("avatarImageName",user.getAvatarImageName());
            model.addAttribute("phone",user.getPhone());
            model.addAttribute("email",user.getEmail());
            model.addAttribute("role",user.getRole());
        } catch(Throwable ex){
            return "redirect:/error";
        }
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
    public String getLoginPage(Model model, @RequestParam(required = false) String info) {
        model.addAttribute(ISLOGGED,false);
        if(info != null) model.addAttribute("info",info);
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
    @GetMapping("/chat")
    public String getChatPage(@CookieValue Cookie token, Model model){
        DecodedJWT jwt = JWT.decode(token.getValue());
        try{
            User user = repository.findById(Long.parseLong(jwt.getSubject()))
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            model.addAttribute("username",user.getUsername());
        } catch(Throwable ex){
            throw new IllegalStateException();
        }
        model.addAttribute(ISLOGGED,true);
        return "chat";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usersProfile/{id}")
    public String openUserProfile(@CookieValue Cookie token,Model model,@PathVariable(name = "id") String id){
        DecodedJWT jwt = JWT.decode(token.getValue());
        try{
            User currentUser = repository.findById(Long.parseLong(jwt.getSubject()))
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            User profileUser = repository.findById(Long.parseLong(id))
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            if(currentUser.getId().equals(profileUser.getId())){
                model.addAttribute("isOwner",true);
            }else{
                model.addAttribute("isOwner",false);
            }
            model.addAttribute("username",profileUser.getUsername());
            model.addAttribute("email",profileUser.getEmail());
            model.addAttribute("role",profileUser.getRole());
        } catch(Throwable ex){
            throw new IllegalStateException();
        }

        model.addAttribute(ISLOGGED,true);
        return "user";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/allPosts")
    public String getAllPostsPage(Model model) {
        model.addAttribute(ISLOGGED,true);
        return "list_of_posts";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post")
    public String getPostPage(Model model,@CookieValue Cookie token,
                              @RequestParam(value="id", required=true   ) String id) {
        DecodedJWT jwt = JWT.decode(token.getValue());
        try{
            User user = repository.findById(Long.parseLong(jwt.getSubject()))
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
            model.addAttribute("username",user.getUsername());
            model.addAttribute("id",id);
        } catch(Throwable ex){
            throw new IllegalStateException();
        }
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