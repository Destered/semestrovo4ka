package ru.destered.semestr3sem.security.details;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.destered.semestr3sem.models.JwtToken;
import ru.destered.semestr3sem.repositories.TokenRepository;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final TokenRepository tokenRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        JwtToken token = tokenRepository.findByValue(value).orElseThrow((Supplier<Throwable>) () ->
        new UsernameNotFoundException("Token not found"));
        return new UserDetailsImpl(token.getUser());
    }
}
