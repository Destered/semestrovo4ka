package ru.destered.semestr3sem.services.impletentations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.SignInService;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signIn(UserAuthForm signInForm) throws LoginProcessErrorException {
        User user = usersRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new LoginProcessErrorException("User not found"));
        boolean passwordResult = passwordEncoder.matches(signInForm.getPassword(), user.getPassword());
        if(passwordResult) return user;
        return null;
    }
}
