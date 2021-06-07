package ru.destered.semestr3sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.Comment;
import ru.destered.semestr3sem.models.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByPost(Post post);
}
