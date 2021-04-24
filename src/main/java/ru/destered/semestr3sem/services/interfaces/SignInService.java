package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;
import ru.destered.semestr3sem.models.User;

public interface SignInService {
    User signIn(UserAuthForm signInForm) throws LoginProcessErrorException;
}
