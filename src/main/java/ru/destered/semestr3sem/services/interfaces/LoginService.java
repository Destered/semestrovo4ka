package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.TokenDto;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;

public interface LoginService {
    TokenDto login(UserAuthForm signInForm);
}
