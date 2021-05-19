package ru.destered.semestr3sem.services.implementations;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.SignUpService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final BCryptPasswordEncoder encoder;
    private final UsersRepository usersRepository;

    @Override
    public UserDto signUp(SignUpForm form) {
        if (usersRepository.existsByEmail(form.getEmail())) return null;

        User user = User.fromSignUpForm(form);
        user.setCurrentConfirmationCode(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setProved(false);
        user.setPhone("");
        usersRepository.save(user);
        return UserDto.fromUser(user);
    }
}
