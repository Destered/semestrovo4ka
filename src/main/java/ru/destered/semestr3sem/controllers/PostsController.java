package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.services.interfaces.PostsService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostsService service;

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPosts(@PathVariable Optional<Long> id) {
        return new ResponseEntity<>(service.getPosts(id
                .orElseThrow(InternalError::new)), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAllPosts(@RequestBody Optional<Integer> number) {
        return new ResponseEntity<>(service.getAllPosts(number.orElse(0)) ,HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPosts(@RequestBody PostForm form) {
        return new ResponseEntity<>(service.createPost(form, User.builder().build()), HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePosts(@PathVariable Long id,@RequestBody PostForm form) {
        return new ResponseEntity<>(service.updatePost(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePosts(@PathVariable Long id) {
        service.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
