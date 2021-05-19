package ru.destered.semestr3sem.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.dto.mapper.FormPostMapper;
import ru.destered.semestr3sem.models.Post;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.PostsRepository;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.PostsService;

import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository repository;
    private final FormPostMapper mapper;
    private final UsersRepository usersRepository;

    @Override
    public Post getPosts(Long id) {
        return repository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @SneakyThrows
    @Override
    public Post createPost(PostForm form, String token) {
        DecodedJWT jwt = JWT.decode(token);

        User user = usersRepository.findById(Long.parseLong(jwt.getSubject()))
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));

        Post post = mapper.formToPost(form);
        post.setDateOfCreation(Date.valueOf(LocalDate.now()));
        post.setCreator(user);
        post.setRating(0.0);
        post.setRatingCount(0);
        return repository.save(post);
    }

    @SneakyThrows
    @Override
    public Post updatePost(Long id, PostForm form, TokenDto token) {
        Post postForUpdate = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        DecodedJWT jwt = JWT.decode(token.getToken());

        if (postForUpdate.getCreator().getId().equals(Long.parseLong(jwt.getSubject()))) {
            postForUpdate.setName(form.getName());
            postForUpdate.setText(form.getText());

            return repository.save(postForUpdate);
        } else {
            throw new AccessDeniedException("omaewa mou shindeiru");
        }
    }

    @Override
    public void deletePost(Long id) {
        Post postForDelete = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        repository.delete(postForDelete);
    }
}
