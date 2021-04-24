package ru.destered.semestr3sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.JwtToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByValue(String value);
}
