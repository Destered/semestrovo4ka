package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.security.details.UserDetailsImpl;
import ru.destered.semestr3sem.services.interfaces.PostsService;

import javax.servlet.http.Cookie;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostsService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPosts(@PathVariable Optional<Long> id) {
        return new ResponseEntity<>(service.getPosts(id
                .orElseThrow(InternalError::new)), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAllPosts(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(service.getAllPosts(pageable), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts")
    public RedirectView createPosts(PostForm form, @CookieValue Cookie token) {
        if (service.createPost(form, token.getValue()) == null) throw new IllegalStateException();
        return new RedirectView("/allPosts");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts/{id}")
    public RedirectView updatePosts(@PathVariable Long id, PostForm form, @CookieValue Cookie token) {
        if (service.updatePost(id, form, TokenDto.builder().token(token.getValue()).build()) == null) throw new IllegalStateException();
        return new RedirectView("/allPosts");
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePosts(@PathVariable Long id) {
        service.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
