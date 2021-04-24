package ru.destered.semestr3sem.services.interfaces;

import org.springframework.data.domain.Page;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

public interface UsersService {
    Page<UserDto> getUsers(int number);

    UserDto createUser(SignUpForm form);

    UserDto updateUser(Long id, SignUpForm form);

    void deleteUser(Long id);
}