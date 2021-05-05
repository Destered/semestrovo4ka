package ru.destered.semestr3sem.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.destered.semestr3sem.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByCurrentConfirmationCode(String code);

    Optional<User> findByUsername(String username);

    @Query("select u from User u where lower(u.username) like lower(concat('%', :nameToFind,'%')) ")
    Page<User> findAllByUsernameIgnoreCase(@Param("nameToFind") String username,
                                           Pageable pageable);
}
