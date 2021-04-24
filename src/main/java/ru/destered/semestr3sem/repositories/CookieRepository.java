package ru.destered.semestr3sem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.Cookie;

import java.util.Optional;

public interface CookieRepository extends JpaRepository<Cookie, String> {

    Optional<Cookie> findByUuid(String uuid);

}
