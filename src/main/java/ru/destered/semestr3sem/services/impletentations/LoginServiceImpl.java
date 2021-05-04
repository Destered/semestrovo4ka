package ru.destered.semestr3sem.services.impletentations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.models.JwtToken;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.TokenRepository;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.LoginService;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UsersRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("key")
    private String key;

    @SneakyThrows
    @Override
    public TokenDto login(UserAuthForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getEmail()).orElseThrow
                ((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(signInForm.getPassword(), user.getPassword())) {
            String tokenValue = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("username", user.getUsername())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("state", user.getState().toString())
                    .withClaim("code", user.getCurrentConfirmationCode())
                    .withClaim("phone",user.getPhone())
                    .sign(Algorithm.HMAC256(key));
            JwtToken token = JwtToken.builder()
                    .user(user)
                    .value(tokenValue)
                    .build();
            tokenRepository.save(token);
            return TokenDto.builder().token(tokenValue).build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
