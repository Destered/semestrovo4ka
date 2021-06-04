package ru.destered.semestr3sem.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Post;

public interface PostsService {
     Post getPosts(Long id);

     Page<Post> getAllPosts(Pageable pageable);

     Post createPost(PostForm form, String token);

     Post updatePost(Long id, PostForm form, TokenDto token);

     Post updatePostComment(Long id,Post post);

    void deletePost(Long id);
}
