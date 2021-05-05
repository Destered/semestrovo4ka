package ru.destered.semestr3sem.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.destered.semestr3sem.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.destered.semestr3sem.repositories.TokenRepository;


@RequiredArgsConstructor
@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final TokenRepository tokenRepository;


    @Override
    public UserDetails loadUserByUsername(String value) {
        DecodedJWT jwt = JWT.decode(value);
        return new UserDetailsImpl(User.builder()
                .id(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .username(jwt.getClaim("username").asString())
                .role(User.Role.valueOf(jwt.getClaim("role").asString()))
                .state(User.State.valueOf(jwt.getClaim("state").asString()))
                .build());
    }

}
