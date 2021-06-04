package ru.destered.semestr3sem.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.models.Comment;
import ru.destered.semestr3sem.repositories.CommentRepository;
import ru.destered.semestr3sem.services.interfaces.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
