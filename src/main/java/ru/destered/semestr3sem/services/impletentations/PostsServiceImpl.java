package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.dto.mapper.FormPostMapper;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.PostsRepository;
import ru.destered.semestr3sem.services.interfaces.PostsService;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository repository;
    private final FormPostMapper mapper;

    @Override
    public Post getPosts(Long id) {
        return repository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Page<Post> getAllPosts(Integer number) {
        Pageable pageable = PageRequest.of(number, 3);
        return repository.findAll(pageable);
    }

    @Override
    public Post createPost(PostForm form, User user) {
        Post post = mapper.formToPost(form);
        post.setDateOfCreation(Date.valueOf(LocalDate.now()));
        post.setCreator(user);
        return repository.save(post);
    }

    @Override
    public Post updatePost(Long id, PostForm form) {
        Post postForUpdate = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        postForUpdate.setName(form.getName());
        postForUpdate.setText(form.getText());

        return repository.save(postForUpdate);
    }

    @Override
    public void deletePost(Long id) {
        Post postForDelete = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        repository.delete(postForDelete);
    }
}
