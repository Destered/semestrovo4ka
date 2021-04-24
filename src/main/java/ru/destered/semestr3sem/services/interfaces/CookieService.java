package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.models.User;

import javax.servlet.http.Cookie;



public interface CookieService {
    boolean checkCookie(String cookieValue);

    Cookie createCookie(User userDto);
}
