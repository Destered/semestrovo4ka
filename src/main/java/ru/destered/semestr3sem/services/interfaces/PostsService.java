package ru.destered.semestr3sem.services.interfaces;

import org.springframework.data.domain.Page;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.models.User;

public interface PostsService {
     Post getPosts(Long id);

     Page<Post> getAllPosts(Integer number);

     Post createPost(PostForm form, User user);

     Post updatePost(Long id, PostForm form);

    void deletePost(Long id);
}
