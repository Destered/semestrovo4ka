package ru.destered.semestr3sem.services.implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.ConfirmationService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    private final UsersRepository usersRepository;
    //@Transactional
    @Override
    public boolean confirmByCode(String code) {
        Optional<User> userCandidate = usersRepository.findByCurrentConfirmationCode(code);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            user.setProved(true);
            usersRepository.save(user);
        }
        return userCandidate.isPresent();
    }
}
