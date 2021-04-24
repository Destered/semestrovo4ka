package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

public interface SignUpService {
    UserDto signUp(SignUpForm form);
}
