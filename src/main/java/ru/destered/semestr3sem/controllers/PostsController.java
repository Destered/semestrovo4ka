package ru.destered.semestr3sem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Comment;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.repositories.CommentRepository;
import ru.destered.semestr3sem.services.interfaces.CommentService;
import ru.destered.semestr3sem.services.interfaces.PostsService;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name="Post controller", description="Post controller API")
public class PostsController {
    private final PostsService postsService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Operation(
            summary = "get single post",
            description = "get single post from id"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/posts/{id}")
    public Post getPosts(@PathVariable Optional<Long> id) {
        Post post = postsService.getPosts(id
                .orElseThrow(InternalError::new));
        return post;

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/posts/comments/{id}")
    public List<Comment> getPostComments(@PathVariable Optional<Long> id) {
        Post post = postsService.getPosts(id
                .orElseThrow(InternalError::new));

        return commentRepository.findByPost(post);
    }

    @Operation(
            summary = "get posts",
            description = "get all posts"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAllPosts(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(postsService.getAllPosts(pageable), HttpStatus.OK);
    }

    @Operation(
            summary = "crete post",
            description = "create post"
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts")
    public RedirectView createPosts(PostForm form, @CookieValue Cookie token) {
        if (postsService.createPost(form, token.getValue()) == null) throw new IllegalStateException();
        return new RedirectView("/allPosts");
    }

    @Operation(
            summary = "update post",
            description = "update post"
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts/{id}")
    public RedirectView updatePosts(@PathVariable Long id, PostForm form, @CookieValue Cookie token) {
        if (postsService.updatePost(id, form, TokenDto.builder().token(token.getValue()).build()) == null) throw new IllegalStateException();
        return new RedirectView("/allPosts");
    }

    @PostMapping("/post/{id}/addComment")
    public RedirectView addComment(@PathVariable Long id, String text){
        Post post = postsService.getPosts(id);
        Comment comment = null;
        if(text != null && !text.equals("")){
            comment = Comment.commentFromText(text,post);
        } else{
            comment = Comment.commentFromText("test text",post);
        }
        commentService.saveComment(comment);
        post.addComment(comment);
        postsService.updatePostComment(id,post);

        return new RedirectView("redirect:/post?id="+id);
    }

    @Operation(
            summary = "delete post",
            description = "allow you delete post"
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePosts(@PathVariable Long id) {
        postsService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
