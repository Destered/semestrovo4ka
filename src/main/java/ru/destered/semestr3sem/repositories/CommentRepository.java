package ru.destered.semestr3sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
