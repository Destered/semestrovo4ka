package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.UserDto;

public interface MailService {
    void sendMail(UserDto userDto);
}
