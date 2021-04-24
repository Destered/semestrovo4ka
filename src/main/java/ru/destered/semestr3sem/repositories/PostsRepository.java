package ru.destered.semestr3sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.Post;

public interface PostsRepository extends JpaRepository<Post, Long> {
}
