package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PageManagerController {

    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping("/editProfile")
    public String getEditProfilePage() {
        return "edit_profile_page";
    }

    @GetMapping("/createPost")
    public String getCreatePostPage() {
        return "create_post_page";
    }

    @GetMapping("/allPosts")
    public String getAllPostsPage() {
        return "list_of_posts";
    }

    @GetMapping("/post")
    public String getPostPage() {
        return "post_page";
    }

    @GetMapping("/editPost")
    public String getEditPostPage() {
        return "edit_post_page";
    }
}